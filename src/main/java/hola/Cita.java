/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hola;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author papay
 */
@Entity
@Table(name = "cita")
@NamedQueries({
    @NamedQuery(name = "Cita.findAll", query = "SELECT c FROM Cita c"),
    @NamedQuery(name = "Cita.findByIdcita", query = "SELECT c FROM Cita c WHERE c.idcita = :idcita"),
    @NamedQuery(name = "Cita.findByCliente", query = "SELECT c FROM Cita c WHERE c.cliente = :cliente"),
    @NamedQuery(name = "Cita.findByDoctor", query = "SELECT c FROM Cita c WHERE c.doctor = :doctor"),
    @NamedQuery(name = "Cita.findByFecha", query = "SELECT c FROM Cita c WHERE c.fecha = :fecha"),
    @NamedQuery(name = "Cita.findByCitaPagada", query = "SELECT c FROM Cita c WHERE c.citaPagada = :citaPagada"),
    @NamedQuery(name = "Cita.findByAvancePago", query = "SELECT c FROM Cita c WHERE c.avancePago = :avancePago"),
    @NamedQuery(name = "Cita.findByPrecio", query = "SELECT c FROM Cita c WHERE c.precio = :precio")})
public class Cita implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idcita")
    private Integer idcita;
    @Column(name = "cliente")
    private String cliente;
    @Column(name = "doctor")
    private String doctor;
    @Column(name = "fecha")
    private String fecha;
    @Column(name = "citaPagada")
    private Boolean citaPagada;
    @Column(name = "avancePago")
    private Integer avancePago;
    @Column(name = "precio")
    private Integer precio;

    public Cita() {
    }

    public Cita(Integer idcita) {
        this.idcita = idcita;
    }

    public Integer getIdcita() {
        return idcita;
    }

    public void setIdcita(Integer idcita) {
        this.idcita = idcita;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Boolean getCitaPagada() {
        return citaPagada;
    }

    public void setCitaPagada(Boolean citaPagada) {
        this.citaPagada = citaPagada;
    }

    public Integer getAvancePago() {
        return avancePago;
    }

    public void setAvancePago(Integer avancePago) {
        this.avancePago = avancePago;
    }

    public Integer getPrecio() {
        return precio;
    }

    public void setPrecio(Integer precio) {
        this.precio = precio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idcita != null ? idcita.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cita)) {
            return false;
        }
        Cita other = (Cita) object;
        if ((this.idcita == null && other.idcita != null) || (this.idcita != null && !this.idcita.equals(other.idcita))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hola.Cita[ idcita=" + idcita + " ]";
    }
    
}
