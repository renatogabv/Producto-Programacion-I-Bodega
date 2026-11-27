package com.bodega.sistemabodega;

import com.bodega.sistemabodega.util.Formato;
import com.bodega.sistemabodega.util.Validador;

public class Producto {

    private static int totalproducto = 0;
    private String nombre;
    private String codigo;
    private String categoria;
    private double preciocompra;
    private double precioventa;
    private int stock;

    public Producto(String codigo, String nombre, String categoria, double preciocompra, double precioventa, int stock) {
        setCodigo(codigo);
        setNombre(nombre);
        setCategoria(categoria);
        setPrecioCompra(preciocompra);
        setPrecioVenta(precioventa);
        setStock(stock);
        totalproducto++;
    }

    public Producto(String codigo, String nombre, String categoria, double preciocompra, double precioventa) {
        this(codigo, nombre, categoria, preciocompra, precioventa, 0);
    }

    public static int getTotalProductos() {
        return totalproducto;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public double getPrecioCompra() {
        return preciocompra;
    }

    public double getPrecioVenta() {
        return precioventa;
    }

    public int getStock() {
        return stock;
    }

    public void setCodigo(String codigo) {
        String limpio = (codigo == null) ? null : codigo.trim().toUpperCase();
        if (!Validador.esCodigoProducto(limpio)) {
            throw new IllegalArgumentException("Codigo invalido");
        } else {
            this.codigo = limpio;
        }
    }

    public void setNombre(String nombre) {
        if (Validador.esVacio(nombre)) {
            throw new IllegalArgumentException("Nombre invalido");
        } else {
            this.nombre = nombre.trim();
        }
    }

    public void setCategoria(String categoria) {
        if (Validador.esVacio(categoria)) {
            throw new IllegalArgumentException("Categoria invalida");
        } else {
            this.categoria = categoria.trim();
        }
    }

    public void setPrecioCompra(double preciocompra) {
        if (!Validador.esNoNegativo(preciocompra)) {
            throw new IllegalArgumentException("Precio de compra invalido");
        }
        this.preciocompra = Formato.redondearDosD(preciocompra);
    }

    public void setPrecioVenta(double precioventa) {
        if (!Validador.esNoNegativo(precioventa)) {
            throw new IllegalArgumentException("Precio de venta invalido");
        }
        this.precioventa = Formato.redondearDosD(precioventa);
    }

    public void setStock(int stock) {
        if (stock < 0) {
            throw new IllegalArgumentException("El stock no puede ser negativo");
        } else {
            this.stock = stock;
        }
    }

    public boolean aumentarStock(int cantidad) {
        if (cantidad > 0) {
            this.stock += cantidad;
            return true;
        }
        return false;
    }

    public boolean descontarStock(int cantidad) {
        if (cantidad <= 0 || cantidad > this.stock) {
            return false;
        }
        this.stock -= cantidad;
        return true;
    }
}
