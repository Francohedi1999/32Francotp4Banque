/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mg.franco.francotp4banque.services;

import jakarta.annotation.sql.DataSourceDefinition;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import java.util.List;
import mg.franco.francotp4banque.entities.CompteBancaire;

/**
 *
 * @author Franco
 */
@DataSourceDefinition(
    className="com.mysql.cj.jdbc.MysqlDataSource",
    name="java:app/jdbc/banque",
    serverName="localhost",
    portNumber=3306,
    user="root",
    password="0000", 
    databaseName="banque",
    properties = {
      "useSSL=false",
      "allowPublicKeyRetrieval=true",
      "driverClass=com.mysql.cj.jdbc.Driver"
    }
)

@ApplicationScoped
public class GestionnaireCompte 
{
    @PersistenceContext(unitName = "banquePU")
    private EntityManager em ;
    
    @Transactional
    public CompteBancaire update(CompteBancaire compteBancaire) 
    {
        return em.merge(compteBancaire);
    }
    
    @Transactional
    public void transferer(CompteBancaire source, CompteBancaire destination, int montant) 
    {
        source.retirer(montant);
        destination.deposer(montant);
        update(source);
        update(destination);
    }
    
    public CompteBancaire findById( Long idCompteBancaire )
    {
        return em.find( CompteBancaire.class, idCompteBancaire ) ;
    }
    
    public List<CompteBancaire> getAllComptes()
    {
        TypedQuery<CompteBancaire> query = em.createQuery( "SELECT c FROM CompteBancaire c" , CompteBancaire.class );
        return query.getResultList();
    }
    
    public Long nbComptes()
    {
        Query query = em.createNamedQuery("CompteBancaire.countAll") ;
        return (Long)query.getSingleResult() ;
    }
    
    @Transactional
    public void create( CompteBancaire compteBancaire )
    {
        em.persist( compteBancaire );
    }
    
    @Transactional
    public void delete( CompteBancaire compteBancaire )
    {
        CompteBancaire compte = em.merge(compteBancaire) ;
        em.remove( compte );
    }
}
