package es.caib.projectebase.arxiu;

import es.caib.plugins.arxiu.api.ContingutArxiu;
import es.caib.plugins.arxiu.api.ContingutOrigen;
import es.caib.plugins.arxiu.api.Document;
import es.caib.plugins.arxiu.api.DocumentContingut;
import es.caib.plugins.arxiu.api.DocumentEstat;
import es.caib.plugins.arxiu.api.DocumentEstatElaboracio;
import es.caib.plugins.arxiu.api.DocumentExtensio;
import es.caib.plugins.arxiu.api.DocumentFormat;
import es.caib.plugins.arxiu.api.DocumentMetadades;
import es.caib.plugins.arxiu.api.DocumentTipus;
import es.caib.plugins.arxiu.api.Expedient;
import es.caib.plugins.arxiu.api.ExpedientEstat;
import es.caib.plugins.arxiu.api.ExpedientMetadades;
import es.caib.plugins.arxiu.api.IArxiuPlugin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.Part;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Controlador per gestionar la creació i esborrat d'expedients a dins arxiu.
 *
 * @author areus
 */
@Named
@SessionScoped
public class ExpedientController implements Serializable {

    private static final Logger LOG = LoggerFactory.getLogger(ExpedientController.class);

    @Inject
    FacesContext context;

    /**
     * Obté el plugin d'arxiu a través de {@link PluginProducer#getArxiuPlugin() }
     */
    @Inject
    private IArxiuPlugin plugin;

    @Inject
    private ExpedientModel model;

    private final List<Expedient> expedientsCreats = new ArrayList<>();

    public List<Expedient> getExpedientsCreats() {
        return expedientsCreats;
    }
    
    public String crearExpedient() {
        LOG.info("createExpedient");

        Expedient expedient = new Expedient();
        expedient.setNom(model.getName());

        if (model.getFile() != null) {
            Part file = model.getFile();

            LOG.info("Rebut fitxer: ");
            LOG.info("name: {}", file.getName());
            LOG.info("submittedFileName: {}", file.getSubmittedFileName());
            LOG.info("contentType: {}", file.getContentType());
            LOG.info("size: {}", file.getSize());
        }

        final List<String> organs = List.of("A04013511");

        ExpedientMetadades metadades = new ExpedientMetadades();
        metadades.setOrgans(organs);
        metadades.setDataObertura(new Date());
        metadades.setClassificacio("organo1_PRO_123456789");
        metadades.setEstat(ExpedientEstat.OBERT);
        metadades.setInteressats(List.of("12345678Z", "00000000T"));
        metadades.setSerieDocumental("S0001");
        expedient.setMetadades(metadades);

        Document documentPerCrear = new Document();
        documentPerCrear.setNom("NOM DOCUMENT");
        documentPerCrear.setEstat(DocumentEstat.ESBORRANY);

        DocumentMetadades documentMetadades = new DocumentMetadades();
        documentMetadades.setOrigen(ContingutOrigen.CIUTADA);
        documentMetadades.setOrgans(organs);
        documentMetadades.setDataCaptura(new Date());
        documentMetadades.setEstatElaboracio(DocumentEstatElaboracio.ORIGINAL);
        documentMetadades.setTipusDocumental(DocumentTipus.ALTRES);
        documentMetadades.setFormat(DocumentFormat.PDF);
        documentMetadades.setExtensio(DocumentExtensio.PDF);
        documentPerCrear.setMetadades(documentMetadades);
        DocumentContingut documentContingut = new DocumentContingut();

        try (InputStream inputStream = model.getFile().getInputStream()) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream((int) model.getFile().getSize());
            inputStream.transferTo(baos);
            documentContingut.setContingut(baos.toByteArray());
        } catch (IOException ioe) {
            throw new RuntimeException(ioe);
        }
        documentContingut.setTipusMime("application/pdf");
        documentPerCrear.setContingut(documentContingut);

        // Cridada al plugin per crear l'expedient
        ContingutArxiu expedientCreat = plugin.expedientCrear(expedient);
        plugin.documentCrear(documentPerCrear, expedientCreat.getIdentificador());

        expedientsCreats.add(plugin.expedientDetalls(expedientCreat.getIdentificador(), null));

        context.addMessage(null, new FacesMessage("Expedient creat"));

        context.getExternalContext().getFlash().setKeepMessages(true);
        return context.getViewRoot().getViewId() + "?faces-redirect=true";
    }

    public String borrarExpedient(String identificador) {
        LOG.info("borrarExpedient({})", identificador);

        // Cercam que l'identificador estigui dins la llista dels expedients que hem creat
        Optional<Expedient> optional = expedientsCreats.stream()
                .filter(expedient -> expedient.getIdentificador().equals(identificador))
                .findAny();

        if (optional.isPresent()) {
            // Cridada al plugin per esborrar l'expedient
            plugin.expedientEsborrar(identificador);

            expedientsCreats.remove(optional.get());
            context.addMessage(null, new FacesMessage("Expedient esborrat"));
        } else {
            context.addMessage(null, 
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                            "Expedient desconegut: " + identificador, null));
        }
        
        context.getExternalContext().getFlash().setKeepMessages(true);
        return context.getViewRoot().getViewId() + "?faces-redirect=true";
    }
}
