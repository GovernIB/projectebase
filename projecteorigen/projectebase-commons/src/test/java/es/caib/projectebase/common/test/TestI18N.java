package es.caib.projectebase.common.test;

import es.caib.projectebase.commons.i18n.I18NException;
import es.caib.projectebase.commons.i18n.I18NTranslator;
import org.junit.Assert;
import org.junit.Test;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

public class TestI18N {

    private static final Locale LOCALE = Locale.forLanguageTag("ca");
    private static final ResourceBundle BUNDLE = ResourceBundle.getBundle("TestLabels", LOCALE);

    @Test
    public void testI18NException() {

        try {
            throw new TestException("error.1", "{param.a}");
        } catch (TestException e) {
            String localizedMessage = e.getLocalizedMessage(LOCALE);
            String expectedMessage = MessageFormat.format(BUNDLE.getString("error.1"),
                    BUNDLE.getString("param.a"));
            Assert.assertEquals(localizedMessage, expectedMessage);
        }

        try {
            throw new TestException("error.2", "{param.a}", "{param.b}");
        } catch (TestException e) {
            String localizedMessage = e.getLocalizedMessage(LOCALE);
            String expectedMessage = MessageFormat.format(BUNDLE.getString("error.2"),
                    BUNDLE.getString("param.a"),
                    BUNDLE.getString("param.b"));
            Assert.assertEquals(localizedMessage, expectedMessage);
        }

        try {
            throw new TestException("error.3", "{param.a}", "{param.b}", "{param.c}");
        } catch (TestException e) {
            String localizedMessage = e.getLocalizedMessage(LOCALE);
            String expectedMessage = MessageFormat.format(BUNDLE.getString("error.3"),
                    BUNDLE.getString("param.a"),
                    BUNDLE.getString("param.b"),
                    BUNDLE.getString("param.c"));
            Assert.assertEquals(localizedMessage, expectedMessage);
        }

        try {
            throw new TestException("error.3", "literal", "{param.desconegut}", 25);
        } catch (TestException e) {
            String localizedMessage = e.getLocalizedMessage(LOCALE);
            String expectedMessage = MessageFormat.format(BUNDLE.getString("error.3"),
                    "literal",
                    "{param.desconegut}",
                    25);
            Assert.assertEquals(localizedMessage, expectedMessage);
        }
    }
}

class TestException extends I18NException {

    private static final long serialVersionUID = 5433469565190777267L;

    public TestException(String messageLabel, Object... parameters) {
        super(messageLabel, parameters);
    }

    @Override
    protected I18NTranslator getTranslator() {
        return I18NTranslator.from("TestLabels");
    }
}