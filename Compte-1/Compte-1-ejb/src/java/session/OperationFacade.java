/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

import entities.Compte;
import entities.Operation;
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
public class OperationFacade extends AbstractFacade<Operation> {

    @PersistenceContext(unitName = "Compte-1-ejbPU")
    private EntityManager em;

    public OperationFacade() {
        super(Operation.class);
    }

    public List<Operation> getAllOperation(){
        Query query=em.createNamedQuery("Operation.findAll");
        return query.getResultList();
    }
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
        
  //Recher ensemble d'operation par un compte specifique
        public List<Operation> getOperationByCompte(Long compte){
            Query query= em.createQuery("SELECT op FROM Operation op WHERE op.compteOwner.id=:compte")
                .setParameter("compte", compte);
        return query.getResultList();
    }

    public void persist(Object object) {
        em.persist(object);
    }
}
