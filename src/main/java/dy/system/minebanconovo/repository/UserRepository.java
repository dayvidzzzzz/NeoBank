package dy.system.minebanconovo.repository;

import dy.system.minebanconovo.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
    boolean existsByCpf(String cpf);
    boolean existsByEmail(String email);
    boolean existsByLogin(String login);
}
