package ssamba.ept.sn.BankerApp.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import ssamba.ept.sn.BankerApp.model.Client;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Integer> {
   // List<Client> findClientById(Integer id);
}
