package ssamba.ept.sn.BankerApp.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.validator.constraints.Length;


@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Entity(name = "Client")
@Table(name = "client")
public class Client {

    @Id
    @GeneratedValue
    private int id;
    @Length(min=3, max=45, message = "Nom trop long ou trop court. ")
    private String nom;
    @Length(min=3, max=45, message = "Nom trop long ou trop court. ")
    private String prenom;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate dateNaissance;

    @OneToMany(
            mappedBy = "client",
           // cascade = CascadeType.ALL,
            orphanRemoval = true
    )

    private List<Compte> comptes = new ArrayList<>();


    public void ajouterCompte(Compte compte) {
        comptes.add(compte);
        compte.setClient(this);
    }

    public void supprimerCompte(Compte compte) {
        comptes.remove(compte);
        compte.setClient(null);
    }
}
