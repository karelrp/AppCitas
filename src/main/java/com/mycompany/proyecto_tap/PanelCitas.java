/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.proyecto_tap;

import hola.Cita;
import hola.DBManager;
import hola.Persona;
import java.awt.Color;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author whois
 */
public class PanelCitas extends javax.swing.JPanel {

    private DBManager db = new DBManager();
    private Personaa cliente;
    private Personaa doctor;
    private int indexCD;
    private Citaa cita;
    ArrayList<Personaa> listPersonas = new ArrayList<>();
    ArrayList<Citaa> citasReservadas = new ArrayList<>();

    /**
     * Creates new form panelCitas
     */
    public PanelCitas() {
        initComponents();
    }

    public PanelCitas(Citaa cita, ArrayList<Personaa> listPersonas, int indexCD, Personaa doctor, int indexD, ArrayList<Citaa> citasReservadas) {
        cliente = cita.getCliente();
        this.cita = cita;
        this.doctor = doctor;
        initComponents();
        this.listPersonas = listPersonas;
        this.citasReservadas = citasReservadas;
        this.indexCD = indexCD;
        labelN.setText(cita.getCliente().getNombre() + " || " + cita.getCliente().getUsuario());
        labelC.setText(cita.getFecha());
        labelP.setText("$ " + cita.getPrecio());
        //bloqueo();
        color();
    }

    public void color() {

        //String patron = "yyyy-MM-dd'T'HH:mm";
        // Crea un formateador de fecha y hora con el patrón especificado
        //DateTimeFormatter formatter = DateTimeFormatter.ofPattern(patron);
        //System.out.println(cita.getFecha());
        // Parsea el string a LocalDateTime usando el formateador
        //LocalDateTime fecha = LocalDateTime.parse(cita.getFecha(), formatter);
        String fecha = cita.getFecha();
        System.out.println("fecha: " + fecha);
        String a = "", m = "", d = "", hora = "";

        boolean java = true;
        for (int i = 0; i < fecha.length(); i++) {
            if (fecha.charAt(i) == '-') {
                java = true;
                break;
            } else {
                java = false;
            }
        }

        if (!java) {
            for (int i = 0, g = 0; i < fecha.length() && g <= 3; i++) {
                if (fecha.charAt(i) != '/' && g == 0 && fecha.charAt(i) != ' ' && fecha.charAt(i) != '-' && fecha.charAt(i) != 'T') {
                    d = d + fecha.charAt(i);
                } else if (fecha.charAt(i) != '/' && g == 1 && fecha.charAt(i) != ' ' && fecha.charAt(i) != '-' && fecha.charAt(i) != 'T') {
                    m = m + fecha.charAt(i);
                } else if (fecha.charAt(i) != '/' && g == 2 && fecha.charAt(i) != ' ' && fecha.charAt(i) != '-' && fecha.charAt(i) != 'T') {
                    a = a + fecha.charAt(i);
                } else if (g == 3) {
                    hora = hora + fecha.charAt(i);
                } else {
                    g++;
                }
            }
        } else {
            for (int i = 0, g = 0; i < fecha.length() && g <= 3; i++) {
                if (fecha.charAt(i) != '/' && g == 0 && fecha.charAt(i) != ' ' && fecha.charAt(i) != '-') {
                    a = a + fecha.charAt(i);
                } else if (fecha.charAt(i) != '/' && g == 1 && fecha.charAt(i) != ' ' && fecha.charAt(i) != '-') {
                    m = m + fecha.charAt(i);
                } else if (fecha.charAt(i) != '/' && g == 2 && fecha.charAt(i) != ' ' && fecha.charAt(i) != '-' && fecha.charAt(i) != 'T') {
                    d = d + fecha.charAt(i);
                } else if (g == 3) {
                    hora = hora + fecha.charAt(i);
                } else {
                    g++;
                }
            }
        }

        DateTimeFormatter formatter;
        LocalDateTime fechaD = LocalDateTime.now();
        String horaF = "";
// Convertir a fecha
        for (int i = 0, k = 0; i < hora.length() && k < 2; i++) {
            if (hora.charAt(i) != ':') {
                horaF = horaF + hora.charAt(i);
                System.out.println("horaF: " + horaF);
            } else {
                if (k == 0) {
                    horaF = horaF + hora.charAt(i);
                }
                System.out.println("horaF: " + horaF);
                k++;
            }
        }
        fecha = a + "-" + m + "-" + d + "T" + horaF;
        formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        fechaD = LocalDateTime.parse(fecha, formatter);
        System.out.println(fechaD);

        if (cliente.isBloqueado()) {
            btnBloqueo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/btnBloqueo.png")));
            labelEstado.setText("Bloqueado");
        } else {

            btnBloqueo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/NoBloqueo.png")));

            if (fechaD.isBefore(LocalDateTime.now()) && cita.isCitaPagada() && cita.getAvancePago() == cita.getDoctor().getCostoConsulta()) {
                labelEstado.setText("Cita completada");
                btnPago.setEnabled(false);
                btnEliminar.setEnabled(false);
                btnBloqueo.setEnabled(false);
            } else if (fechaD.isBefore(LocalDateTime.now()) && cita.getAvancePago() != cita.getDoctor().getCostoConsulta()) {
                labelEstado.setText("Deudor moroso");
            } else if (fechaD.isAfter(LocalDateTime.now()) && cita.isCitaPagada() && cita.getAvancePago() == cita.getDoctor().getCostoConsulta()) {
                labelEstado.setText("Cita Liquidada");
                btnPago.setEnabled(false);
                btnEliminar.setEnabled(false);
                btnBloqueo.setEnabled(false);
            } else if (fechaD.isAfter(LocalDateTime.now()) && !cita.isCitaPagada() && cita.getAvancePago() < cita.getDoctor().getCostoConsulta()) {
                labelEstado.setText("Cita pendiente");
                btnEliminar.setEnabled(false);
                btnBloqueo.setEnabled(false);
            }
        }
    }

