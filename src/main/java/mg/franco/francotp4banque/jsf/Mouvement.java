/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package mg.franco.francotp4banque.jsf;

import jakarta.inject.Named;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.persistence.OptimisticLockException;
import java.io.Serializable;
import mg.franco.francotp4banque.entities.CompteBancaire;
import mg.franco.francotp4banque.services.GestionnaireCompte;
import mg.franco.francotp4banque.utils.UtilMessage;

/**
 *
 * @author Franco
 */
@Named(value = "mouvement")
@ViewScoped
public class Mouvement implements Serializable {

    /**
     * Creates a new instance of Mouvement
     */
    
    @Inject
    private GestionnaireCompte gestionnaireCompte ;
    
    private Long idCompte ;
    private CompteBancaire compteBancaire ;
    private int somme ;  // solde pour le mouvement
    private String type ; // type de mouvement 

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

    public int getSomme() {
        return somme;
    }

    public void setSomme(int somme) {
        this.somme = somme;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    
    public Mouvement() 
    {
        
    }
    
    public void loadCompte()
    {
        compteBancaire = gestionnaireCompte.findById(idCompte) ;
    }
    
    public String faireMouvement()
    {
        try 
        {
            if( type.equals("depot") )
            {
                compteBancaire.deposer(somme);
                gestionnaireCompte.update(compteBancaire) ;
                UtilMessage.addFlashInfoMessage(    "Un dépôt de " + 
                                                    somme + 
                                                    " a été effectué sur le compte (source) ID : " +
                                                    compteBancaire.getId() );
                return "listeComptes?faces-redirect=true" ;
            }
            else if( type.equals("retrait"))
            {
                if( somme > compteBancaire.getSolde() )
                {
                    UtilMessage.messageErreur(  "Solde du compte insuffisant pour le retrait" , 
                                                "Solde du compte insuffisant pour le retrait" , 
                                                "form:somme");
                }
                else
                {                
                    compteBancaire.retirer(somme);
                    gestionnaireCompte.update(compteBancaire) ;
                    UtilMessage.addFlashInfoMessage(    "Un retrait de " + 
                                                        somme + 
                                                        " a été effectué sur le compte (source) ID : " +
                                                        compteBancaire.getId() );
                    return "listeComptes?faces-redirect=true" ;
                }
            }
            else
            {
                UtilMessage.messageErreur(  "Aucun type de mouvement sélectionné" , 
                                            "Aucun type de mouvement sélectionné" , 
                                            "form:type");
            }
        } 
        catch (OptimisticLockException  e) 
        {
            UtilMessage.messageErreur(  "Le compte de " + 
                                        compteBancaire.getNom() + 
                                        " ID: " +
                                        compteBancaire.getId() + 
                                        " a été modifié ou supprimé par un autre utilisateur !");
            return null;
        }        
        return "listeComptes?faces-redirect=true" ;
    }
}
