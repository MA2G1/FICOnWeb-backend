package es.ficonlan.web.backend.model.newsitem;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.stereotype.Repository;

import es.ficonlan.web.backend.model.util.dao.GenericDaoHibernate;

/**
 * @author Daniel Gómez Silva
 */
@Repository("newsDao")
public class NewsDaoHibernate extends GenericDaoHibernate<NewsItem, Integer> implements NewsDao {
	
	@SuppressWarnings("unchecked")
	@Override
	public List<NewsItem> getAllNewsItem() {
		return getSession().createQuery(
	        	"SELECT n " +
		        "FROM NewsItem n " +
	        	"ORDER BY n.creationDate").list();
		
	}
	
	@SuppressWarnings("unchecked")
	public List<NewsItem> getAllNewsItemFromEvent(int eventId) {
		return getSession().createQuery(
	        	"SELECT n " +
		        "FROM NewsItem n WHERE n.event.eventId = :eventId ORDER BY n.publishDate"
		        + "").setParameter("eventId", eventId).list();
	} 

	@SuppressWarnings("unchecked")
	@Override
	public List<NewsItem> getLastNews(Calendar dateLimit, boolean onlyOutstandingNews) {
		
		List<NewsItem> result = getSession().createQuery( "SELECT n " +
                                        				  "FROM NewsItem n " +
                                        				  "WHERE n.publishDate <= current_timestamp() " + 
                                        				  	"AND n.publishDate >= :dateLimit " +
                                                          "ORDER BY n.publishDate DESC" 
														).setDate("dateLimit", dateLimit.getTime()).list();

		
		if (onlyOutstandingNews) {
			List<NewsItem> outstanding = new ArrayList<NewsItem>();
			for (NewsItem n:result){
				Calendar priorityTimeLimit = (Calendar) n.getPublishDate().clone();
				priorityTimeLimit.add(Calendar.HOUR_OF_DAY, n.getPriorityHours());
				if(priorityTimeLimit.compareTo(Calendar.getInstance())>=0) outstanding.add(n);
			}
			return outstanding;
		}
		return result; 
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<NewsItem> getLastNewsFormEvent(int eventId, Calendar dateLimit, boolean onlyOutstandingNews) {

		List<NewsItem> result = getSession().createQuery( "SELECT n " +
                                        				  "FROM NewsItem n " +
                                        				  "WHERE n.publishDate <= current_timestamp() " + 
                                        				  	"AND n.publishDate >= :dateLimit " +
                                        				    "AND n.event.id = :eventId" +
                                                          "ORDER BY n.publishDate DESC" 
														).setParameter("eventId", eventId).setDate("dateLimit", dateLimit.getTime()).list();

		
		if (onlyOutstandingNews) {
			List<NewsItem> outstanding = new ArrayList<NewsItem>();
			for (NewsItem n:result){
				Calendar priorityTimeLimit = (Calendar) n.getPublishDate().clone();
				priorityTimeLimit.add(Calendar.HOUR_OF_DAY, n.getPriorityHours());
				if(priorityTimeLimit.compareTo(Calendar.getInstance())>=0) outstanding.add(n);
			}
			return outstanding;
		}
		return result;
	}
	
}
