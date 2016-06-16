/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sd.projectii.beans;

import static com.sun.glass.ui.Cursor.setVisible;
import java.awt.event.ActionEvent;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import sd.projectii.persistencia.entidade.Exercise;
import sd.projectii.persistencia.entidade.Solution;
import sd.projectii.session.ExerciseDAO;

/**
 *
 * @author ng
 */

@Named("exercise")
@RequestScoped
public class ExerciseBean  implements Serializable{

    private 
    @EJB
    ExerciseDAO exerciseDAO; ///AccountFacade facade

    /**
     * Creates a new instance of ExerciseBean
     */
    
    @PersistenceContext(unitName = "projectiiPU")
    private EntityManager em;


    //public ExerciseDAO getExerciseDAO() {
    //    return exerciseDAO;
    //}

    //public void setExerciseDAO(ExerciseDAO exerciseDAO) {
    //    this.exerciseDAO = exerciseDAO;
    //}

    
     private String uc; 

    public String getUc() {
        return uc;
    }

    public void setUc(String uc) {
        this.uc = uc;
    }
     
    
//        private Exercise exerc; //Account account
//        
//    public Exercise getExerc() {
//        return exerc;
//    }
//
//    public void setExerc(Exercise exerc) {
//        this.exerc = exerc;
//    }

    public void create(Exercise entity) {
        em.persist(entity);
    }
    
    public String createExercise(String uc, String title, String question){
        	sd.projectii.persistencia.entidade.Exercise u = new sd.projectii.persistencia.entidade.Exercise();
//        u.setIdexercise(0);
        u.setQuestion(question);
        u.setTitle(title);
        u.setUc(uc);
        u.setTag("OPEN");
        u.setTsolution(0);

        EntityManager em = Persistence.createEntityManagerFactory("projectiiPU").createEntityManager(); //mostrar os dados
        exerciseDAO.create(u);
        
        return "/index?faces-redirect=true";
    }

  @Transactional
   public void deleteExercise(int idexercise) {

       
        String jpql = "DELETE FROM Exercise obj WHERE obj.idexercise = :idexercise";

        Query query = em.createQuery(jpql).setParameter("idexercise", idexercise);
       query.executeUpdate();

   }

    public List<Exercise> listAllOpen() {
        //    List query= findAll();
        String tag = "OPEN";
        int tsolution = 0;
        String jpql = "SELECT obj FROM Exercise obj WHERE obj.tag = :tag AND obj.tsolution = :tsolution";
        Query query = em.createQuery(jpql);
        query.setParameter("tag", tag);
        query.setParameter("tsolution", tsolution);

        try {
            List<Exercise> notifs = query.getResultList();

            return notifs;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    
        public List<Exercise> listAll() {


        String jpql = "SELECT obj FROM Exercise obj ";
        Query query = em.createQuery(jpql);

        try {
            List<Exercise> notifs = query.getResultList();

            return notifs;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
      


    public List<Exercise> ConsultUCID(String uc, int idexercise) {

        String jpql = "SELECT obj FROM Exercise obj WHERE obj.uc = :uc AND obj.idexercise = :idexercise   ";
        Query query = em.createQuery(jpql);
        query.setParameter("uc", uc);
        query.setParameter("idexercise", idexercise);

        try {
            List<Exercise> notifs = query.getResultList();

            return notifs;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void VerifyIF(int idexercise) {

        String jpql = " UPDATE Tag t set t.tsolution = t.tsolution + 1 WHERE t.idexercise = :idexercise ";
        Query query = em.createQuery(jpql);

        query.setParameter("idexercise", idexercise);

    }

    public void UpdateTotalSolution(int idexer) {
        Exercise d = new Exercise(idexer);
        String answers = "SELECT  s FROM Solution s WHERE s.idexer = :idexer";
        Query q = em.createQuery(answers);
        q.setParameter("idexer", d);
        List results = q.getResultList();
        int len = results.size();

        //String jpql = "UPDATE Exercise e SET e.tsolution = (SELECT COUNT(s) FROM Solution s GROUP BY s.idexer HAVING s.indexer = :idexer_ext) WHERE e.idexercise = :idexer_ext";
        String jpql = "UPDATE Exercise e SET e.tsolution = :counter WHERE e.idexercise = :idexer_ext";
        Query query = em.createQuery(jpql);
        query.setParameter("idexer_ext", idexer);
        query.setParameter("counter", len);
        query.executeUpdate();
    }

    public List<Solution> ConsultUCIDOClose(String uc) {
        //String jpql = "SELECT obj FROM Exercise obj WHERE obj.tag = :tag AND obj.uc = :uc";

	String jpql = "SELECT s FROM Solution s JOIN s.idexer i WHERE NOT EXISTS (SELECT e FROM Exercise e WHERE e = i AND e.uc = :uc AND e.tag <> :tag)";
        Query query = em.createQuery(jpql);
        query.setParameter("tag", "CLOSE");
        query.setParameter("uc", uc);

        try {
            List<Solution> notifs = query.getResultList();

            return notifs;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Exercise findByID(int idexercise) {

        String jpql = "SELECT obj FROM Exercise obj WHERE obj.idexercise = :idexercise";

        Query query = em.createQuery(jpql).setParameter("idexercise", idexercise);

        try {
            Exercise e = (Exercise) query.getSingleResult();

            return e;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    @Transactional
    public void CloseExercise(int idexercise) {
        String tag = "CLOSE";
        String jpql = "UPDATE Exercise e SET e.tag = :tag WHERE e.idexercise = :idexercise";
        Query query = em.createQuery(jpql);
        query.setParameter("tag", tag);
        query.setParameter("idexercise", idexercise);
        query.executeUpdate();
    }

    @Transactional
    public void ClassifyExercise(int idsolution, String tag) {

        String jpql = "UPDATE Solution e SET e.tag = :tag WHERE e.idsolution = :idsolution";
        Query query = em.createQuery(jpql);
        query.setParameter("tag", tag);
        query.setParameter("idsolution", idsolution);
        query.executeUpdate();
    }

    public List<Solution> consultOpenExe(String uc) {

        //String jpql = "SELECT obj FROM Exercise obj WHERE obj.tag = :tag AND obj.uc = :uc";
	String jpql = "SELECT s FROM Solution s JOIN s.idexer i WHERE NOT EXISTS (SELECT e FROM Exercise e WHERE e = i AND e.uc = :uc AND e.tag <> :tag)";
        Query query = em.createQuery(jpql);
        query.setParameter("tag", "OPEN");
        query.setParameter("uc", uc);

        try {
            List<Solution> notifs = query.getResultList();

            return notifs;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
        public List<Exercise> consultUC() {

        String jpql = " select q  from Exercise q group by q.uc   ";

        Query query = em.createQuery(jpql);

        try {
            //    List<Exercise> notifs = query.getResultList();
            List<Exercise> notifs = query.getResultList();
            //          return (List<Exercise>) a;
            return notifs;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
        
        private boolean visible = false; // getter/setter

public void getUserList(ActionEvent event)
{
    setVisible(true);

    // Your code
}



}
