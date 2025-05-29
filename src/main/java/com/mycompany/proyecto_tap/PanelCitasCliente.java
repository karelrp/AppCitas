/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.mycompany.proyecto_tap;


import hola.Cita;
import hola.Persona;
import java.awt.Color;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author whois
 */
public class PanelCitasCliente extends javax.swing.JPanel {

    private Personaa cliente;
    private Personaa doctor;
    private Citaa cita;

    /**
     * Creates new form PanelCitasCliente
     */
    public PanelCitasCliente() {
        initComponents();
    }

    public PanelCitasCliente(Citaa cita) {
        this.cita = cita;
        cliente = cita.getCliente();
        doctor = cita.getDoctor();
        initComponents();
        labelN.setText(cita.getCliente().getNombre() + " " + cita.getCliente().getApellido());
        labelU.setText(cita.getCliente().getUsuario());
        labelC.setText("" + cita.getFecha());
        labelP.setText("$ " + cita.getPrecio());
        color();
    }

    public void color() {
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
       for (int i = 0, k=0; i < hora.length() && k<2; i++) {
              if(hora.charAt(i)!=':'){
                horaF = horaF + hora.charAt(i);
                System.out.println("horaF: "+horaF);
              }
              else{
                if(k==0)
                    horaF = horaF + hora.charAt(i);
                System.out.println("horaF: "+horaF);
                  k++;
              }
        }
        fecha = a + "-" + m + "-" + d + "T" + horaF;
        formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        fechaD = LocalDateTime.parse(fecha, formatter);

        if (fechaD.isBefore(LocalDateTime.now()) && cita.isCitaPagada() && cita.getAvancePago() == cita.getDoctor().getCostoConsulta()) {
            this.setBackground(new Color(204, 255, 204));
            labelN.setForeground(Color.WHITE);
            labelU.setForeground(Color.WHITE);
            labelC.setForeground(Color.WHITE);
            labelP.setForeground(Color.WHITE);
            jLabel2.setForeground(Color.WHITE);
            jLabel3.setForeground(Color.WHITE);
            jLabel6.setForeground(Color.WHITE);
            jLabel5.setForeground(Color.WHITE);

        } else if (fechaD.isBefore(LocalDateTime.now()) && !cita.isCitaPagada()) {
            this.setBackground(new Color(200, 0, 0));
            labelN.setForeground(Color.BLACK);
            labelU.setForeground(Color.BLACK);
            labelC.setForeground(Color.BLACK);
            labelP.setForeground(Color.BLACK);
            jLabel2.setForeground(Color.BLACK);
            jLabel3.setForeground(Color.BLACK);
            jLabel6.setForeground(Color.BLACK);
            jLabel5.setForeground(Color.BLACK);

        } else if (fechaD.isAfter(LocalDateTime.now()) && cita.isCitaPagada() && cita.getAvancePago() == cita.getDoctor().getCostoConsulta()) {
            this.setBackground(new Color(120, 205, 230));
            labelN.setForeground(Color.BLACK);
            labelU.setForeground(Color.BLACK);
            labelC.setForeground(Color.BLACK);
            labelP.setForeground(Color.BLACK);
            jLabel2.setForeground(Color.BLACK);
            jLabel3.setForeground(Color.BLACK);
            jLabel6.setForeground(Color.BLACK);
            jLabel5.setForeground(Color.BLACK);

        } else if (fechaD.isAfter(LocalDateTime.now()) && !cita.isCitaPagada() && cita.getAvancePago() < cita.getDoctor().getCostoConsulta()) {
            this.setBackground(new Color(255, 255, 153));
            labelN.setForeground(Color.BLACK);
            labelU.setForeground(Color.BLACK);
            labelC.setForeground(Color.BLACK);
            labelP.setForeground(Color.BLACK);
            jLabel2.setForeground(Color.BLACK);
            jLabel3.setForeground(Color.BLACK);
            jLabel6.setForeground(Color.BLACK);
            jLabel5.setForeground(Color.BLACK);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        labelN = new javax.swing.JLabel();
        labelU = new javax.swing.JLabel();
        labelC = new javax.swing.JLabel();
        labelP = new javax.swing.JLabel();

        jLabel2.setText("NOMBRE:");

        jLabel3.setText("USUARIO: ");

        jLabel5.setText("HORA CITA:");

        jLabel6.setText("PRECIO: ");

        labelN.setText("...");

        labelU.setText("...");

        labelC.setText("...");

        labelP.setText("...");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelU, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelN, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(150, 150, 150)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addGap(150, 150, 150)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(labelC, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE)
                    .addComponent(labelP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel5)
                    .addComponent(labelN)
                    .addComponent(labelC))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel6)
                    .addComponent(labelU)
                    .addComponent(labelP))
                .addContainerGap(14, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel labelC;
    private javax.swing.JLabel labelN;
    private javax.swing.JLabel labelP;
    private javax.swing.JLabel labelU;
    // End of variables declaration//GEN-END:variables
}
