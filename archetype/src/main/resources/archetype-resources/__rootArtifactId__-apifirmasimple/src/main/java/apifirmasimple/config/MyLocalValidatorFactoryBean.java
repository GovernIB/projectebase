#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.apifirmasimple.config;

import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import javax.validation.ConstraintViolation;

import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.lang.Nullable;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

/**
 * 
 * @author anadal
 *
 */
public class MyLocalValidatorFactoryBean extends LocalValidatorFactoryBean {

    public static final ThreadLocal<Errors> originalErrors = new ThreadLocal<Errors>();
    
    protected final ReloadableResourceBundleMessageSource messageSource;

    public MyLocalValidatorFactoryBean(ReloadableResourceBundleMessageSource messageSource) {
        super();
        this.messageSource = messageSource;
    }

    /* XYZ ZZZ
    
    @Override
    public <T> Set<ConstraintViolation<T>> validate(T object, Class<?>... groups) {

        System.out.println("${symbol_escape}nVVVVVVVVV11111 validate(T object, Class<?>... groups)${symbol_escape}n");
        return super.validate(object, groups);
    }

    @Override
    public <T> Set<ConstraintViolation<T>> validateProperty(T object, String propertyName, Class<?>... groups) {
        System.out.println("${symbol_escape}nVVVVVVVVVV2222 validateProperty(T object, String propertyName, Class<?>... groups)${symbol_escape}n");
        return super.validateProperty(object, propertyName, groups);
    }

    @Override
    public <T> Set<ConstraintViolation<T>> validateValue(Class<T> beanType, String propertyName, Object value,
            Class<?>... groups) {
        System.out.println(
                "${symbol_escape}nVVVVVVVVVV3333 validateValue(Class<T> beanType, String propertyName, Object value,Class<?>... groups)${symbol_escape}n");
        return super.validateValue(beanType, propertyName, value, groups);
    }

    @Override
    public void validate(@Nullable Object target, Errors errors) {
        System.out.println("${symbol_escape}nVVVVVVVVVVV4444 validate(@Nullable Object target, Errors errors) ${symbol_escape}n");
        super.validate(target, errors);
    }
    */

    @Override
    public void validate(@Nullable Object target, Errors errors, @Nullable Object... validationHints) {

        /* XYZ ZZZ
        List<ObjectError> objectErrors = errors.getAllErrors();
        StringBuffer str = new StringBuffer();
        for (ObjectError e : objectErrors) {
            str.append("${symbol_escape}n   + e.getObjectName() => " + e.getObjectName() + " |  e.getCode() => " + e.getCode());
        }

        StringBuffer str2 = new StringBuffer();
        for (Object o : validationHints) {
            str2.append("${symbol_escape}n   + " + ((o == null) ? "NULL" : o.toString()));
        }

        System.out.println(
                "${symbol_escape}n${symbol_escape}n ========================== VVVVVVVVVVV55555 validate(@Nullable Object target, Errors errors, @Nullable Object... validationHints)"
                        + "${symbol_escape}n    TARGET CLASS => " + target.getClass() + "${symbol_escape}n    ERRORS => " + str.toString()
                        + "${symbol_escape}n    HINTS => " + str2.toString() + "${symbol_escape}n    errors.getObjectName() " + errors.getObjectName()
                        + "${symbol_escape}n    errors.getNestedPath() " + errors.getNestedPath() + "${symbol_escape}n${symbol_escape}n");
*/
        originalErrors.set(errors);

    //    List<FieldError> list = errors.getFieldErrors().stream().collect(Collectors.toList());

        BeanPropertyBindingResult clonederrors = new BeanPropertyBindingResult(target, errors.getObjectName());
        clonederrors.setNestedPath(errors.getNestedPath());

//        for (FieldError fieldError : list) {
//            clonederrors.addError(fieldError);
//        }

        super.validate(target, clonederrors, validationHints);

       // XYZ ZZZ System.out.println("${symbol_escape}n${symbol_escape}n ========================== VVVVVVVVVVV55555   FINAL FINAL FINAL =========${symbol_escape}n");

    }



    @Override
    protected void processConstraintViolations(Set<ConstraintViolation<Object>> violations, Errors errorsCloned) {

        //  XYZ ZZZ
        //System.out.println("${symbol_escape}n${symbol_escape}n ========================== XXXXXXXXXXX PRES processConstraintViolations: "
        //        + errorsCloned.getClass());

        // org.springframework.validation.BeanPropertyBindingResult e2 =
        // (org.springframework.validation.BeanPropertyBindingResult)errors;

        // e2.getModel().clear();

        super.processConstraintViolations(violations, errorsCloned);

        Errors errorsOk = originalErrors.get();

        // XYZ ZZZ StringBuffer str2 = new StringBuffer();
        //int count = 0;
        
        Set<String> processed= new HashSet<String>();
        
        for (ConstraintViolation<Object> violation : violations) {
            String field = determineField(violation);
            
            if (processed.contains(field)) {
                continue;
            }
            
            processed.add(field);

            List<FieldError> feList = errorsCloned.getFieldErrors(field);

            for (FieldError fe : feList) {

                /* XYZ ZZZ
                str2.append("${symbol_escape}n  [" + count + "] + FIELD => " + field + " |  C => " + fe.getCode() + " | D => "
                        + fe.getDefaultMessage() + " O => " + fe.getObjectName() + " | P => "
                        + violation.getPropertyPath());
*/
                String msg = fe.getDefaultMessage();

                if (msg.indexOf("{FieldName}") == -1) {
                    errorsOk.rejectValue(field, "codi.comodi", new Object[] { msg }, msg);
                } else {

                    String codeLabel = fe.getObjectName() + "." + violation.getPropertyPath();
                    // TODO locale NULL
                    Locale locale = MyMessageInterpolator.languageThread.get();
                    String traduit;
                    try {
                        traduit = messageSource.getMessage(codeLabel, null, locale);
                    } catch (org.springframework.context.NoSuchMessageException e) {
                        e.printStackTrace();
                        traduit = "???" + codeLabel + "???";
                    }

                    msg = msg.replace((CharSequence) "{FieldName}", (CharSequence) traduit);

                    errorsOk.rejectValue(field, "codi.comodi", new Object[] { msg }, msg);

                }
            }

        }

    }

}
