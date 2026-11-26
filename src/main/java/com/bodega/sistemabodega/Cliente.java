package com.bodega.sistemabodega;

public class Cliente extends EntidadComercial{
    private double fiado;
    
    public Cliente (String DNI, String nombre) {
        super(DNI, nombre);
        this.fiado = 0.0;
    }
    public Cliente (String DNI, String nombre, String telefono) {
        super(DNI, nombre, telefono);
        this.fiado = 0.0;
        
    }
    
    public double getFiado(){
        
        return fiado;
    }
    public void setFiado(double fiado) {
        if (fiado <0) {
            throw new IllegalArgumentException("Fiado NO puede ser negativo");
            
        }
        this.fiado = fiado;
        
    }
    @Override
    public boolean validarDocumento() {
        return super.validarDocumento() && documento.matches("\\d{8}");
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