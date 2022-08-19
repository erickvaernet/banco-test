package com.example.banco.dto;



import java.util.List;

public class PaginaDTO <T>{
    private long cantidad;
    private List<T> resultados;

    private Integer tamanioDePagina;

    private Integer numeroDePagina;

    public PaginaDTO( Integer numeroDePagina,Integer tamanioDePagina,long cantidad, List<T> resultados ) {
        this.cantidad = cantidad;
        this.resultados = resultados;
        this.tamanioDePagina = tamanioDePagina==null ? 10 : tamanioDePagina;
        this.numeroDePagina = numeroDePagina==null ? 0 : numeroDePagina;
    }

    public void setTamanioDePagina(Integer tamanioDePagina) {
        this.tamanioDePagina = tamanioDePagina==null?8:tamanioDePagina;
    }

    public void setNumeroDePagina(Integer numeroDePagina) {
        this.numeroDePagina = numeroDePagina==null?0:numeroDePagina;
    }

    public long getCantidad() {
        return cantidad;
    }

    public void setCantidad(long cantidad) {
        this.cantidad = cantidad;
    }

    public List<T> getResultados() {
        return resultados;
    }

    public void setResultados(List<T> resultados) {
        this.resultados = resultados;
    }

    public Integer getTamanioDePagina() {
        return tamanioDePagina;
    }

    public Integer getNumeroDePagina() {
        return numeroDePagina;
    }
}
