package ssamba.ept.sn.BankerApp.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import ssamba.ept.sn.BankerApp.model.Client;
import ssamba.ept.sn.BankerApp.model.Compte;

import java.util.stream.Stream;

public interface CompteRepository extends JpaRepository<Compte, Integer> {
    //Stream<Compte> save(Compte compte);
   /*Stream<Compte> save(Compte compte){
        saveAndFlush(compte);
        return Stream.<Compte>builder().build();
    }*/
    // List<Client> findClientById(Integer id);
}
