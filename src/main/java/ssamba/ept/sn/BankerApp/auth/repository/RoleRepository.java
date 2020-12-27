package ssamba.ept.sn.BankerApp.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ssamba.ept.sn.BankerApp.auth.model.Role;
import ssamba.ept.sn.BankerApp.auth.model.RoleName;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName roleName);
}