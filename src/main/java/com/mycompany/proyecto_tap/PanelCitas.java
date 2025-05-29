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
        labelN.setText(cita.getCliente().getNombre() + " " + cita.getCliente().getApellido());
        labelU.setText(cita.getCliente().getUsuario());
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

        // Imprime el resultado
        System.out.println(fechaD);
        if (cliente.isBloqueado()) {
            btnBloqueo.setSelected(true);
            this.setBackground(Color.BLACK);
            labelN.setForeground(Color.WHITE);
            labelU.setForeground(Color.WHITE);
            labelC.setForeground(Color.WHITE);
            labelP.setForeground(Color.WHITE);
            jLabel2.setForeground(Color.WHITE);
            jLabel3.setForeground(Color.WHITE);
            jLabel6.setForeground(Color.WHITE);
            jLabel5.setForeground(Color.WHITE);
        } else if (fechaD.isBefore(LocalDateTime.now()) && cita.isCitaPagada() && cita.getAvancePago() == cita.getDoctor().getCostoConsulta()) {
            this.setBackground(new Color(204, 255, 204));
            labelN.setForeground(Color.BLACK);
            labelU.setForeground(Color.BLACK);
            labelC.setForeground(Color.BLACK);
            labelP.setForeground(Color.BLACK);
            jLabel2.setForeground(Color.BLACK);
            jLabel3.setForeground(Color.BLACK);
            jLabel6.setForeground(Color.BLACK);
            jLabel5.setForeground(Color.BLACK);
            btnPago.setEnabled(false);
            btnEliminar.setEnabled(false);
            btnBloqueo.setEnabled(false);
        } else if (fechaD.isBefore(LocalDateTime.now()) && cita.getAvancePago() != cita.getDoctor().getCostoConsulta()) {
            this.setBackground(new Color(150, 0, 0));
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
            btnPago.setEnabled(false);
            btnEliminar.setEnabled(false);
            btnBloqueo.setEnabled(false);
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
            btnEliminar.setEnabled(false);
            btnBloqueo.setEnabled(false);
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
        btnEliminar = new javax.swing.JButton();
        btnBloqueo = new javax.swing.JToggleButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        labelU = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        labelC = new javax.swing.JLabel();
        labelP = new javax.swing.JLabel();
        btnPago = new javax.swing.JButton();

        labelN.setText("...");

        btnEliminar.setText("🗑");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnBloqueo.setText("🚫");
        btnBloqueo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBloqueoActionPerformed(evt);
            }
        });

        jLabel2.setText("PACIENTE:");

        jLabel3.setText("USUARIO: ");

        labelU.setText("...");

        jLabel5.setText("HORA CITA:");

        jLabel6.setText("PRECIO: ");

        labelC.setText("...");

        labelP.setText("...");

        btnPago.setText("💵");
        btnPago.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPagoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelN, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelU, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelP, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(24, 24, 24))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelC, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBloqueo, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnPago, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(10, 10, 10))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelN)
                            .addComponent(jLabel2)
                            .addComponent(jLabel5)
                            .addComponent(labelC))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnPago)
                        .addGap(18, 18, 18)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(labelU)
                        .addComponent(btnBloqueo)
                        .addComponent(jLabel6)
                        .addComponent(labelP)))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        /*int indexC=0;
        System.out.println("listP:"+listPersonas.size());
        for(int i=0;i<listPersonas.size();i++){
            if(listPersonas.get(i).getUsuario().equals(cita.getCliente().getUsuario())){
                indexC=i;
                break;
            }
        }*/
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
                        i=1000000000;
                    }
                    
                } else {

                    i++;
                }
                System.out.println("listo");
            } else {

                i++;
            }
            if(i==0)
                i++;
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
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnBloqueoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBloqueoActionPerformed
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
    }//GEN-LAST:event_btnBloqueoActionPerformed

    private void btnPagoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPagoActionPerformed
        RegistroPagoFrame registroPagoFrame = new RegistroPagoFrame(listPersonas, cliente, doctor, indexCD, cita, citasReservadas);
        registroPagoFrame.setVisible(true);
        listPersonas = registroPagoFrame.getListPersonas();
    }//GEN-LAST:event_btnPagoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton btnBloqueo;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnPago;
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
