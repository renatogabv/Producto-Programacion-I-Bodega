package com.bodega.sistemabodega;
import java.util.ArrayList;


public class Venta {
    private static int contadorventa = 0;
    private int numero;
    private Cliente cliente;
    private String tipopago;
    private double total;
    private double ganancia;
    private double montopagado;
    private ArrayList<DetalleVenta> detalles;
    
    public Venta(Cliente dnicliente){
        this.cliente = dnicliente;
    }

    public int getContadorventa() {
        return contadorventa;
    }

    public int getNumero() {
        return numero;
    }

    public Cliente getDnicliente() {
        return cliente;
    }

    public String getTipopago() {
        return tipopago;
    }

    public double getTotal() {
        return total;
    }

    public double getGanancia() {
        return ganancia;
    }

    public double getMontopagado() {
        return montopagado;
    }

    public void setTipopago(String tipopago) {
        this.tipopago = tipopago;
    }
    
    public void agregarDetalle(Producto,
    }
    
    public boolean registrarPago(double monto) {
        if (monto >= total && total > 0) {
            this.montopagado = monto;
            return true;
        }
        return false;
    }
    
    public double calcularVuelto(){
        return this.montopagado - this.total;
    }
}
