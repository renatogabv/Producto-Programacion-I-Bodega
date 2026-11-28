package com.bodega.sistemabodega;

import com.bodega.sistemabodega.util.Constante;
import com.bodega.sistemabodega.util.Formato;
import com.bodega.sistemabodega.util.Validador;

public class Producto {

private static int totalproducto = 0;
    private String nombre;
    private String codigo;
    private Categoria categoria;
    private String nombreCategoria;
    private String unidad;
    private double preciocompra;
    private double precioventa;
    private int stock;

    public Producto(Categoria categoria, String nombre, String unidad, double preciocompra, double precioventa, int stock) {
        setCategoria(categoria);
        this.codigo = categoria.generarSiguienteCodigoProducto();
        setNombre(nombre);
        setUnidad(unidad);
        setPrecioCompra(preciocompra);
        setPrecioVenta(precioventa);
        setStock(stock);
        totalproducto++;
    }

    public Producto(Categoria categoria, String nombre, String unidad, double preciocompra, double precioventa) {
        this(categoria, nombre, unidad, preciocompra, precioventa, 0);
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

    public Categoria getCategoria() {
        return categoria;
    }

    public String getNombreCategoria() {
        return (categoria != null) ? categoria.getNombre() : nombreCategoria;
    }

    public String getUnidad() {
        return unidad;
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

    public void setNombre(String nombre) {
        if (Validador.esVacio(nombre)) {
            throw new IllegalArgumentException("Nombre invalido");
        }
        this.nombre = nombre.trim();
    }

    // Agregacion: si la categoria luego se elimina de la lista, el producto conserva nombreCategoria y su codigo
    public void setCategoria(Categoria categoria) {
        if (categoria == null) {
            throw new IllegalArgumentException("La categoria no puede ser nula");
        }
        this.categoria = categoria;
        this.nombreCategoria = categoria.getNombre();
    }

    public void desvincularCategoria() {
        if (this.categoria != null) {
            this.nombreCategoria = this.categoria.getNombre();
            this.categoria = null;
        }
    }

    public void setUnidad(String unidad) {
        if (!Validador.esUnidadValida(unidad)) {
            throw new IllegalArgumentException("Unidad invalida (" + Constante.U_UNIDAD + ", " + Constante.U_KILO + ", " + Constante.U_LITRO + ")");
        }
        this.unidad = unidad.trim().toUpperCase();
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
        }
        this.stock = stock;
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
