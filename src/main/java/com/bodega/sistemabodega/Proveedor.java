
package com.bodega.sistemabodega;

public class Proveedor extends EntidadComercial{
    private String rubro;
    
    public Proveedor(String RUC, String razonSocial, String rubro) {
        super (RUC, razonSocial);
        setRubro(rubro);
        
    }
    public Proveedor(String RUC, String razonSocial, String telefono, String rubro){
        super(RUC, razonSocial, telefono);
        setRubro(rubro);
        
    }
    public void setRubro(String rubro){
        if (rubro == null || rubro.trim().isEmpty()) {
            throw new IllegalArgumentException("Rubro Invalido");
            
        }
        this.rubro = rubro;
    }

    public String getRubro() {
        return rubro;
    }
    @Override 
    public boolean validarDocumento() {
        return super.validarDocumento() && documento.matches("\\d{11}");

    }
        @Override
    public String toString() {
        return "Cliente{" +
                 "DNI=" + getDocumento() +
                 ", nombre=" + getNombre() +
                 ", telefono=" + getTelefono() +
                 ", rubro=" + rubro +
                    '}';
    }
}
