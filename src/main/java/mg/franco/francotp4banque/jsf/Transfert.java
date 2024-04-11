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
        CompteBancaire source = gestionnaireCompte.findById(idSource) ;        
        CompteBancaire destination = gestionnaireCompte.findById(idDestination) ;
        
        gestionnaireCompte.transferer( source , destination , somme);

        return "listeComptes?faces-redirect=true" ;
    }
    
}
