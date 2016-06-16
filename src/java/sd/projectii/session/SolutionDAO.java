/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sd.projectii.session;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import sd.projectii.persistencia.entidade.Exercise;
import sd.projectii.persistencia.entidade.Solution;

/**
 *
 * @author ng
 */
@Stateless
public class SolutionDAO extends AbstractDAO<Solution> {

    
    @EJB
    private ExerciseDAO exerciseDAO;
    
    @EJB
    private SolutionDAO solutionDAO;
        
    @PersistenceContext(unitName = "projectiiPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public SolutionDAO() {
        super(Solution.class);
    }
    
    public void create(Solution entity) {
        em.persist(entity);
    }
    
     public void deleteSolution(int idsolution) {

        String jpql = "DELETE FROM Exercise obj WHERE obj.idsolution = :idsolution";

        Query query = em.createQuery(jpql).setParameter("idsolution", idsolution);

        query.executeUpdate();
    }

    public void getWrongSolutions(int idexercise) {

        String jpql = "SELECT s FROM Solution s WHERE s.idexer = :idexercise AND s.tag = :tag";

        Query query = em.createQuery(jpql).setParameter("idexercise", idexercise).setParameter("tag", "incorrect");

        List<Solution> x = query.getResultList();
        //for (int i=0; i<x.size(); i++) {
        //    deleteSolution(x.get(i).getIdsolution());
        /*
        Iterating through the list of Solutions that are marked as wrong and
        deleting them all one by one.
         */
        for (Solution a : x) {
            deleteSolution(a.getIdsolution());
        }

    }

    public List<Solution> listAllSol() {

        String jpql = "SELECT obj FROM Solution obj ";
        Query query = em.createQuery(jpql);

        try {
            List<Solution> notifs = query.getResultList();
//	    List<SolutionRepr> results = new ArrayList<SolutionRepr>();
//	    for (Solution s: notifs) {
//		    SolutionRepr aux = new SolutionRepr(s);
//		    results.add(aux);
//	    }
//	    System.out.println("Notifications first element");
	    //System.out.println(results.get(0));
//	    System.out.println("Idexer:");

 //           return results;
	    return notifs;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
        public String createSolution(String id, String sol){
        	sd.projectii.persistencia.entidade.Solution u = new sd.projectii.persistencia.entidade.Solution();
        
        
        int idc = 0;
        if (id != null && !id.isEmpty()) {
            idc = Integer.parseInt(id.trim());
        }
        	sd.projectii.persistencia.entidade.Exercise exe = exerciseDAO.findByID(idc);
        
        u.setTag("validate");
        u.setIdexer(exe);
        u.setIdsolution(0);
        u.setAnswer(sol);

        EntityManager em = Persistence.createEntityManagerFactory("projectiiPU").createEntityManager(); //mostrar os dados
        
        create(u);
        exerciseDAO.UpdateTotalSolution(idc);
        return "/index?faces-redirect=true";
    }
      @Transactional
    public void getSolution(int idexer){
            String jpql = "SELECT obj FROM Solution obj WHERE obj.idexer = :idexer";
	    Query query = em.createQuery(jpql);
                query.setParameter("idexer", idexer);

      
    }
    
}