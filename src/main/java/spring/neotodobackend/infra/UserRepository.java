package spring.neotodobackend.infra;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.neotodobackend.domain.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByIdAndPassword(String id, String password);
    Optional<User> findByNicknameAndStatus(String nickname, String status);
    Optional<User> findByIdAndStatus(String id, String status);

}
