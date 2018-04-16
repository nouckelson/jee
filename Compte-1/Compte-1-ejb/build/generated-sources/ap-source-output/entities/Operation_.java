package entities;

import entities.Compte;
import entities.Operation.TypeOperation;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-04-15T16:19:34")
@StaticMetamodel(Operation.class)
public class Operation_ { 

    public static volatile SingularAttribute<Operation, Double> montant;
    public static volatile SingularAttribute<Operation, Long> id;
    public static volatile SingularAttribute<Operation, Date> date_operation;
    public static volatile SingularAttribute<Operation, Compte> compteOwner;
    public static volatile SingularAttribute<Operation, TypeOperation> type_operation;
    public static volatile SingularAttribute<Operation, Compte> compteVirement;

}