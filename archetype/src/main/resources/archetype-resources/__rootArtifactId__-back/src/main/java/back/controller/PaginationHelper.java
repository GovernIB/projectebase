#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.back.controller;

import java.io.Serializable;
import java.util.List;

/**
 * Classe per matenir les dades i l'estat d'una paginació. Per emprar-ho cal fer una subclasse i implementar
 * el mètode {@link ${symbol_pound}updateModel()}.
 *
 * @author areus
 */
public abstract class PaginationHelper<E extends Serializable> {

    /**
     * Tamany de pàgina. Per defecte, 10.
     */
    private Integer pageSize = 10;

    /**
     * Nombre de pàgina actual. La primera és 0.
     */
    private Integer page = 0;

    /**
     * Nombre de registres total.
     */
    private int count;

    /**
     * Llista d'entitats corresponents a la pàgina actual.
     */
    private List<E> model;

    /**
     * Quan construim l'objecte, s'actualitza el model.
     */
    PaginationHelper() {
        updateModel();
    }

    /**
     * Fixa el tamany de pàgina. Provoca que es pasi a la primera pàgina i s'actualitzi el model si el tamany canvia.
     * @param pageSize nou tamany de pàgina
     */
    public void setPageSize(Integer pageSize) {
        if (pageSize != this.pageSize) {
            this.pageSize = pageSize;
            this.page = 0;
            updateModel();
        }
    }

    /**
     * Obté el tamany de pàgina actual.
     */
    public Integer getPageSize() {
        return pageSize;
    }

    /**
     * Obté el número d'índex de la pàgina actual. La primera és 0.
     * @return número d'índex de la pàgina actual.
     */
    public Integer getPage() {
        return page;
    }

    /**
     * Fixa el número total de registres.
     * @param count Nombre total de registres.
     */
    void setCount(int count) {
        this.count = count;
    }

    /**
     * Obté el nombre total de registres.
     * @return nombre total de registres.
     */
    public int getCount() {
        return count;
    }

    /**
     * Obté els registres de la pàgina actual.
     * @return registres de la pàgina actual.
     */
    public List<E> getModel() {
        return model;
    }

    /**
     * Fixa els registres de la pàgina actual.
     * @param model registres de la pàgina actual.
     */
    void setModel(List<E> model) {
        this.model = model;
    }

    /**
     * Obté l'índex del primer element de la pàgina actual. El primer de la primera pàgina és 0.
     * Equivalent a <code>page * pageSize</code>
     * @return índex del primer element de la pàgina actual.
     */
    public int getPageFirstItem() {
        return page*pageSize;
    }

    /**
     * Obté el nombre d'índex de la pàgina més gran segons el nombre de registres i el tamany de pàgina.
     * Atès que la primera és 0, si hi ha 5 pàgines per exemple, el mètode retornà 4, atès que les
     * 5 pàgines seran: 0, 1 ,2 ,3 ,4
     * @return Valor de la darrera pàgina.
     */
    public int getMaxPage() {
        return (count -1) / pageSize;
    }

    /**
     * Indica si hi ha una pàgina següent a l'actual.
     * @return <code>true</code> si hi ha una pàgina següent, <code>false</code> en cas contrari.
     */
    public boolean isHasNextPage() {
        return (page+1)*pageSize+1 <= count;
    }

    /**
     * Indica si hi ha una pàgina anterior a l'actual.
     * @return <code>true</code> si hi ha una pàgina anterior, <code>false</code> en cas contrari.
     */
    public boolean isHasPreviousPage() {
        return page > 0;
    }

    /**
     * Actualitza la pàgina a la següent i actualitza el model.
     */
    public void nextPage() {
        if (isHasNextPage()) {
            page++;
            updateModel();
        }
    }

    /**
     * Actualitza la pàgina a la anterior i actualitza el model.
     */
    public void previousPage() {
        if (isHasPreviousPage()) {
            page--;
            updateModel();
        }
    }

    /**
     * Fixa la pàgina i actualitza el model, sempre que la pàgina sigui major que 0, i com a màxim
     * {@link ${symbol_pound}getMaxPage()}.
     * @param page índex de la nova pàgina
     */
    public void gotoPage(int page) {
        if (page >= 0 && page <= getMaxPage()) {
            this.page = page;
            updateModel();
        }
    }

    /**
     * Actualitza el model de dades. Les implementacions han de cridar {@link ${symbol_pound}setCount(int)} amb el
     * nombre tota de registres, i {@link ${symbol_pound}setModel(List)} amb els registres corresponents a la pàgina
     * actual (el primer serà {@link ${symbol_pound}getPageFirstItem()} i n'hi haurà com a màxim {@link ${symbol_pound}getPageSize()}.
     */
    public abstract void updateModel();
}
