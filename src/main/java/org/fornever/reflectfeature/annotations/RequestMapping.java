/**
 * 
 */
package org.fornever.reflectfeature.annotations;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Documented
@Retention(RUNTIME)
@Target({ METHOD, TYPE })
/**
 * @author Theo Sun
 *
 */
public @interface RequestMapping {

	/**
	 * map path
	 * 
	 * @return
	 */
	public String path() default "/";

	public String method() default "GET";

}
