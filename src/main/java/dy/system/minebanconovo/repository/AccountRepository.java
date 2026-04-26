package dy.system.minebanconovo.repository;

import dy.system.minebanconovo.domain.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, String> {
}
