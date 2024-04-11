/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */

import jakarta.inject.Named;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import java.io.Serializable;
import java.util.List;
import mg.franco.francotp4banque.entities.CompteBancaire;
import mg.franco.francotp4banque.services.GestionnaireCompte;

/**
 *
 * @author Franco
 */
@Named(value = "listeComptes")
@ViewScoped
public class ListeComptes implements Serializable {
    
    private List<CompteBancaire> listCompteBancaires ;
    
    @Inject
    private GestionnaireCompte gestionnaireCompte ;
    
    /**
     * Creates a new instance of ListeComptes
     */
    public ListeComptes() {
    }
    
    public List<CompteBancaire> getAllComptes()
    {
        if( listCompteBancaires == null )
        {
            listCompteBancaires = gestionnaireCompte.getAllComptes();
        }
        return listCompteBancaires ;
    }
    
}
