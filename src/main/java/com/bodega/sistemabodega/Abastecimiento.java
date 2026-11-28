
package com.bodega.sistemabodega;

import com.bodega.sistemabodega.util.Formato;
import java.time.LocalDateTime;

public class Abastecimiento {
 
private final Proveedor proveedor;
    private final Producto producto;
    private final int cantidad;
    private final double costoUnitario;
    private final LocalDateTime fechaHora;

    public Abastecimiento(Proveedor proveedor, Producto producto, int cantidad, double costoUnitario) {
        if (proveedor == null) {
            throw new IllegalArgumentException("El abastecimiento requiere un proveedor");
        }
        if (producto == null) {
            throw new IllegalArgumentException("El abastecimiento requiere un producto");
        }
        if (cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor a 0");
        }
        if (!producto.aumentarStock(cantidad)) {
            throw new IllegalArgumentException("No se pudo aumentar el stock del producto");
        }
        if (costoUnitario >= 0) {
            producto.setPrecioCompra(costoUnitario);
        }
        this.proveedor = proveedor;
        this.producto = producto;
        this.cantidad = cantidad;
        this.costoUnitario = Formato.redondearDosD(producto.getPrecioCompra());
        this.fechaHora = LocalDateTime.now();
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public Producto getProducto() {
        return producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public double getCostoUnitario() {
        return costoUnitario;
    }

    public double getTotalCosto() {
        return Formato.redondearDosD(cantidad * costoUnitario);
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    @Override
    public String toString() {
        return Formato.fechaHora(fechaHora)
                + " | Prov: " + proveedor.getNombre()
                + " | Prod: " + producto.getCodigo() + " - " + producto.getNombre()
                + " | +" + cantidad + " " + producto.getUnidad()
                + " | Total: " + Formato.moneda(getTotalCosto());
    }    
    
}
