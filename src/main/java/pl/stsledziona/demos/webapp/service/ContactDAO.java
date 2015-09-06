package pl.stsledziona.demos.webapp.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import pl.stsledziona.demos.webapp.domain.Contact;

@Stateless
public class ContactDAO {

	@PersistenceContext
	private EntityManager em;

	public boolean isUnique(Contact contact) {
		try {
			List<Contact> c = em.createNamedQuery("uniqueEmail", Contact.class).setParameter("email", contact.getEmail()).getResultList();
			if(c.isEmpty()){
				return true;
			}else{
				return false;
			}
		}catch(Exception e){
			return false;
		}
	}

	public void addContact(Contact contact) {
		contact.setIdContact(null);
		em.persist(contact);
	}

	public void editContact(Contact contact) {
		em.merge(contact);
	}

	public void deleteContact(Contact contact) {
		contact = em.find(Contact.class, contact.getIdContact());
		em.remove(contact);
	}

	public Contact getContact(Contact contact) {
		return em.find(Contact.class, contact.getIdContact());
	}

	@SuppressWarnings("unchecked")
	public List<Contact> getAllContacts() {
		return em.createNamedQuery("Contact.getAll").getResultList();
	}

	public Contact getContactById(Long contactId) {
		return em.find(Contact.class, contactId);
	}

}
