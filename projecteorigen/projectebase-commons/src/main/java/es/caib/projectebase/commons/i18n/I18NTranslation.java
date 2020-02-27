package es.caib.projectebase.commons.i18n;

/**
 * Representa un missatge traduïble que està compost per una etiqueta i uns arguments opcionals.
 *
 * @author anadal
 */
public class I18NTranslation {

    private String code;

    private I18NArgument[] args;

    /**
     * Construeix un missatge traduïble buid.
     */
    public I18NTranslation() {
    }

    /**
     *
     * @param code
     *
     */
    public I18NTranslation(String code, I18NArgument... args) {
        this.code = code;
        this.args = args;
    }

    /**
     * @param code
     */
    public I18NTranslation(String code) {
        this(code, (I18NArgument[]) null);
    }

    /**
     * @param code
     */
    public I18NTranslation(String code, String... args) {
        this(code, stringdToI18NArgument(args));
    }


    public static I18NArgument[] stringdToI18NArgument(String... args) {
        I18NArgument[] argus;
        if (args == null || args.length == 0) {
            argus = null;
        } else {

            argus = new I18NArgument[args.length];
            for (int i = 0; i < args.length; i++) {
                argus[i] = new I18NArgumentString(args[i]);
            }
        }
        return argus;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public I18NArgument[] getArgs() {
        return args;
    }

    public void setArgs(I18NArgument[] args) {
        this.args = args;
    }

}
