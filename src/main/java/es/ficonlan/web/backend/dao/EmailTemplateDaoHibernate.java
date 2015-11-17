package es.ficonlan.web.backend.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import es.ficonlan.web.backend.entities.EmailTemplate;

/**
 * @author Miguel Ángel Castillo Bellagona
 */
@Repository("emailTemplateDao")
public class EmailTemplateDaoHibernate extends GenericDaoHibernate<EmailTemplate, Integer> implements EmailTemplateDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<EmailTemplate> getAllEmailTemplate() {
		return getSession().createQuery( "SELECT e " +
				 "FROM EmailTemplate e ").list();
	}
	
	@Override
	public EmailTemplate findByName(String name) {
		return (EmailTemplate) getSession()
				.createQuery("SELECT e FROM EmailTemplate e WHERE e.name = :name")
				.setParameter("name", name).uniqueResult();
	}

}
