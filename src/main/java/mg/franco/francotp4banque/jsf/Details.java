/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package mg.franco.francotp4banque.jsf;

import jakarta.inject.Named;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import java.io.Serializable;
import mg.franco.francotp4banque.entities.CompteBancaire;
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
    }
}
