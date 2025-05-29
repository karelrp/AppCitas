package com.mycompany.proyecto_tap;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Citaa implements Serializable {
    
    
    private int precio=500;
    private Personaa cliente;
    private Personaa doctor;
    
    private String fecha;
    private boolean citaPagada=false;
    private int avancePago;
    private int idcita;
    

    public Citaa() {
        
    }

    public Citaa(int precio,Personaa doctor,Personaa cliente, String fecha, int avancePago) {
        this.precio=precio;
        this.doctor = doctor;
        this.cliente=cliente;
        this.fecha=fecha;
        this.avancePago=avancePago;
        citaPagada=false;
        
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public Personaa getCliente() {
        return cliente;
    }

    public void setCliente(Personaa cliente) {
        this.cliente = cliente;
    }

    public Personaa getDoctor() {
        return doctor;
    }

    public void setDoctor(Personaa doctor) {
        this.doctor = doctor;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public boolean isCitaPagada() {
        return citaPagada; 
    }

    public void setCitaPagada(boolean citaPagada) {
        this.citaPagada = citaPagada;
    }

    public int getAvancePago() {
        return avancePago;
    }

    public void setAvancePago(int avancePago) {
        this.avancePago = avancePago;
    }

    public int getIdcita() {
        return idcita;
    }

    public void setIdcita(int idcita) {
        this.idcita = idcita;
    }

    

    
}