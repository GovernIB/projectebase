package es.caib.projectebase.arxiu;

import es.caib.plugins.arxiu.api.ArxiuException;
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
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.Part;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Controlador per gestionar la creació i esborrat d'expedients a dins arxiu.
 * Proporciona a la pàgina d'exemple els mètodes per crear un nou expedient, esborrar-lo i
 * descarregar documents. Manté una llista d'expedients creats dins aquesta sessió d'usuari.
 * IMPORTANT: una vegada finalitzada la sessió d'usuari per quasevol motiu, els expedients
 * que no s'haguessin esborrat quedaran dins el servidor de l'arxiu a la data en que s'han creat.
 *
 * @author areus
 */
@Named
@SessionScoped
public class ExpedientController implements Serializable {

    private static final long serialVersionUID = 6444222206315310496L;

    private static final Logger LOG = LoggerFactory.getLogger(ExpedientController.class);

    @Inject
    @ConfigProperty(name = "es.caib.projectebase.arxiu.serieDocumental")
    private String serieDocumental;

    @Inject
    @ConfigProperty(name = "es.caib.projectebase.arxiu.classificacio")
    private String classificacio;

    @Inject
    @ConfigProperty(name = "es.caib.projectebase.arxiu.organs")
    private List<String> organs;

    @Inject
    @ConfigProperty(name = "es.caib.projectebase.arxiu.interessats")
    private List<String> interessats;

    @Inject
    FacesContext context;

    @Inject
    private IArxiuPlugin plugin;

    /**
     * Model de dades del formulari
     */
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

        ExpedientMetadades metadades = new ExpedientMetadades();
        metadades.setOrgans(organs);
        metadades.setDataObertura(new Date());
        metadades.setClassificacio(classificacio);
        metadades.setEstat(ExpedientEstat.OBERT);
        metadades.setInteressats(interessats);
        metadades.setSerieDocumental(serieDocumental);
        expedient.setMetadades(metadades);

        // Cream l'expedient
        ContingutArxiu expedientCreat = plugin.expedientCrear(expedient);

        Part file = model.getFile();
        if (file != null) {
            Document documentPerCrear = new Document();
            documentPerCrear.setNom(file.getSubmittedFileName());
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

            // Cream el contingut del document a partir del fitxer rebut        
            DocumentContingut documentContingut = new DocumentContingut();
            documentContingut.setTipusMime(file.getContentType());

            // L'arxiuNom no es guarda 
            // Veure: https://github.com/GovernIB/pluginsib-arxiu/issues/20
            // documentContingut.setArxiuNom(file.getSubmittedFileName());

            documentContingut.setTamany(file.getSize());
            try (InputStream inputStream = file.getInputStream()) {
                ByteArrayOutputStream baos = new ByteArrayOutputStream((int) file.getSize());
                inputStream.transferTo(baos);
                documentContingut.setContingut(baos.toByteArray());
            } catch (IOException ioe) {
                throw new RuntimeException(ioe);
            }
            documentPerCrear.setContingut(documentContingut);

            // Cream el document dins l'expedient creat.
            // Capturam l'excepció per evitar que afecti la consulta de l'expedient
            try {
                plugin.documentCrear(documentPerCrear, expedientCreat.getIdentificador());
            } catch (ArxiuException ae) {
                LOG.error("Error creant document", ae);
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Error creant document", ae.getMessage()));
            }
        }

        // Obtenim els detalls de l'expedient i els guardam dins la llista d'expedients creats dins la sessió
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

            // El llevam de la llista d'expedients creats
            expedientsCreats.remove(optional.get());
            context.addMessage(null, new FacesMessage("Expedient esborrat", ""));
        } else {
            context.addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Expedient desconegut: " + identificador, ""));
        }

        context.getExternalContext().getFlash().setKeepMessages(true);
        return context.getViewRoot().getViewId() + "?faces-redirect=true";
    }

    public void descarregarDocument(String identificador) throws IOException {
        LOG.info("descarregarDocument({})", identificador);

        ExternalContext ec = context.getExternalContext();

        Document document = plugin.documentDetalls(identificador, null, true);
        DocumentMetadades metadades = document.getMetadades();
        DocumentContingut contingut = document.getContingut();

        // Hem de resetejar la reposta per si hi ha cap capçalera o res.
        ec.responseReset();
        ec.setResponseContentType(contingut.getTipusMime());
        ec.setResponseContentLength((int) contingut.getTamany());

        final String filename = document.getNom() + metadades.getExtensio().toString();
        ec.setResponseHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");

        OutputStream output = ec.getResponseOutputStream();
        try (ByteArrayInputStream inputStream = new ByteArrayInputStream(contingut.getContingut())) {
            inputStream.transferTo(output);
        }

        // Important perquè JSF no intenti seguir processant la resposta.
        context.responseComplete();
    }
}
