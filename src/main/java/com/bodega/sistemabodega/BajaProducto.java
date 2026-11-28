
package com.bodega.sistemabodega;

import com.bodega.sistemabodega.util.Formato;
import com.bodega.sistemabodega.util.Validador;
import java.time.LocalDateTime;

public class BajaProducto {

private final Producto producto;
    private final int cantidad;
    private final String motivo;
    private final LocalDateTime fechaHora;

    public BajaProducto(Producto producto, int cantidad, String motivo) {
        if (producto == null) {
            throw new IllegalArgumentException("Se requiere un producto");
        }
        if (!Validador.esMotivoBajaValido(motivo)) {
            throw new IllegalArgumentException("Motivo de baja invalido");
        }
        if (!producto.descontarStock(cantidad)) {
            throw new IllegalArgumentException("Cantidad invalida o stock insuficiente para dar de baja");
        }
        this.producto = producto;
        this.cantidad = cantidad;
        this.motivo = motivo.trim().toUpperCase();
        this.fechaHora = LocalDateTime.now();
    }

    public Producto getProducto() {
        return producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public String getMotivo() {
        return motivo;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    @Override
    public String toString() {
        return Formato.fechaHora(fechaHora)
                + " | Prod: " + producto.getCodigo() + " - " + producto.getNombre()
                + " | -" + cantidad + " " + producto.getUnidad()
                + " | Motivo: " + motivo;
    }
    
}
