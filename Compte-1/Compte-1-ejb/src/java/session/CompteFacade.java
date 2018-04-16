/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entities.Compte;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author dtic
 */
@Stateless
public class CompteFacade extends AbstractFacade<Compte> {

    @PersistenceContext(unitName = "Compte-1-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CompteFacade() {
        super(Compte.class);
    }
    public List<Compte> getAllCompte(){
        Query query=em.createNamedQuery("Compte.findAll");
        return query.getResultList();
    }
    
    //Rechercher tus les clients
    public List<Compte> findAllCompteClient(){
        Query query=em.createNamedQuery("Compte.findAllCompteByClient");
        return query.getResultList();

    }
    //Recher compte par un client specifique
    
    public List<Compte> getCompteByOwner(Long owner){
        Query query=em.createNamedQuery("Compte.findClientByOwner").setParameter("owner", owner);
        //Query query=em.createNamedQuery("Compte.findAll");
        return query.getResultList();
    }
    
    public Compte getCompteByNumber(String number){
        try{
                Query query=em.createNamedQuery("Compte.findCompteByNumber").setParameter("numero",number);
                return (Compte) query.getSingleResult();
        }
        catch(Exception ex){
        return null;
        }

    }    
    public void depot(Long id,double montant){
        em.createQuery("UPDATE Compte c set c.sold=c.sold+:montant where c.id=:id").setParameter("id", id).setParameter("montant", montant).executeUpdate();
    }
    public void debit(Long id,double montant){
        em.createQuery("UPDATE Compte c set c.sold=((c.sold)-:montant) where c.id=:id").setParameter("id", id).setParameter("montant", montant).executeUpdate();
    }
}
