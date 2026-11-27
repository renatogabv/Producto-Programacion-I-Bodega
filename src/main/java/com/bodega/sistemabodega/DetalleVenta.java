package com.bodega.sistemabodega;

import com.bodega.sistemabodega.util.Formato;

public class DetalleVenta {

    private Producto producto;
    private int cantidad;
    private double precioUnitario;
    private double costoUnitario;

    public DetalleVenta(Producto producto, int cantidad) {
        if (producto == null) {
            throw new IllegalArgumentException("El producto no puede ser nulo");
        }
        if (cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor a 0");
        }
        this.producto = producto;
        this.cantidad = cantidad;
        this.precioUnitario = producto.getPrecioVenta();
        this.costoUnitario = producto.getPrecioCompra();
    }

    public Producto getProducto() {
        return producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public double calcularSubtotal() {
        return Formato.redondearDosD(cantidad * precioUnitario);
    }

    public double calcularGanancia() {
        return Formato.redondearDosD((precioUnitario - costoUnitario) * cantidad);
    }

    @Override
    public String toString() {
        return producto.getNombre() + " x" + cantidad
                + " (" + Formato.moneda(precioUnitario) + " c/u) = "
                + Formato.moneda(calcularSubtotal());
    }
}
