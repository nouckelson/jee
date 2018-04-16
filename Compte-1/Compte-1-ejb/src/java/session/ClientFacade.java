/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entities.Client;
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
public class ClientFacade extends AbstractFacade<Client> {

    @PersistenceContext(unitName = "Compte-1-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ClientFacade() {
        super(Client.class);
    }
    
    
    public void delete(Long id){
        Query query=em.createNamedQuery("Client.deletByid").setParameter("id", id);
    }
    

    public List<Client> getAllAgent(){
        Query query=em.createNamedQuery("Client.findAllAgent").setParameter("role",Client.Role.AGENT);
        return query.getResultList();
    }
    
    public List<Client> getAllClient(){
        
        Query query=em.createNamedQuery("Client.findAllClient").setParameter("role",Client.Role.CLIENT);
        System.out.println(query.getResultList());
        return query.getResultList();
    }
    
    
    public Client getClientIdFromStringId(Long id) {
        Query query = em.createNamedQuery("Client.findById");
        query.setParameter("id",id);
        return (Client) query.getSingleResult();
    }
    
   public Client getClientByEmailPwd(String email,String pwd){
       try{
                Query query = em.createNamedQuery("Client.findByEmailPwd");
                query.setParameter("email",email);
                query.setParameter("password",pwd);
                return (Client) query.getSingleResult();
       }
       catch(Exception ex){
           System.out.println(ex.toString());
           return null;
       }

       

   }
//    
//    public List<Client> getClientByName(String nom){
//        Query query=em.createNamedQuery("Client.findByName").setParameter("nom", nom);
//        return query.getResultList();
//        //return super.find(nom);
//    }
//    
//    @Override
//    public void create(Client entity) {
//        super.create(entity);
//    }
//        public Contact createContact(Contact contact){
//        em.persist(contact);
//        return contact;
//    }
    
    /**
     *
     * @param id
     * @return
     */
//    public Client find(Long id){
//       return super.find(id);
//    }
//    
//   public void creerClient(Client c){
//        em.persist(c);
//    }
//    
//    public void creerUnNouveauClient()
//    {
//       creerClient(new Client("Joseph","Alexis","45555555","nazon"));
//    }
}
