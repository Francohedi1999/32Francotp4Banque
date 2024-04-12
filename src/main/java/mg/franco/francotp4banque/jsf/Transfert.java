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
import mg.franco.francotp4banque.utils.UtilMessage ;

/**
 *
 * @author Franco
 */
@Named(value = "transfert")
@ViewScoped
public class Transfert implements Serializable {

    private Long idSource ;    
    private Long idDestination ;
    private int somme ;    

    public Long getIdSource() {
        return idSource;
    }

    public void setIdSource(Long idSource) {
        this.idSource = idSource;
    }

    public Long getIdDestination() {
        return idDestination;
    }

    public void setIdDestination(Long idDestination) {
        this.idDestination = idDestination;
    }

    public int getSomme() {
        return somme;
    }

    public void setSomme(int somme) {
        this.somme = somme;
    }
    
    @Inject
    private GestionnaireCompte gestionnaireCompte ;

    /**
     * Creates a new instance of Transfert
     */
    public Transfert() {
    }
    
    public String transfert()
    {
        boolean erreur = false;
        
        CompteBancaire source = gestionnaireCompte.findById(idSource) ;        
        CompteBancaire destination = gestionnaireCompte.findById(idDestination) ;
        
        if( source == null )
        {
            UtilMessage.messageErreur(  "Aucun compte (source) trouvé pour l'ID : " + idSource , 
                                        "Aucun compte (source) trouvé pour l'ID : " + idSource , 
                                        "form:idSource");
            erreur = true ;
        }
        else
        {
            if( source.getSolde() < somme )
            {
                UtilMessage.messageErreur(  "Solde du compte (source) insuffisant pour l'ID : " + idSource , 
                                            "Solde du compte (source) insuffisant pour l'ID : " + idSource , 
                                            "form:somme");
                erreur = true;
            }
        }
        
        if( destination == null )
        {
            UtilMessage.messageErreur(  "Aucun compte (destination) trouvé pour l'ID : " + idDestination , 
                                        "Aucun compte (destination) trouvé pour l'ID : " + idDestination , 
                                        "form:idDestination");
            erreur = true ;
        }
        
        if( erreur )
        {
            return null ;
        }
        
        gestionnaireCompte.transferer( source , destination , somme);
        
        UtilMessage.addFlashInfoMessage(    "Le montant de " + 
                                            somme + 
                                            " a été bien transféré depuis le compte (source) ID : " +
                                            source.getId() + 
                                            " vers le compte (destination) ID : " + 
                                            destination.getId() );

        return "listeComptes?faces-redirect=true" ;
    }
    
}
