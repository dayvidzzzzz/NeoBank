package dy.system.minebanconovo.repository;

import dy.system.minebanconovo.domain.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, String> {
}
