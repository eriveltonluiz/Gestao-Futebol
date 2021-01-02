package Filters;

import java.io.Serializable;
import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Named;

import Model.TimeCamp;

@Named
@FacesConverter(forClass = TimeCamp.class, value = "converterTimes")
public class ConverterTimesCamp implements Converter, Serializable{


	private static final long serialVersionUID = 5941811165675074022L;
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
            TimeCamp status = (TimeCamp) value;
            this.addAttribute(component, status);
            Long codigo = status.getId();
            if (codigo != null) {
                return String.valueOf(codigo);
            }
        }
        return (String) value;
    }
 
    protected void addAttribute(UIComponent component, TimeCamp o) {
        String key = o.getId().toString();
        this.getAttributesFrom(component).put(key, o);
    }
 
    protected Map<String, Object> getAttributesFrom(UIComponent component) {
        return component.getAttributes();
    }

}
