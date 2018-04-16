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
import javax.ejb.Stateful;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import session.*;

/**
 *
 * @author dtic
 */
//@Stateful
@Named(value = "userMBean")
@SessionScoped
public class UserMBean implements Serializable {
    private String message="";
    public UserMBean(){this.message="";}
    @EJB
    private ClientFacade clientFacade;
    private Client client=new Client();
    

    public void setClient(Client client) {
        this.client = client;
    }

    public Client getClient() {
        return client;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    public String authentifier(){
        this.message="";
        String email=this.client.getEmail();
        String pwd=this.client.getPassword();
        Client c=this.clientFacade.getClientByEmailPwd(email, pwd);
        if(c!=null){
            this.client=c;
            return "home";
        }
        else{
            this.message="User or Password est incorrect.";
            return null;
        }
    }

  public String logout(){
       this.client=null;
      return "index";
  }

}
