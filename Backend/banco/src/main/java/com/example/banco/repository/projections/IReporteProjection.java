package com.example.banco.repository.projections;

public interface IReporteProjection {
    public String getFecha();
    public String getNombreCliente();
    public String getNumeroCuenta();
    public String getTipoCuenta();
    public Double getSaldoInicial();
    public Boolean getEstado();
    public String getTipoMovimiento();
    public Double getSaldoDisponible();

}
