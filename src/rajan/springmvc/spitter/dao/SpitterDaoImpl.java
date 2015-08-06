package rajan.springmvc.spitter.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import rajan.springmvc.spitter.persistence.Spitter;
import rajan.springmvc.spitter.dao.SpitterDao;



@Repository("spitterDao")
public class SpitterDaoImpl implements SpitterDao {
  private SessionFactory sessionFactory;

  @Autowired
  public SpitterDaoImpl(SessionFactory sessionFactory) {//<co id="co_injectSessionFactory"/>
	  System.out.println("Inside SF");
    this.sessionFactory = sessionFactory;
  }
 
  private Session currentSession() {
    return sessionFactory.getCurrentSession();//<co id="co_getCurrentSession"/>
  }
  
  public void addSpitter(Spitter spitter) {
	  System.out.println("Argument Received in Dao are: "+spitter.getFullName()+spitter.getEmail()+spitter.getEmail());
    currentSession().save(spitter);//<co id="co_useSession"/>
  }

  public Spitter getSpitterById(long id) {
    return (Spitter) currentSession().get(Spitter.class, id);//<co id="co_useSession"/>
  }

  public void saveSpitter(Spitter spitter) {
    currentSession().update(spitter);//<co id="co_useSession"/>
  }
  //<end id="java_contextualHibernateDao"/> 
  
  public Spitter getSpitterByUsername(String username) {
    Session session = currentSession();
    Query query = session.createQuery("FROM Spitter WHERE username = :username");
    query.setParameter("username", username);
    Spitter spitter = (Spitter) query.uniqueResult();
    return spitter;
  }

  
  public List<Spitter> findAllSpitters() {
    // TODO Auto-generated method stub
    return null;
  }
}