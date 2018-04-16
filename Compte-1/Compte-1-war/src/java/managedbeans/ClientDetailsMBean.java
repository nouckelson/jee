/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbeans;

import entities.Client;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import session.ClientFacade;

/**
 *
 * @author dtic
 */
@Named(value = "clientDetailsMBean")
@ViewScoped
public class ClientDetailsMBean implements Serializable {
    private int idClient;  
    private Client client;  

    public Client getClient() {
        return client;
    }


    @EJB
    private ClientFacade clientFacade;

    /**
     * Creates a new instance of ClientDetailsMBean
     */
    public ClientDetailsMBean() {
          
    }

    public void doCreateClient(Client client){
        clientFacade.create(client);
        
    }
}
