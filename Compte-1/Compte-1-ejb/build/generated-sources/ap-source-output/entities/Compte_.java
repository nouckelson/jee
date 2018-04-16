package entities;

import entities.Client;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-04-15T16:19:34")
@StaticMetamodel(Compte.class)
public class Compte_ { 

    public static volatile SingularAttribute<Compte, Client> owner;
    public static volatile SingularAttribute<Compte, Double> sold;
    public static volatile SingularAttribute<Compte, String> numero;
    public static volatile SingularAttribute<Compte, String> type_compte;
    public static volatile SingularAttribute<Compte, Long> id;

}