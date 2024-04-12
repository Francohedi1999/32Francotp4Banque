/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package mg.franco.francotp4banque.jsf;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.validation.constraints.PositiveOrZero;
import mg.franco.francotp4banque.entities.CompteBancaire;
import mg.franco.francotp4banque.services.GestionnaireCompte;
import mg.franco.francotp4banque.utils.UtilMessage;

/**
 *
 * @author Franco
 */
@Named(value = "ajoutCompte")
@ApplicationScoped
public class AjoutCompte {

    private String nom ;
    
    @PositiveOrZero
    private int solde ;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getSolde() {
        return solde;
    }

    public void setSolde(int solde) {
        this.solde = solde;
    }
    
    @Inject
    private GestionnaireCompte gestionnaireCompte ;
    /**
     * Creates a new instance of AjoutCompte
     */
    public AjoutCompte() {
    }
    
    public String ajout()
    {
        CompteBancaire compteBancaire = new CompteBancaire( nom , solde );
        gestionnaireCompte.create(compteBancaire);
        UtilMessage.addFlashInfoMessage(    "Le compte de " + 
                                            nom + 
                                            " a été bien créé avec un solde inital de " + 
                                            solde );
        return "listeComptes?faces-redirect=true" ;
    }
}
