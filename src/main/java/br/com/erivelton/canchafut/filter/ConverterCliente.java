package br.com.erivelton.canchafut.filter;

import java.io.Serializable;
import java.util.Map;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Named;

import br.com.erivelton.canchafut.model.Cliente;

@Named
@FacesConverter(forClass = Cliente.class, value = "converterClientes")
public class ConverterCliente implements Converter, Serializable{

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
            Cliente status = (Cliente) value;
            this.addAttribute(component, status);
            Long codigo = status.getIdCliente();
            if (codigo != null) {
                return String.valueOf(codigo);
            }
        }
        return (String) value;
    }
 
    protected void addAttribute(UIComponent component, Cliente o) {
        String key = o.getIdCliente().toString();
        this.getAttributesFrom(component).put(key, o);
    }
 
    protected Map<String, Object> getAttributesFrom(UIComponent component) {
        return component.getAttributes();
    }
}
