/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedbeans;

import entities.Client;
import entities.Compte;
import entities.Operation;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import session.ClientFacade;
import session.CompteFacade;
import session.OperationFacade;

/**
 *
 * @author dtic
 */
@Named(value = "operationMBean")
@SessionScoped
public class OperationMBean implements Serializable {
    private UIInput composantSerch;
    private String search;

   
    private UIInput composantSerchCompte;
    private String searchCompte;
    private String message="";

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    

    @EJB
    private CompteFacade compteFacade;
    private Compte compte;
    private Compte compteTwo;

    public Compte getCompteTwo() {
        return compteTwo;
    }

    public void setCompteTwo(Compte compteTwo) {
        this.compteTwo = compteTwo;
    }

    public Compte getCompte() {
        return compte;
    }
    @EJB
    private ClientFacade clientFacade;
    private Client client;

    public Client getClientTwo() {
        return clientTwo;
    }

    public void setClientTwo(Client clientTwo) {
        this.clientTwo = clientTwo;
    }
    private Client clientTwo;
    
    public Client getClient() {
        return client;
    }
    @EJB
    private OperationFacade operationFacade;
    
     private Operation op=new Operation();
     private List<Operation> operations;
     private List<Operation> OperationParCompte;

     
   public OperationMBean() {
       this.message="";
    }


    public List<Operation> getOperations() {
        return operationFacade.findAll();
    }

    public void setOp(Operation op) {
        this.op = op;
    }

    public Operation getOp() {
        return op;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public UIInput getComposantSerch() {
        return composantSerch;
    }

    public void setComposantSerch(UIInput composantSerch) {
        this.composantSerch = composantSerch;
    }
    
    
    public UIInput getComposantSerchCompte() {
        return composantSerchCompte;
    }

    public void setComposantSerchCompte(UIInput composantSerchCompte) {
        this.composantSerchCompte = composantSerchCompte;
    }

    public String getSearchCompte() {
        return searchCompte;
    }

    public void setSearchCompte(String searchCompte) {
        this.searchCompte = searchCompte;
    }
    public String add(){
        this.op.setCompteOwner(this.compte);
        
        Long compteId=this.op.getCompteOwner().getId();
        double newMontant=this.op.getMontant();
        double oldMontant=this.compte.getSold();
       
        if(this.op.getType_operation()==Operation.TypeOperation.DEPOT){
            this.operationFacade.create(this.op);
            this.compteFacade.depot(compteId,newMontant);
        }
        if(this.op.getType_operation()==Operation.TypeOperation.RETRAIT){
            if(newMontant> oldMontant){
                    this.message="Vous ne pouvez pas vider votre compte.";
            }else{
                this.operationFacade.create(this.op);
                this.compteFacade.debit(compteId,newMontant);
            }
           
        }
        if(this.op.getType_operation()==Operation.TypeOperation.VIREMENT){
            System.out.println((newMontant-oldMontant)+" < 100   "+this.op.getMontant()+" > "+oldMontant);
               if(newMontant > oldMontant){
                    this.message="Vous ne pouvez pas vider votre compte.";
               }else{
                System.out.println(this.op.getCompteVirement()+" ggg "+this.compteTwo+" / "+this.op.getCompteOwner());
                this.op.setCompteVirement(compteTwo);
                Long compteVirementId=this.compteTwo.getId();
                this.operationFacade.create(this.op);
                this.compteFacade.depot(compteVirementId,newMontant);
                this.compteFacade.debit(compteId,newMontant);
                
            }
           
        }        
        this.op =new Operation();
        this.search=this.compte.getNumero();
        this.searchBy();
        if(client.getRole()==new Client().getRole().AGENT){
            return "/Operation/index";
        }else{
            return "/Operation/succes";
         }
        


    }
    
    public String depot(Compte compte){
        this.op.setMontant(0.0);
        this.compte=compte;
        this.op.setType_operation(Operation.TypeOperation.DEPOT);
        return "/Operation/transaction";
    }
    
    public String retait(Compte compte){
        this.op.setMontant(0.0);
        this.compte=compte;
        this.op.setType_operation(Operation.TypeOperation.RETRAIT);
        return "/Operation/transaction";
    }   
    
    public String virement(Compte compte){
        this.op.setMontant(0.0);
        this.message="";
        this.compte=compte;
        this.op.setType_operation(Operation.TypeOperation.VIREMENT);
        return "/Operation/virement";
    }  
    /**
     * Creates a new instance of OperationMBean
     * @return 
     */
   

    
    private final Converter compteConverter = new Converter() {
        @Override
        public Object getAsObject(FacesContext context, UIComponent component, String value) {
            Long id = Long.valueOf(value);    
            return (Compte)compteFacade.find(id);
        }

        @Override
        public String getAsString(FacesContext context, UIComponent component, Object value) {
            Compte compt = (Compte) value;
            return compt.getId().toString();
        }
  };

    public Converter getCompteConverter() {
        return compteConverter;
    }


    
    public void searchBy(){
            this.compte=compteFacade.getCompteByNumber(this.search);
            Long id=this.compte.getOwner().getId();
            this.client=clientFacade.getClientIdFromStringId(id);

    }
    
    public void searchCompteVirment(){
            this.message="";
            this.compteTwo=compteFacade.getCompteByNumber(this.searchCompte);
            if(this.compteTwo!=null){
                Long id=this.compteTwo.getOwner().getId();
                this.clientTwo=clientFacade.getClientIdFromStringId(id);
            }else{
                this.clientTwo=null;
                this.message="Ce compte n'existe pas.";
            }
    }
    
    
    //Recherche un ensemble d'operation par numero de compte
    public List<Operation> getOperationParCompte() {
        return OperationParCompte;
    }
    public void ListOperationByCompte(Long compte){
       this.OperationParCompte=operationFacade.getOperationByCompte(compte);
   }
    
   //redirection vers la page detail operation par compte
    public String show(Long idcompte){
       this.ListOperationByCompte(idcompte);
       return "/Operation/showDetailOperation";
    }
}
