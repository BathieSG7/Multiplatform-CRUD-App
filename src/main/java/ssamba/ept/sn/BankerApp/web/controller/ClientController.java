package ssamba.ept.sn.BankerApp.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ssamba.ept.sn.BankerApp.model.Client;
import ssamba.ept.sn.BankerApp.repository.ClientRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;

import ssamba.ept.sn.BankerApp.web.exceptions.ResourceNotFoundException;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;


@Api( description="API pour les opérations CRUD sur les Clients.")
@RestController
@Slf4j
public class ClientController {
    @Autowired
    private ClientRepository clientRepository;
    //private CompteRepository compteRepository;

    @ApiOperation(value = "Récupère un client grâce à son ID à condition que celui-ci existe!")
    @GetMapping(path = "/client/{id}")
    public Client getClient(@PathVariable Integer id) {
        return clientRepository
                .findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Le client",id));
    }
    @GetMapping("/client/")
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    @PostMapping(path = "/client/")
    public ResponseEntity<Client> addClient(@Valid @RequestBody Client client) {
        /*client.getComptes().stream().flatMap(compte -> {
           // System.out.println(compte);
            log.info(compte.toString());

            return null;
        });*/
        //List<Compte> comptes =  ;
        //Client clientAdded =  clientRepository.save(client.toBuilder().comptes(client.getComptes()).build());
        Client clientAdded =  clientRepository.save(client);
        if (clientAdded == null)
            return ResponseEntity.noContent().build();

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(clientAdded.getId())
                .toUri();
        return ResponseEntity.created(location).body(clientAdded);
    }

    @DeleteMapping("/client/{id}")
    public ResponseEntity<?> deleteClient(@PathVariable(value = "id") int id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Client", id));

        clientRepository.delete(client);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/client/{id}")
    public ResponseEntity<?> updateClient(@PathVariable(value = "id") int clientId,@Valid @RequestBody Client client) {
        Client existingClient = clientRepository.findById(clientId)
                .orElseThrow(() -> new ResourceNotFoundException("Le Client",clientId));
        existingClient.setNom(client.getNom());
        existingClient.setPrenom(client.getPrenom());
        existingClient.setDateNaissance(client.getDateNaissance());
        client = clientRepository.save(existingClient);
        return ResponseEntity.ok().body(client) ;
    }

}
