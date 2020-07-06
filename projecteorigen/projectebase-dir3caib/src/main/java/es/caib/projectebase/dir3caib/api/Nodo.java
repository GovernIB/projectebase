package es.caib.projectebase.dir3caib.api;

import java.util.List;

/**
 * Model emprat per Api Rest Dir3Caib
 */
public class Nodo extends ObjetoBasico {

    private String idPadre;
    private List<Nodo> hijos;
    private List<Nodo> oficinasDependientes;
    private List<Nodo> oficinasAuxiliares;
    private List<Nodo> oficinasFuncionales;
    private List<Nodo> historicos;
    private boolean tieneOficinaSir = false;
    private boolean esEdp = false;
    private int nivel;

    public String getIdPadre() {
        return idPadre;
    }

    public void setIdPadre(String idPadre) {
        this.idPadre = idPadre;
    }

    public List<Nodo> getHijos() {
        return hijos;
    }

    public void setHijos(List<Nodo> hijos) {
        this.hijos = hijos;
    }

    public List<Nodo> getOficinasDependientes() {
        return oficinasDependientes;
    }

    public void setOficinasDependientes(List<Nodo> oficinasDependientes) {
        this.oficinasDependientes = oficinasDependientes;
    }

    public List<Nodo> getOficinasAuxiliares() {
        return oficinasAuxiliares;
    }

    public void setOficinasAuxiliares(List<Nodo> oficinasAuxiliares) {
        this.oficinasAuxiliares = oficinasAuxiliares;
    }

    public List<Nodo> getOficinasFuncionales() {
        return oficinasFuncionales;
    }

    public void setOficinasFuncionales(List<Nodo> oficinasFuncionales) {
        this.oficinasFuncionales = oficinasFuncionales;
    }

    public List<Nodo> getHistoricos() {
        return historicos;
    }

    public void setHistoricos(List<Nodo> historicos) {
        this.historicos = historicos;
    }

    public boolean isTieneOficinaSir() {
        return tieneOficinaSir;
    }

    public void setTieneOficinaSir(boolean tieneOficinaSir) {
        this.tieneOficinaSir = tieneOficinaSir;
    }

    public boolean isEsEdp() {
        return esEdp;
    }

    public void setEsEdp(boolean esEdp) {
        this.esEdp = esEdp;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }
}
