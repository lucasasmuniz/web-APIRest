package proj.web_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import proj.web_api.model.User;

@Repository
public interface UserRepository extends JpaRepository<User,Integer>{
    //Criar algumas consultas aqui
    @Query("SELECT e FROM User e JOIN FETCH e.roles WHERE e.login= (:login)")
    public User findByLogin(@Param ("login") String login);
    
    public boolean existsByLogin(String login);
}
