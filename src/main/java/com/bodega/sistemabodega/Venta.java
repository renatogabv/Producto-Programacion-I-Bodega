package com.bodega.sistemabodega;

import com.bodega.sistemabodega.util.Constante;
import com.bodega.sistemabodega.util.Formato;
import com.bodega.sistemabodega.util.Validador;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Venta {

    private static int contadorventa = 0;
    private int numero;
    private LocalDateTime fechaHora;
    private Cliente cliente;
    private String tipopago;
    private double total;
    private double ganancia;
    private double montopagado;
    private ArrayList<DetalleVenta> detalles;

    public Venta(Cliente cliente) {
        if (cliente == null) {
            throw new IllegalArgumentException("La venta requiere un cliente");
        }
        contadorventa++;
        this.numero = contadorventa;
        this.fechaHora = LocalDateTime.now();
        this.cliente = cliente;
        this.tipopago = Constante.T_EFECTIVO;
        this.total = 0.0;
        this.ganancia = 0.0;
        this.montopagado = 0.0;
        this.detalles = new ArrayList<>();
    }

    public static int getContadorventa() {
        return contadorventa;
    }

    public int getNumero() {
        return numero;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public Cliente getCliente() {
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

    public ArrayList<DetalleVenta> getDetalles() {
        return detalles;
    }

    public void setTipopago(String tipopago) {
        if (Validador.esVacio(tipopago)) {
            throw new IllegalArgumentException("Tipo de pago inválido");
        }
        String tipo = tipopago.trim().toUpperCase();
        if (!tipo.equals(Constante.T_EFECTIVO)
                && !tipo.equals(Constante.T_TARJETA)
                && !tipo.equals(Constante.T_YAPE)
                && !tipo.equals(Constante.T_PLIN)) {
            throw new IllegalArgumentException("Tipo de pago no soportado: " + tipo);
        }
        this.tipopago = tipo;
    }

    public boolean agregarDetalle(Producto producto, int cantidad) {
        if (producto == null || cantidad <= 0) {
            return false;
        }
        if (!producto.descontarStock(cantidad)) {
            return false;
        }
        DetalleVenta detalle = new DetalleVenta(producto, cantidad);
        detalles.add(detalle);
        this.total = Formato.redondearDosD(this.total + detalle.calcularSubtotal());
        this.ganancia = Formato.redondearDosD(this.ganancia + detalle.calcularGanancia());
        return true;
    }

    public boolean registrarPago(double monto) {
        if (monto >= total && total > 0) {
            this.montopagado = Formato.redondearDosD(monto);
            return true;
        }
        return false;
    }

    public double calcularVuelto() {
        if (montopagado < total) {
            return 0.0;
        }
        return Formato.redondearDosD(this.montopagado - this.total);
    }
}
