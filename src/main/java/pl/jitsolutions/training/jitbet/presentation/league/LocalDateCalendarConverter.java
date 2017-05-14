package pl.jitsolutions.training.jitbet.presentation.league;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.component.calendar.Calendar;

@FacesConverter("localDateCalendarConverter")
public class LocalDateCalendarConverter implements Converter {
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (StringUtils.isBlank(value)) {
            return null;
        }

        if (component instanceof Calendar) {
            Calendar calendar = (Calendar) component;
            String pattern = calendar.getPattern();

            return LocalDate.parse(value, DateTimeFormatter.ofPattern(pattern));
        }

        return null;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return null;
    }
}
