package com.bodega.sistemabodega;

import com.bodega.sistemabodega.util.Constante;
import com.bodega.sistemabodega.util.Formato;

public class Boleta {

    private String numeroBoleta;
    private Venta venta;

    public Boleta(Venta venta) {
        if (venta == null) {
            throw new IllegalArgumentException("La boleta requiere una venta válida");
        }
        this.venta = venta;
        this.numeroBoleta = Formato.numBoleta(venta.getNumero());
    }

    public String getNumeroBoleta() {
        return numeroBoleta;
    }

    public Venta getVenta() {
        return venta;
    }

    public double calcularSubtotalSinIgv() {
        return Formato.redondearDosD(venta.getTotal() / (1.0 + Constante.IGV));
    }

    public double calcularIgv() {
        return Formato.redondearDosD(venta.getTotal() - calcularSubtotalSinIgv());
    }

    public void imprimirBoleta() {
        System.out.println("\nBOLETA DE VENTA");
        System.out.println("N° Comprobante : " + numeroBoleta);
        System.out.println("Fecha y Hora   : " + Formato.fechaHora(venta.getFechaHora()));
        System.out.println("Cliente        : " + venta.getCliente().getNombre() + " (DNI: " + venta.getCliente().getDocumento() + ")");
        System.out.println("Tipo de Pago   : " + venta.getTipopago());
        for (DetalleVenta d : venta.getDetalles()) {
            System.out.println("- " + d);
        }
        System.out.println("Op. Gravada    : " + Formato.moneda(calcularSubtotalSinIgv()));
        System.out.println("IGV (" + (int) (Constante.IGV * 100) + "%)      : " + Formato.moneda(calcularIgv()));
        System.out.println("TOTAL          : " + Formato.moneda(venta.getTotal()));
        System.out.println("Pagado         : " + Formato.moneda(venta.getMontopagado()));
        System.out.println("Vuelto         : " + Formato.moneda(venta.calcularVuelto()));
    }
}
