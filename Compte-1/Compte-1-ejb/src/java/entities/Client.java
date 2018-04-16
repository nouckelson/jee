/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import lib.Md5;

/**
 *
 * @author dtic
 */
@Entity
@NamedQueries({
    @NamedQuery(name="Client.findAll",query="SELECT c FROM Client c "),
    @NamedQuery(name="Client.findAllClient",query="SELECT c FROM Client c WHERE c.role=:role "),
    @NamedQuery(name="Client.findAllAgent",query="SELECT c FROM Client c WHERE c.role='AGENT' "),
    @NamedQuery(name="Client.findById",query="SELECT c FROM Client c WHERE c.id = :id"),
    @NamedQuery(name="Client.findByName",query="SELECT c FROM Client c WHERE c.nom = :nom"),
    @NamedQuery(name="Client.deletByid",query="delete FROM Client c WHERE c.id = :id"),
    @NamedQuery(name="Client.findByEmailPwd",query="SELECT c FROM Client c WHERE (c.email= :email AND c.password=:password)")
})
public class Client implements Serializable {
    public enum Role {
           AGENT,CLIENT 
    }
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name="CLIENT_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(unique = true, nullable = false)
    private String NIP ;
    @Column(nullable = false)
    private String nom ;
    @Column(nullable = false)
    private String prenom;
    @Column(nullable = false)
    private String phone;
    @Column(nullable = false)
    private String adresse;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    private String email;
    @Column(nullable = false)
    private String password;    
    @OneToMany(mappedBy="owner")
    private List<Compte> compte;

    public Client(String nom, String prenom, String phone, String adresse) {
        this.nom = nom;
        this.prenom = prenom;
        this.phone = phone;
        this.adresse = adresse;
    }


    public Client() {
    }

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getNIP() {
        return NIP;
    }

    public void setNIP(String NIP) {
        this.NIP = NIP;
    }
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        Md5 encrypt=new Md5();
        this.password = encrypt.encryptMd5(password);
    }
    
    
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
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
        if (!(object instanceof Client)) {
            return false;
        }
        Client other = (Client) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Client[ id=" + id + " ]";
    }
    
}
