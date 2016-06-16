/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sd.projectii.session;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import sd.projectii.persistencia.entidade.Uc;

/**
 *
 * @author ng
 */
@Stateless
public class UcDAO extends AbstractDAO<Uc> {

    @PersistenceContext(unitName = "projectiiPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UcDAO() {
        super(Uc.class);
    }
    
}
