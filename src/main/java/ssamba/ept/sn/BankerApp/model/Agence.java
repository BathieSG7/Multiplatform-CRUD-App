package ssamba.ept.sn.BankerApp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@Builder(toBuilder = true)
@AllArgsConstructor
@Entity(name = "Agence")
@Table(name = "agence")
public class Agence {
    @Id
    @GeneratedValue
    private int code;
    @Length(min=3, max=45, message = "Nom trop long ou trop court. ")
    private String nom;
    @Length(min=3, max=45)
    private String adresse;
    @Length(min=3, max=45)
    private String telephone;

    @OneToMany(
            mappedBy = "agence",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )

    private List<Compte> comptes = new ArrayList<>();


    public void ajouterCompte(Compte compte) {
        comptes.add(compte);
        compte.setAgence(this);
    }

    public void supprimerCompte(Compte compte) {
        comptes.remove(compte);
        compte.setAgence(null);
    }
}
