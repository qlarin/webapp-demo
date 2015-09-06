package pl.stsledziona.demos.webapp.web;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import pl.stsledziona.demos.webapp.service.LoginDAO;

@ManagedBean(name = "login")
@SessionScoped
public class LoginBean implements Serializable {

	@Inject
	private LoginDAO ld;

	private static final long serialVersionUID = -7384551440178220837L;
	private String username;
	private String password;
	
	private UIComponent loginButton;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String validateInput() {
		if (ld.validate(username, password)) {
			HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext()
					.getSession(false);
			session.setAttribute("username", username);
			return "/loggedin?faces-redirect=true";
		} else {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Invalid input",
					"Please enter valid data");
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(loginButton.getClientId(context), message);
			return "/login";
		}
	}
	
	public void setLoginButton(UIComponent loginButton){
		this.loginButton = loginButton;
	}
	
	public UIComponent getLoginButton(){
		return loginButton;
	}
	
	

	public String logout() {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		session.invalidate();
		return "/loggedout?faces-redirect=true";
	}

}
