package pl.stsledziona.demos.webapp.service;

import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;

import pl.stsledziona.demos.webapp.domain.Contact;

@ManagedBean
public class ContactConverter implements Converter {

	@Inject
	private ContactDAO cd;
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if(value == null){
			return null;
		}
		Long id = Long.parseLong(value);
		return cd.getContactById(id);
		
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if(value == null){
			return null;
		}
		Long id = ((Contact) value).getIdContact();
		return (id != null) ? id.toString() : null;
	}

	
}
