package pl.jitsolutions.training.jitbet.business;

import java.sql.Date;
import java.time.Year;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class YearAttrConverter implements AttributeConverter<Year, Date> {
    @Override
    public Date convertToDatabaseColumn(Year attribute) {
        if (attribute == null) {
            return null;
        }

        return Date.valueOf(attribute.atDay(1));
    }

    @Override
    public Year convertToEntityAttribute(Date dbData) {
        return Year.from(dbData.toLocalDate());
    }
}
