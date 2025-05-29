/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto_tap;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 *
 * @author whois
 */
public class Personaa implements Serializable{
    private int costoConsulta=500;
   
    private String nombre;
    private String apellido;
    private String contrasena;
    private String usuario;
    private boolean doctor;
    private boolean bloqueado;
    private int idpersona;
 
   

    public Personaa() {
        bloqueado=false;
        doctor=false;
        
    }

    public Personaa( String nombre, String apellido, String contrasena, String usuario, boolean doctor, boolean bloqueado) {
        //this.id=id;
        this.nombre = nombre;
        this.apellido=apellido;
        this.contrasena = contrasena;
        this.usuario = usuario;
        this.doctor= doctor;
        this.bloqueado=bloqueado;
      
        
    }
    
   
    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    
    public boolean isDoctor() {
        return doctor;
    }

    public void setDoctor(boolean doctor) {
        this.doctor = doctor;
    }

 

    public boolean isBloqueado() {
        return bloqueado;
    }

    public void setBloqueado(boolean bloqueado) {
        this.bloqueado = bloqueado;
    }

    public int getCostoConsulta() {
        return costoConsulta;
    }

    public void setCostoConsulta(int costoConsulta) {
        this.costoConsulta = costoConsulta;
    }
    
    

    @Override
    public String toString() {
        return "Cliente{" +" nombre=" + nombre + ", apellido=" + apellido + ", contrase\u00f1a=" + contrasena + ", usuario=" + usuario + '}';
    }

    public String getMontoPlan() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public int getIdpersona() {
        return idpersona;
    }

    public void setIdpersona(int idpersona) {
        this.idpersona = idpersona;
    }
    
}