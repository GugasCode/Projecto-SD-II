/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sd.projectii.beans;

import java.io.Serializable;
import javax.annotation.ManagedBean;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedProperty;



/**
 *
 * @author ng
 */
@ManagedBean
@SessionScoped
public class InjBean implements Serializable {

	@ManagedProperty(value="#{exercise}")
	private ExerciseBean e;

	//must povide the setter method
	public void setMessageBean(ExerciseBean e) {
		this.e = e;
	}

	//...
}