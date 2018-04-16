/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Entity;
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
    @NamedQuery( name="Compte.findAll",query="SELECT c FROM Compte c"),
    @NamedQuery( name="Compte.findAllCompteByClient",query="SELECT c FROM Compte c"),
    @NamedQuery( name="Compte.findClientByOwner",query="SELECT c FROM Compte c where c.owner.id=:owner"),
     @NamedQuery( name="Compte.findCompteByNumber",query="SELECT c FROM Compte c where c.numero=:numero")
})
////@NamedQuery( name="Compte.findAllCompteByClient",query="SELECT c, client.NIP FROM Compte c LEFT OUTER JOIN c.owner client")

//findAllCompteByClient
public class Compte implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @ManyToOne
    //@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="OWNER_ID")
   
    private Client owner;

    public void setOwner(Client owner) {
        this.owner = owner;
    }

    public Client getOwner() {
        return owner;
    }

    private String numero;

    private Double sold;
    private String type_compte;



    public Compte(String numero, Double sold, String type_compte) {
        this.numero = numero;
        this.sold = sold;
        this.type_compte = type_compte;
    }

    public Compte() {
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getSold() {
        return sold;
    }

    public void setSold(Double sold) {
        this.sold = sold;
    }

    public String getType_compte() {
        return type_compte;
    }

    public void setType_compte(String type_compte) {
        this.type_compte = type_compte;
    }
    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
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
        if (!(object instanceof Compte)) {
            return false;
        }
        Compte other = (Compte) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Compte[ id=" + id + " ]";
    }
    
}
