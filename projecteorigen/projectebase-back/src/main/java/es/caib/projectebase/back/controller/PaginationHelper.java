package es.caib.projectebase.back.controller;

import java.io.Serializable;
import java.util.List;

/**
 * Per mantenir l'estat bàsic d'una paginació.
 */
public abstract class PaginationHelper<E extends Serializable> {

    private int pageSize = 10;
    private int page = 0;
    private int count;
    private List<E> model;

    PaginationHelper() {
        updateModel();
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
        updateModel();
    }

    public int getPageSize() {
        return pageSize;
    }

    int getPageFirstItem() {
        return page*pageSize;
    }

    void setCount(int count) {
        this.count = count;
    }

    public List<E> getModel() {
        return model;
    }

    void setModel(List<E> model) {
        this.model = model;
    }

    public boolean isHasNextPage() {
        return (page+1)*pageSize+1 <= count;
    }

    public boolean isHasPreviousPage() {
        return page > 0;
    }

    public void nextPage() {
        if (isHasNextPage()) {
            page++;
        }
        updateModel();
    }

    public void previousPage() {
        if (isHasPreviousPage()) {
            page--;
        }
        updateModel();
    }

    protected abstract void updateModel();
}
