/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mg.franco.francotp4banque.services;

import jakarta.annotation.sql.DataSourceDefinition;
import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import java.util.List;
import mg.franco.francotp4banque.entities.CompteBancaire;

/**
 *
 * @author Franco
 */
@DataSourceDefinition (
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

@RequestScoped
public class GestionnaireCompte 
{
    @PersistenceContext(unitName = "banquePU")
    private EntityManager em ;
    
    @Transactional
    public void persist( CompteBancaire compteBancaire )
    {
        em.persist( compteBancaire );
    }
    
    public List<CompteBancaire> getAllComptes()
    {
        String sql = "select c from compte as c" ;
        TypedQuery<CompteBancaire> query = em.createQuery( sql , CompteBancaire.class );
        return query.getResultList();
    }
}
