package br.com.fiaphexa.dominio.dtos;

import java.awt.print.Pageable;

public class PageInfoDto {

    private int pageNumber;
    private int pageSize;


    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}