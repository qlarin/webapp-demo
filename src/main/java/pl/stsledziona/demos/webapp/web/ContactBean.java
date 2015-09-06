package pl.stsledziona.demos.webapp.web;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.model.ListDataModel;
import javax.inject.Inject;
import javax.inject.Named;

import pl.stsledziona.demos.webapp.domain.Contact;
import pl.stsledziona.demos.webapp.service.ContactDAO;

@SessionScoped
@Named("contactBean")
public class ContactBean implements Serializable {

	private static final long serialVersionUID = -3909446543482385195L;

	private Contact contact = new Contact();
	private Contact contactToShow = new Contact();
	private UIComponent button;
	private ListDataModel<Contact> contacts = new ListDataModel<Contact>();

	@Inject
	private ContactDAO cd;

	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}

	public String addContact() {
		if(cd.isUnique(contact)){
			cd.addContact(contact);
			return "/contacts?faces-redirect=true";
		}else{
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Duplicated email",
					"Please enter another email");
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(button.getClientId(context), message);
			return null;
		}
		
	}
	
	public String editContact(){
		cd.editContact(contact);
		return "/contacts?faces-redirect=true";
	}

	public String deleteContact(Contact contactToDelete) {
		cd.deleteContact(contactToDelete);
		return "/contacts";
	}

	public String showDetails(Contact contact) {
		setContactToShow(contact);
		return "/secured/contactDetails?faces-redirect=true";
	}

	public ListDataModel<Contact> getAllContacts() {
		contacts.setWrappedData(cd.getAllContacts());
		return contacts;
	}

	public Contact getContactToShow() {
		return contactToShow;
	}

	public void setContactToShow(Contact contactToShow) {
		this.contactToShow = contactToShow;
	}
	
	public void setButton(UIComponent button){
		this.button = button;
	}
	
	public UIComponent getButton(){
		return button;
	}

}