    public ArrayList<Personaa> getListPersonas() {
        return listPersonas;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        labelN = new javax.swing.JLabel();
        labelC = new javax.swing.JLabel();
        labelP = new javax.swing.JLabel();
        btnEliminar = new org.edisoncor.gui.panel.PanelImage();
        btnPago = new org.edisoncor.gui.panel.PanelImage();
        btnBloqueo = new org.edisoncor.gui.panel.PanelImage();
        jSeparator1 = new javax.swing.JSeparator();
        labelEstado = new javax.swing.JLabel();

        setBackground(java.awt.Color.white);

        labelN.setText("...");

        labelC.setText("...");

        labelP.setText("...");

        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/btnEliminar.png"))); // NOI18N
        btnEliminar.setPreferredSize(new java.awt.Dimension(20, 20));
        btnEliminar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnEliminarMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout btnEliminarLayout = new javax.swing.GroupLayout(btnEliminar);
        btnEliminar.setLayout(btnEliminarLayout);
        btnEliminarLayout.setHorizontalGroup(
            btnEliminarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );
        btnEliminarLayout.setVerticalGroup(
            btnEliminarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );

        btnPago.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/btnPagar.png"))); // NOI18N
        btnPago.setPreferredSize(new java.awt.Dimension(20, 20));
        btnPago.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnPagoMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout btnPagoLayout = new javax.swing.GroupLayout(btnPago);
        btnPago.setLayout(btnPagoLayout);
        btnPagoLayout.setHorizontalGroup(
            btnPagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );
        btnPagoLayout.setVerticalGroup(
            btnPagoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );

        btnBloqueo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/NoBloqueo.png"))); // NOI18N
        btnBloqueo.setPreferredSize(new java.awt.Dimension(20, 20));
        btnBloqueo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnBloqueoMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout btnBloqueoLayout = new javax.swing.GroupLayout(btnBloqueo);
        btnBloqueo.setLayout(btnBloqueoLayout);
        btnBloqueoLayout.setHorizontalGroup(
            btnBloqueoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );
        btnBloqueoLayout.setVerticalGroup(
            btnBloqueoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );

        labelEstado.setText("...");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelC, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelN, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelEstado, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelP, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnPago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnBloqueo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addComponent(jSeparator1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(labelC)
                        .addComponent(labelN)
                        .addComponent(labelP)
                        .addComponent(labelEstado))
                    .addComponent(btnPago, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEliminar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBloqueo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnEliminarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEliminarMouseClicked
        boolean repetir = false;
        cliente = cita.getCliente();
        String nombre;
        for (int i = 0; i < citasReservadas.size();) {
            if (citasReservadas.get(i).getCliente().getUsuario().equals(cliente.getUsuario())) {
                System.out.println("entró al primer if");
                nombre = citasReservadas.get(i).getCliente().getUsuario();

                if (cliente.getUsuario().equals(nombre)) {
                    try {
                        if (JOptionPane.showConfirmDialog(null, "Seguro") == JOptionPane.YES_OPTION) {
                            if (db.removeA(cita.getCliente().getUsuario()) > 0) {

                                citasReservadas.clear();
                                //usuario.setListArchivos(listArchivos);
                                //db.removeA(cita.getIdcita());
                                this.hide();
                                //System.out.println(usuario.getUsuario() + "  NOMBRE ARCHIVO: " + archivo.getNombre());
                                JOptionPane.showMessageDialog(null, "Eliminado");

                            } else {
                                JOptionPane.showMessageDialog(null, "No se eliminó");
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        System.out.println("date de bajaaaaa");
                        i = 1000000000;
                    }

                } else {

                    i++;
                }
                System.out.println("listo");
            } else {

                i++;
            }
            if (i == 0) {
                i++;
            }
            System.out.println("salio del if");
            System.out.println("cantidad de i: " + i);
        }
        System.out.println("listPersonas: " + listPersonas.size());
        for (int i = 0; i < listPersonas.size(); i++) {
            System.out.println("usuario en list: " + listPersonas.get(i).getUsuario());
            System.out.println("usuario en cliente: " + cliente.getUsuario());
            if (listPersonas.get(i).getUsuario().equals(cliente.getUsuario())) {
                System.out.println("entró al if para eliminar");
                try {
                    db.removeU(cliente.getIdpersona(), cliente);
                } catch (Exception e) {
                    System.out.println("error");
                }
                break;
            }
        }
        System.out.println("removio correctamente");
    }//GEN-LAST:event_btnEliminarMouseClicked

    private void btnPagoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnPagoMouseClicked
        RegistroPagoFrame registroPagoFrame = new RegistroPagoFrame(listPersonas, cliente, doctor, indexCD, cita, citasReservadas);
        registroPagoFrame.setVisible(true);
        listPersonas = registroPagoFrame.getListPersonas();
    }//GEN-LAST:event_btnPagoMouseClicked

    private void btnBloqueoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnBloqueoMouseClicked
        //JOptionPane.showMessageDialog(null,cliente.isBloqueado()+"");
        if (cliente.isBloqueado() == true) {

            try {
                if (db.update(cliente) > 0) {
                    JOptionPane.showMessageDialog(null, "USUARIO DESBLOQUEADO");
                    cliente.setBloqueado(false);
                }
            } catch (Exception e) {
                System.out.println("error 1");
            }

        } else if (cliente.isBloqueado() == false) {
            try {
                if (db.update(cliente) > 0) {
                    JOptionPane.showMessageDialog(null, "USUARIO BLOQUEADO");
                    cliente.setBloqueado(true);
                }
            } catch (Exception e) {
                System.out.println("error 2");
                e.printStackTrace();
            }

        }
        color();
    }//GEN-LAST:event_btnBloqueoMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private org.edisoncor.gui.panel.PanelImage btnBloqueo;
    private org.edisoncor.gui.panel.PanelImage btnEliminar;
    private org.edisoncor.gui.panel.PanelImage btnPago;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel labelC;
    private javax.swing.JLabel labelEstado;
    private javax.swing.JLabel labelN;
    private javax.swing.JLabel labelP;
    // End of variables declaration//GEN-END:variables
}
