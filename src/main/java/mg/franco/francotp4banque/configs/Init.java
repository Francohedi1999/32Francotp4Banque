/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package mg.franco.francotp4banque.configs;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.Initialized;
import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.ServletContext;
import jakarta.transaction.Transactional;
import mg.franco.francotp4banque.entities.CompteBancaire;
import mg.franco.francotp4banque.services.GestionnaireCompte;

/**
 *
 * @author Franco
 */
@Named
@ApplicationScoped
public class Init 
{

    /**
     * Creates a new instance of Init
     */
    
    @Inject
    private GestionnaireCompte gestionnaireCompte ;
    
    public Init() {
    }
    
    @Transactional
    @PostConstruct
    public void init(   @Observes
                        @Initialized(ApplicationScoped.class) ServletContext context )
    {
        if( gestionnaireCompte.nbComptes() == 0 )
        {
            gestionnaireCompte.creerCompte(new CompteBancaire("John Lennon", 150000));          
            gestionnaireCompte.creerCompte(new CompteBancaire("Paul McCartney", 950000));  
            gestionnaireCompte.creerCompte(new CompteBancaire("Ringo Starr", 20000));  
            gestionnaireCompte.creerCompte(new CompteBancaire("Georges Harrisson", 100000));
        }

    }
    
}
