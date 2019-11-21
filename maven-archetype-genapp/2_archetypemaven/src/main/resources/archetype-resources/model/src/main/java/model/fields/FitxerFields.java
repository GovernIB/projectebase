#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )

package ${package}.model.fields;
import org.fundaciobit.genapp.common.query.*;
public interface FitxerFields extends java.io.Serializable {

  public static final String _TABLE_SQL = "${prefix}_fitxer";


  public static final String _TABLE_MODEL = "fitxer";


  public static final String _TABLE_TRANSLATION = _TABLE_MODEL + "." + _TABLE_MODEL;


	 public static final LongField FITXERID = new LongField(_TABLE_MODEL, "fitxerID", "fitxerid");  // PK
	 public static final StringField NOM = new StringField(_TABLE_MODEL, "nom", "nom");
	 public static final StringField DESCRIPCIO = new StringField(_TABLE_MODEL, "descripcio", "descripcio");
	 public static final StringField MIME = new StringField(_TABLE_MODEL, "mime", "mime");
	 public static final LongField TAMANY = new LongField(_TABLE_MODEL, "tamany", "tamany");


  public static final Field<?>[] ALL_FITXER_FIELDS = {
    FITXERID,
    NOM,
    DESCRIPCIO,
    MIME,
    TAMANY
  };


  public static final Field<?>[] PRIMARYKEY_FIELDS = {
FITXERID
  };
}
