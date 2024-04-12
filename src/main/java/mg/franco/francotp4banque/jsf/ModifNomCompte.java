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
import mg.franco.francotp4banque.utils.UtilMessage;

/**
 *
 * @author Franco
 */
@Named(value = "modifNomCompte")
@ViewScoped
public class ModifNomCompte implements Serializable {

    @Inject
    private GestionnaireCompte gestionnaireCompte ;
    
    private Long idCompte ;
    private CompteBancaire compteBancaire ;
    private String nouveauNom ;
    private String nomActuel ;

    public String getNomActuel() {
        return nomActuel;
    }

    public void setNomActuel(String nomActuel) {
        this.nomActuel = nomActuel;
    }

    public String getNouveauNom() {
        return nouveauNom;
    }

    public void setNouveauNom(String nouveauNom) {
        this.nouveauNom = nouveauNom;
    }

    public Long getIdCompte() {
        return idCompte;
    }

    public void setIdCompte(Long idCompte) {
        this.idCompte = idCompte;
    }

    public CompteBancaire getCompteBancaire() {
        return compteBancaire;
    }

    public void setCompteBancaire(CompteBancaire compteBancaire) {
        this.compteBancaire = compteBancaire;
    }
    
    public String modifNom()
    {
        compteBancaire.setNom(nouveauNom);
        gestionnaireCompte.update(compteBancaire);
        UtilMessage.addFlashInfoMessage(    "Le nom du possesseur du compte ID : " + 
                                                compteBancaire.getId() + 
                                                " " +
                                                nomActuel +
                                                " a été changé en " + 
                                                nouveauNom );
        return "listeComptes?faces-redirect=true" ;
    }
    /**
     * Creates a new instance of ModifNomCompte
     */
    
    public void loadCompte()
    {
        compteBancaire = gestionnaireCompte.findById(idCompte) ;
        nomActuel = compteBancaire.getNom() ;
    }
    
    public ModifNomCompte() 
    {
        
    }
    
}
