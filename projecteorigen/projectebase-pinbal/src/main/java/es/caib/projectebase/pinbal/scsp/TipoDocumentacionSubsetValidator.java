package es.caib.projectebase.pinbal.scsp;

import es.caib.pinbal.client.recobriment.model.ScspTitular;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

public class TipoDocumentacionSubsetValidator
        implements ConstraintValidator<TipoDocumentacionSubset, ScspTitular.ScspTipoDocumentacion> {

    private ScspTitular.ScspTipoDocumentacion[] subset;

    @Override
    public void initialize(TipoDocumentacionSubset constraint) {
        this.subset = constraint.anyOf();
    }

    @Override
    public boolean isValid(ScspTitular.ScspTipoDocumentacion value, ConstraintValidatorContext context) {
        return value == null || Arrays.asList(subset).contains(value);
    }

}
