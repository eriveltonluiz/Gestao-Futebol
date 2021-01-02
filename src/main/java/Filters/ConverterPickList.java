package Filters;

import java.io.Serializable;
import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import Model.Jogador;

@FacesConverter(value = "converterPickList", forClass = Jogador.class)
public class ConverterPickList implements Converter, Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Override
    public Object getAsObject(FacesContext ctx, UIComponent component, String value) {
        if (value != null) {
        	return this.getAttributesFrom(component).get(value);
        }
        return null;
    }
 
    @Override
    public String getAsString(FacesContext ctx, UIComponent component, Object value) {
        if (value != null && !"".equals(value)) {
            Jogador status = (Jogador) value;
            this.addAttribute(component, status);
            Long codigo = status.getId();
            if (codigo != null) {
                return String.valueOf(codigo);
            }
        }
        return (String) value;
    }
 
    protected void addAttribute(UIComponent component, Jogador o) {
        String key = o.getId().toString();
        this.getAttributesFrom(component).put(key, o);
    }
 
    protected Map<String, Object> getAttributesFrom(UIComponent component) {
        return component.getAttributes();
    }
}
