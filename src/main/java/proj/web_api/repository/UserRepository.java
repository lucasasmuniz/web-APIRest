package proj.web_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import proj.web_api.model.User;

@Repository
public interface UserRepository extends JpaRepository<User,Integer>{
    public User findByLogin(String login);
}
