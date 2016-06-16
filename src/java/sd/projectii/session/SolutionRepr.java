/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sd.projectii.session;

import sd.projectii.persistencia.entidade.Solution;

/**
 *
 * @author User
 */
public class SolutionRepr {
	int idsolution;
	int iduserdes;
	int iduserorg;
	int idexer;
	String answer;
	String tag;

	public SolutionRepr(int idsolution, String tag, int iduserdes, int iduserorg, String answer, int idexer) {
		this.idsolution = idsolution;
		this.idexer = idexer;
		this.answer = answer;
		this.iduserdes = iduserdes;
		this.iduserorg = iduserorg;
		this.tag = tag;

	}
	public SolutionRepr(Solution s) {
		this.idsolution = s.getIdsolution();
		this.iduserdes = s.getIduserdes();
		this.iduserorg = s.getIduserorg();
		this.tag = s.getTag();
		this.answer = s.getAnswer();
		this.idexer = s.getIdexer().getIdexercise();
	}
}
