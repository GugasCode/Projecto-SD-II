/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sd.projectii.beans;

import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import sd.projectii.persistencia.entidade.Solution;
import sd.projectii.session.SolutionDAO;

/**
 *
 * @author gugas
 */

@Named(value = "solution")
@RequestScoped

public class SolutionBean {
    
    @EJB
    SolutionDAO solutionDAO; ///AccountFacade facade

    /**
     * Creates a new instance of SolutionBean
     */
    @PersistenceContext(unitName = "projectiiPU")
    private EntityManager em;

    public SolutionBean() {
    }
    public SolutionDAO getSolutionDAO() {
        return solutionDAO;
    }

    public void setSolutionDAO(SolutionDAO solutionDAO) {
        this.solutionDAO = solutionDAO;
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
    
    private Solution sol; //Account account
        
    public Solution getExerc() {
        return sol;
    }

    public void setSol(Solution sol) {
        this.sol = sol;
    }

    @Transactional
    public void ClassifyExercise(int idsolution, String tag) {

        String jpql = "UPDATE Solution e SET e.tag = :tag WHERE e.idsolution = :idsolution";
        Query query = em.createQuery(jpql);
        query.setParameter("tag", tag);
        query.setParameter("idsolution", idsolution);
        query.executeUpdate();
    }
}
