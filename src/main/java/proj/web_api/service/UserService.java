package proj.web_api.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import proj.web_api.handler.NoUserFoundException;
import proj.web_api.handler.ObligatoryFieldException;
import proj.web_api.model.Endereco;
import proj.web_api.model.User;
import proj.web_api.repository.EnderecoRepository;
import proj.web_api.repository.UserRepository;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EnderecoRepository enderecoRepository;
    // @Autowired
    // private PasswordEncoder encoder;
    @Autowired
    private ViaCepService viaCepService;
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    public void createUser(User user){
        Endereco endereco = fazerChecagens(user);
        user.setEndereco(endereco);
        userRepository.save(user);
    }
    
    public void deleteUser(Integer id){
        if (userRepository.findById(id).isEmpty()){
            throw new NoUserFoundException("Usuário com a id " + id + " não foi encontrado");
        }
        userRepository.deleteById(id);
    }
    
    public Iterable<User> findAllUsers(){
        return userRepository.findAll();
    }
    
    public User findUserByLogin(String login){
        return userRepository.findByLogin(login);
    }
    
    public void refreshUserData(Integer id, User user) {
        Endereco endereco = fazerChecagens(user);
        User existingUser = userRepository.findById(id).orElseThrow(() -> 
            new RuntimeException(String.format("O Usuário com id: %d não existe.", id))
        );
    
        // Atualizar o usuário com as novas informações
        existingUser.setLogin(user.getLogin());
        existingUser.setName(user.getName());
        existingUser.setPassword(user.getPassword());
        existingUser.setEndereco(endereco);
    
        // Salvar as mudanças no banco
        userRepository.save(existingUser); // Atualiza o usuário existente
    }
        
    private Endereco fazerChecagens(User user) {
        if (user.getLogin() == null) {
            throw new ObligatoryFieldException("login");
        } else if (user.getName() == null) {
            throw new ObligatoryFieldException("nome");
        } else if (user.getPassword() == null) {
            throw new ObligatoryFieldException("senha");
        }

        String cep = user.getEndereco().getCep();

        Endereco endereco = enderecoRepository.findById(cep).orElseGet(() -> {
            logger.info("Endereço não encontrado no banco. Consultando API ViaCEP.");
            Endereco novoEndereco = viaCepService.consultarCep(cep);
            novoEndereco.setCep(novoEndereco.getCep().replace("-", ""));

            if (novoEndereco == null || novoEndereco.getCep() == null) {
                throw new RuntimeException("Endereço inválido ou CEP não encontrado: " + cep);
            }

            return enderecoRepository.save(novoEndereco);
        });
        return endereco;
    }
}