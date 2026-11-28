package com.bodega.sistemabodega;

import com.bodega.sistemabodega.util.Validador;

public class Proveedor extends EntidadComercial {

private String rubro;

    public Proveedor(String ruc, String razonSocial, String rubro) {
        super(ruc, razonSocial);
        setRubro(rubro);
    }

    public Proveedor(String ruc, String razonSocial, String telefono, String rubro) {
        super(ruc, razonSocial, telefono);
        setRubro(rubro);
    }

    @Override
    public void setDocumento(String ruc) {
        String limpio = (ruc == null) ? null : ruc.trim();
        if (!Validador.esRuc(limpio)) {
            throw new IllegalArgumentException("RUC invalido, debe tener 11 digitos");
        }
        this.documento = limpio;
    }

    public void setRubro(String rubro) {
        if (Validador.esVacio(rubro)) {
            throw new IllegalArgumentException("Rubro invalido");
        }
        this.rubro = rubro.trim();
    }

    public String getRubro() {
        return rubro;
    }

    @Override
    public boolean validarDocumento() {
        return Validador.esRuc(documento);
    }

    @Override
    public String toString() {
        return "Proveedor{ "
                + "RUC = " + getDocumento()
                + ", razon social = " + getNombre()
                + ", telefono = " + (getTelefono() != null ? getTelefono() : "Sin telefono")
                + ", rubro = " + rubro
                + " }";
    }
}
