package converter;

import javax.el.ELContext;
import javax.el.ELResolver;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import entity.Cliente;
import mb.ClienteMb;




@FacesConverter(forClass=Cliente.class)
public class ClienteConverter implements Converter{

	@Override
	public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
		ELContext elContext = facesContext.getELContext();
		ELResolver elResolver = elContext.getELResolver();
		
		ClienteMb clienteMb = (ClienteMb) elResolver.getValue(elContext, null, "cliMB");
		
		Long id = Long.parseLong(value);
		return clienteMb.buscarclientePorId(id);
	}

	@Override
	public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object object) {
		Cliente cliente = (Cliente) object;
		return String.valueOf(cliente.getId());
	}


}
