package es.ficonlan.web.backend.jersey.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;

import es.ficonlan.web.backend.jersey.util.ApplicationContextProvider;
import es.ficonlan.web.backend.model.emailservice.EmailService;
import es.ficonlan.web.backend.model.emailtemplate.EmailTemplate;
import es.ficonlan.web.backend.model.util.exceptions.ServiceException;

/**
 * @author Miguel Ángel Castillo Bellagona
 */
@Path("emailTemplate")
public class EmailTemplateResource {

	@Autowired
	private EmailService emailService;
	
	public EmailTemplateResource(){
		this.emailService = ApplicationContextProvider.getApplicationContext().getBean(EmailService.class);
	}
	
	@POST
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces(MediaType.APPLICATION_JSON)
	public EmailTemplate createEmailTemplate(@HeaderParam("sessionId") String sessionId, EmailTemplate emailTemplate) throws ServiceException {
		return emailService.createEmailTemplate(sessionId, emailTemplate);
	}
	
	@Path("/{emailTemplateId}")
	@PUT
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces(MediaType.APPLICATION_JSON)
	public EmailTemplate changeEmailTemplate(@HeaderParam("sessionId") String sessionId, @PathParam("emailTemplateId") int emailTemplateId, EmailTemplate emailTemplate) throws ServiceException {
		return emailService.changeEmailTemplate(sessionId, emailTemplateId, emailTemplate);
	}
	
	@GET
	@Produces({MediaType.APPLICATION_JSON})
	public List<EmailTemplate> getAllEmailTemplate(@HeaderParam("sessionId") String sessionId) throws ServiceException {
		return emailService.getAllEmailTemplate(sessionId);
	}
	
	@Path("/{emailTemplateId}")
	@DELETE
	public void removeEmailTemplate(@HeaderParam("sessionId") String sessionId, @PathParam("emailTemplateId") int emailTemplateId) throws ServiceException {
		emailService.removeEmailTemplate(sessionId, emailTemplateId);
	}
}