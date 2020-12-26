package ssamba.ept.sn.BankerApp.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ObjectKey {
    private static final long serialVersionUID = 1L;

    private int clientId;
    private int agenceCode;
    private Client client;
    private Agence agence;
}
