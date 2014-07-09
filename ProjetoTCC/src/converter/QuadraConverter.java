package converter;

import javax.el.ELContext;
import javax.el.ELResolver;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;







import mb.QuadraMb;

import entity.Quadra;

@FacesConverter(forClass=Quadra.class)
public class QuadraConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
		ELContext elContext = facesContext.getELContext();
		ELResolver elResolver = elContext.getELResolver();
		
		QuadraMb quadraMb = (QuadraMb) elResolver.getValue(elContext, null, "quadMB");
		
		Long id = Long.parseLong(value);
		return quadraMb.buscarQuadraPorId(id);
	}

	@Override
	public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object object) {
		Quadra quadra = (Quadra) object;
		return String.valueOf(quadra.getId());
	}


}
