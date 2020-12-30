package ssamba.ept.sn.BankerApp.web.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ssamba.ept.sn.BankerApp.dto.CompteDto;
import ssamba.ept.sn.BankerApp.model.Agence;
import ssamba.ept.sn.BankerApp.model.Client;
import ssamba.ept.sn.BankerApp.model.Compte;
import ssamba.ept.sn.BankerApp.repository.AgenceRepository;
import ssamba.ept.sn.BankerApp.repository.ClientRepository;
import ssamba.ept.sn.BankerApp.repository.CompteRepository;
import ssamba.ept.sn.BankerApp.web.exceptions.ResourceNotFoundException;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;


@Api( description="API pour les opérations CRUD sur les Comptes.")
@RestController
@Slf4j
public class CompteController {
    @Autowired
    private CompteRepository compteRepository;
    @Autowired
    private AgenceRepository agenceRepository;
    @Autowired
    private ClientRepository clientRepository;

    @ApiOperation(value = "Récupère un compte grâce à son ID à condition que celui-ci existe!")
    @GetMapping(path = "/compte/{id}")
    public Compte getCompte(@PathVariable Integer id) {
        return compteRepository
                .findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Le compte",id));
    }
    @GetMapping("/compte/all")
    public List<Compte> getAllComptes() {
        return compteRepository.findAll();
    }



    @PostMapping(path = "/compte/new")
    public ResponseEntity addCompte(@Valid @RequestBody CompteDto compteDto) {
        Optional<Agence> agence = agenceRepository.findById(compteDto.getAgenceCode()) ;
        Optional<Client> client = clientRepository.findById(compteDto.getClientId() ) ;

        if(agence.isPresent() && client.isPresent()){
            Agence currAgence = agenceRepository.saveAndFlush(agence.get());
            Client currClient = clientRepository.saveAndFlush(client.get());
            Compte compte = new Compte();
            BeanUtils.copyProperties(compteDto , compte );
            Compte compteAdded =  compteRepository.saveAndFlush(compte.toBuilder().agence(currAgence).client(currClient).build());
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(compteAdded.getId())
                    .toUri();
            return ResponseEntity.created(location).body(compteAdded);
        }else{
            return ResponseEntity.badRequest().body("L'agence ou le client spécifié n'existe pas ");
        }
    }


   /* @PostMapping(path = "/compte/new")
    public ResponseEntity<Compte> addCompte(@Valid @RequestBody ObjectKey objectKey) {

        Agence currAgence = compte.getAgence();
        Optional<Agence> agence = agenceRepository.findById(currAgence.getCode()) ;
        if(agence.isPresent()){
            currAgence = agenceRepository.saveAndFlush(agence.get());
        }else{
            currAgence = agenceRepository.saveAndFlush(currAgence);
        }

        Client currClient = compte.getClient();
        Optional<Client> client = clientRepository.findById(currClient.getId()) ;
        if(client.isPresent()){
            currClient = clientRepository.saveAndFlush(client.get());
        }else{
            currClient = clientRepository.saveAndFlush(currClient);
        }

        Compte compteAdded =  compteRepository.saveAndFlush(compte.toBuilder().agence(currAgence).client(currClient).build());

        if (compteAdded == null)
            return ResponseEntity.noContent().build();

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(compteAdded.getId())
                .toUri();
        return ResponseEntity.created(location).body(compteAdded);
    }
*/
   @PutMapping("/compte/{id}")
   public ResponseEntity<?> updateAgence(@PathVariable(value = "id") int compteId,@Valid @RequestBody CompteDto compte) {
       Compte existingCompte= compteRepository.findById(compteId)
               .orElseThrow(() -> new ResourceNotFoundException("Le Code",compteId));
       existingCompte.setDecouvert(compte.getDecouvert());
       existingCompte.setSolde(compte.getSolde());
      // compte = compteRepository.save(existingCompte);
       return ResponseEntity.ok().body(compteRepository.save(existingCompte)) ;
   }

    @DeleteMapping("/compte/{id}")
    public ResponseEntity<?> deleteCompte(@PathVariable(value = "id") int id) {
        Compte compte = compteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Compte", id));

        compteRepository.delete(compte);
        return ResponseEntity.ok().build();
    }



}
