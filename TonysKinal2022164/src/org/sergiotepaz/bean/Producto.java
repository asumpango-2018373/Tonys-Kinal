package org.sergiotepaz.bean;

public class Producto {
    
    private int codigoProducto;
    private String nombreProducto;
    private int cantidadProducto;

    public Producto() {
    }

    public Producto(int codigoProducto, String nombreProducto, int cantidad) {
        this.codigoProducto = codigoProducto;
        this.nombreProducto = nombreProducto;
        this.cantidadProducto = cantidad;
    }

    public int getCodigoProducto() {
        return codigoProducto;
    }

    public void setCodigoProducto(int codigoProducto) {
        this.codigoProducto = codigoProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public int getCantidadProducto() {
        return cantidadProducto;
    }

    public void setCantidadProducto(int cantidad) {
        this.cantidadProducto = cantidad;
    }
   
    @Override
    public String toString() {
        return codigoProducto + " - " + nombreProducto;
    }
}
