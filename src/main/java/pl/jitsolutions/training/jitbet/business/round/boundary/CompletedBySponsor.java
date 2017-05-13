package pl.jitsolutions.training.jitbet.business.round.boundary;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import pl.jitsolutions.training.jitbet.business.league.entity.Sponsor;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.TYPE})
@Documented
public @interface CompletedBySponsor {
    Sponsor value() default Sponsor.BP;
}
