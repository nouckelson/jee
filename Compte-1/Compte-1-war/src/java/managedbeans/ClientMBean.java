/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbeans;

import entities.Client;
import entities.Compte;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import session.*;

/**
 *
 * @author dtic
 */
@Named(value = "clientMBean")
@SessionScoped
public class ClientMBean implements Serializable {

    @EJB
    private CompteFacade compteFacade;
        private Compte cp=new Compte();
        private List<Compte> comptes;
        
    public List<Compte> getComptes() {
        return comptes;
    }
    @EJB
    private ClientFacade clientFacade;
    
    private Client c=new Client();
    private List<Client> clients;
    public List<Client> getClients() {
        return clientFacade.findAll();
    }
    public Client  getClient(Long id) {
        return clientFacade.find(id);
    }
    public Client getC() {
        return c;
    }

    public void setC(Client c) {
        this.c = c;
    }
    
    public String add(){
        String link="";
        if(this.c.getRole()==Client.Role.AGENT){
            this.c.setRole(Client.Role.AGENT);
            link="/Client/addagent";
        }
        else{
            this.c.setRole(Client.Role.CLIENT);
            link="/Client/index";
        }
        this.clientFacade.create(this.c);
        this.c=new Client();
        return link;
    }

    public void delete(Client c){
        this.clientFacade.remove(this.c);
    }

    public String edit(Client c){
        this.c=c;
        return "edit";
    }
    
    public String edit(){
        this.clientFacade.edit(this.c);
        return "index";
    }
    /**
     * Creates a new instance of ClientMBean
     */

    public List<Client> showClient(){
        return clientFacade.getAllClient();
    }
    
    public String view(Client c){
        this.c=c;
        this.comptes=this.views(c);
        return "compteClient";
    } 
    
    public List<Compte> views(Client c){        
        return compteFacade.getCompteByOwner(c.getId());
    }
//    
//    public List<Client> showClientByName(String nom){
//        return clientFacade.getClientByName(nom);
//    }
    
}
