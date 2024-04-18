/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package mg.franco.francotp4banque.jsf;

import jakarta.inject.Named;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import java.io.Serializable;
import java.util.List;
import mg.franco.francotp4banque.entities.CompteBancaire;
import mg.franco.francotp4banque.entities.OperationBancaire;
import mg.franco.francotp4banque.services.GestionnaireCompte;

/**
 *
 * @author Franco
 */
@Named(value = "details")
@ViewScoped
public class Details implements Serializable {

    @Inject
    private GestionnaireCompte gestionnaireCompte ;
    
    private Long idCompte ;
    private CompteBancaire compteBancaire ;
    private List<OperationBancaire> operations ;

    public GestionnaireCompte getGestionnaireCompte() {
        return gestionnaireCompte;
    }

    public void setGestionnaireCompte(GestionnaireCompte gestionnaireCompte) {
        this.gestionnaireCompte = gestionnaireCompte;
    }

    public List<OperationBancaire> getOperations() {
        return operations;
    }

    public void setOperations(List<OperationBancaire> operations) {
        this.operations = operations;
    }

    public CompteBancaire getCompteBancaire() 
    {
        return compteBancaire;
    }

    public void setCompteBancaire(CompteBancaire compteBancaire) 
    {
        this.compteBancaire = compteBancaire;
    }

    public Long getIdCompte() 
    {
        return idCompte;
    }

    public void setIdCompte(Long idCompte) 
    {
        this.idCompte = idCompte;
    }   
    
    /**
     * Creates a new instance of Details
     */
    public Details() {
    }
    
    public void loadCompte()
    {
        compteBancaire = gestionnaireCompte.findById(idCompte) ;
        operations = compteBancaire.getOperations() ;
    }
}
