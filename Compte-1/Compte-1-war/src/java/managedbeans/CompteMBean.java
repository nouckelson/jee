/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbeans;

import entities.*;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import session.ClientFacade;
import session.CompteFacade;

/**
 *
 * @author dtic
 */
@Named(value = "compteMBean")
@SessionScoped
public class CompteMBean implements Serializable {
    @EJB
    private ClientFacade owner;
    @EJB
    private CompteFacade compteFacade;
    private Compte cp=new Compte();
    private List<Compte> comptes;

    public List<Compte> getComptes() {
        return compteFacade.findAll();
    }

    /**
     * Creates a new instance of CompteMBean
     */
    public CompteMBean() {
    }
    public Compte getCp() {
        return cp;
    }

    public void setCp(Compte cp) {
        this.cp = cp;
    }

    
    //Lister les comptes
    public List<Compte> showComptes(){
        return compteFacade.findAll();
    }
//     public List<Compte> Comptes(){
//        return compteFacade.findAllCompteClient();
//    }  
    public String add(){
       this.compteFacade.create(this.cp);
       this.cp=new Compte();
        return "/Compte/index";
    }
    public void delete(Compte cp){
        this.compteFacade.remove(this.cp);
        this.cp=cp;
//        this.showComptes();
    }
     public String edit(Compte cp){
        this.cp=cp;
        return "edit";
    }
    
    public String edit(){
        this.compteFacade.edit(this.cp);
        return "index";
    }
    
    
    
    // Pour remplir la liste des discount codes
    public List<Client> getAllClientCodes() {
        return owner.findAll();
    }
    
    
    //recherche un client
    public String findClient(Client owner){
        Client cli=this.owner.getClientIdFromStringId(owner.getId());
        return cli.getNom()+" "+cli.getPrenom();
    }
    
    
    
    //Recherche un compte par client
    public List<Compte> showComptesByClient(Long idclient){
        return compteFacade.getCompteByOwner(idclient);
    }
    //Le connverteur
    public Converter getClientCodeConverter() {
        return clientCodeConverter;
    }
    
    
    private Converter clientCodeConverter = new Converter() {
        @Override
        public Object getAsObject(FacesContext context, UIComponent component, String value) {
            Long id = Long.valueOf(value);      
            return (Client)owner.find(id);
        }

        @Override
        public String getAsString(FacesContext context, UIComponent component, Object value) {
            Client dc = (Client) value;
            return dc.getId().toString();
        }
  };
}
