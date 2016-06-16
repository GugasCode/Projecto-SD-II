/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sd.projectii.session;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transaction;
import javax.transaction.Transactional;
import sd.projectii.persistencia.entidade.Exercise;

/**
 *
 * @author ng
 * EJB CODING BUSINESS LOGIC onde tao os DAOs
 * 
 * 
 */
@Stateless
public class ExerciseDAO extends AbstractDAO<Exercise> {

    @PersistenceContext(unitName = "projectiiPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ExerciseDAO() {
        super(Exercise.class);
    }
    

    public void deleteExercise(int idexercise) {

        String jpql = "DELETE FROM Exercise obj WHERE obj.idexercise = :idexercise";

        Query query = em.createQuery(jpql).setParameter("idexercise", idexercise);

        query.executeUpdate();
   

    }

    public List<Exercise> ListAllOpen() {
        //    List query= findAll();
        String tag = "CLOSE";
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

    public List<Exercise> ConsultUCIDOClose(String uc) {
        String jpql = "SELECT obj FROM Exercise obj WHERE obj.tag = :tag AND obj.uc = :uc";
        Query query = em.createQuery(jpql);
        query.setParameter("tag", "CLOSE");
        query.setParameter("uc", uc);

        try {
            List<Exercise> notifs = query.getResultList();

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

    public void CloseExercise(int idexercise) {
        String tag = "CLOSE";
        String jpql = "UPDATE Exercise e SET e.tag = :tag WHERE e.idexercise = :idexercise";
        Query query = em.createQuery(jpql);
        query.setParameter("tag", tag);
        query.setParameter("idexercise", idexercise);
        query.executeUpdate();
    }

    public void ClassifyExercise(int idsolution, String tag) {

        String jpql = "UPDATE Solution e SET e.tag = :tag WHERE e.idsolution = :idsolution";
        Query query = em.createQuery(jpql);
        query.setParameter("tag", tag);
        query.setParameter("idsolution", idsolution);
        query.executeUpdate();
    }

    public List<Exercise> ConsultOpenExe(String uc) {

        String jpql = "SELECT obj FROM Exercise obj WHERE obj.tag = :tag AND obj.uc = :uc";
        Query query = em.createQuery(jpql);
        query.setParameter("tag", "OPEN");
        query.setParameter("uc", uc);

        try {
            List<Exercise> notifs = query.getResultList();

            return notifs;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
}
