package ssamba.ept.sn.BankerApp.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import ssamba.ept.sn.BankerApp.model.Agence;
import ssamba.ept.sn.BankerApp.model.Client;
import ssamba.ept.sn.BankerApp.model.Compte;

import java.util.stream.Stream;

public interface AgenceRepository extends JpaRepository<Agence, Integer> {

}
