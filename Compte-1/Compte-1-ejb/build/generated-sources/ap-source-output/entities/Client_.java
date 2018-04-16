package entities;

import entities.Client.Role;
import entities.Compte;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-04-15T16:19:34")
@StaticMetamodel(Client.class)
public class Client_ { 

    public static volatile SingularAttribute<Client, String> password;
    public static volatile SingularAttribute<Client, Role> role;
    public static volatile SingularAttribute<Client, String> NIP;
    public static volatile SingularAttribute<Client, String> phone;
    public static volatile SingularAttribute<Client, String> adresse;
    public static volatile SingularAttribute<Client, Long> id;
    public static volatile SingularAttribute<Client, String> nom;
    public static volatile SingularAttribute<Client, String> prenom;
    public static volatile SingularAttribute<Client, String> email;
    public static volatile ListAttribute<Client, Compte> compte;

}