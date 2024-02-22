package edu.escuelaing.arem.ASE.app.POJO.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


@Retention(RetentionPolicy.RUNTIME)
public @interface RequestMapping {
    String value();
}
