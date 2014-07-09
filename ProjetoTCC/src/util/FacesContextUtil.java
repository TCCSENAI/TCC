package util;

import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

public class FacesContextUtil {

	private static final String ENTITY_MANAGER = "ENTITY_MANAGER";

	public static void setEntityManager(ServletRequest servletRequest,
			EntityManager entityManager) {
		servletRequest.setAttribute(ENTITY_MANAGER, entityManager);
	}
	
	public static EntityManager getEntityManager() {
		HttpServletRequest request = (HttpServletRequest) FacesContext
				.getCurrentInstance().getExternalContext().getRequest();
		return (EntityManager) request.getAttribute(ENTITY_MANAGER);
	}
}
