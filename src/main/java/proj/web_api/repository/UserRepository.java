package proj.web_api.repository;

import java.util.List;
import org.springframework.stereotype.Repository;
import proj.web_api.model.User;

@Repository
public class UserRepository {
    
    public void save(User user){}
    public void deleteById(Integer id){}
    public List<User> findAll(){}
    public User findById(Integer id){}
    public User findByUsername(String username){}
}
