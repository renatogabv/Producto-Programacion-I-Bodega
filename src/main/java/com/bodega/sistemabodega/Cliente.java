package com.bodega.sistemabodega;

public class Cliente extends EntidadComercial{
    private double fiado;
    
    public Cliente (String DNI, String nombre) {
        super(DNI, nombre);
        
    }
    public Cliente (String DNI, String nombre, String telefono) {
        super(DNI, nombre, telefono);
    }
    @Override
    public String toString() {
        return "Cliente{" +
                 "DNI=" + getDocumento() +
                 ", nombre=" + getNombre() +
                 ", telefono=" + getTelefono() +
                 ", fiado=" + fiado +
                    '}';
}
}