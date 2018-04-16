/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author dtic
 */
@Entity
@NamedQueries({
    @NamedQuery( name="Operation.findAll",query="SELECT o FROM Operation o")
   // @NamedQuery( name="Operation.findOperationByCompte",query="SELECT * FROM OPERATION WHERE COMPTE_ID_CLIENT=101")
})
public class Operation implements Serializable {
    public enum TypeOperation {
           DEPOT,RETRAIT,VIREMENT 
    }
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private double montant;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="COMPTE_ID_CLIENT")
    private Compte compteOwner;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="COMPTE_ID_VIREMENT")
    private Compte compteVirement;
    
    private Date date_operation=new Date();
    
    @Enumerated(EnumType.STRING)
    private TypeOperation type_operation;

        
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getMontant() {
        return montant;
    }

    public void setMontant(double montant) {
        this.montant = montant;
    }

    public Compte getCompteOwner() {
        return compteOwner;
    }

    public void setCompteOwner(Compte compteOwner) {
        this.compteOwner = compteOwner;
    }

    public Compte getCompteVirement() {
        return compteVirement;
    }

    public void setCompteVirement(Compte compteVirement) {
        this.compteVirement = compteVirement;
    }

    public Date getDate_operation() {
        return date_operation;
    }

    public void setDate_operation(Date date_operation) {
        this.date_operation = date_operation;
    }

    public TypeOperation getType_operation() {
        return type_operation;
    }

    public void setType_operation(TypeOperation type_operation) {
        this.type_operation = type_operation;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Operation)) {
            return false;
        }
        Operation other = (Operation) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Operation[ id=" + id + " ]";
    }
    
}
