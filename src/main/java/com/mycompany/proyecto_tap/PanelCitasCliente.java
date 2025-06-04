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
        labelN.setText(cita.getCliente().getNombre() + " || " + cita.getCliente().getUsuario());
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

            labelC.setForeground(Color.WHITE);
            labelP.setForeground(Color.WHITE);


        } else if (fechaD.isBefore(LocalDateTime.now()) && !cita.isCitaPagada()) {
            this.setBackground(new Color(200, 0, 0));
            labelN.setForeground(Color.BLACK);
 
            labelC.setForeground(Color.BLACK);
            labelP.setForeground(Color.BLACK);
 

        } else if (fechaD.isAfter(LocalDateTime.now()) && cita.isCitaPagada() && cita.getAvancePago() == cita.getDoctor().getCostoConsulta()) {
            this.setBackground(new Color(120, 205, 230));
            labelN.setForeground(Color.BLACK);

            labelC.setForeground(Color.BLACK);
            labelP.setForeground(Color.BLACK);


        } else if (fechaD.isAfter(LocalDateTime.now()) && !cita.isCitaPagada() && cita.getAvancePago() < cita.getDoctor().getCostoConsulta()) {
            this.setBackground(new Color(255, 255, 153));
            labelN.setForeground(Color.BLACK);

            labelC.setForeground(Color.BLACK);
            labelP.setForeground(Color.BLACK);

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

        labelN = new javax.swing.JLabel();
        labelC = new javax.swing.JLabel();
        labelP = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();

        setBackground(java.awt.Color.white);

        labelN.setText("...");

        labelC.setText("...");

        labelP.setText("...");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelC, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelN, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(labelP, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(52, Short.MAX_VALUE))
            .addComponent(jSeparator1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelN)
                    .addComponent(labelC)
                    .addComponent(labelP))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel labelC;
    private javax.swing.JLabel labelN;
    private javax.swing.JLabel labelP;
    // End of variables declaration//GEN-END:variables
}
