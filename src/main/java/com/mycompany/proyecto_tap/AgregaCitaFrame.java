/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.proyecto_tap;

import javax.swing.*;
import java.awt.*;
import java.time.*;
import java.util.ArrayList;
import java.io.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.mycompany.proyecto_tap.Citaa;
import com.mycompany.proyecto_tap.Personaa;
import com.toedter.calendar.JCalendar;
import hola.Cita;
import hola.DBManager;
import hola.Persona;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author whois
 */
public class AgregaCitaFrame extends javax.swing.JFrame {
    private DBManager db = new DBManager();
    private JComboBox<String> comboBoxHora;
    private JCalendar calendar;
    private ArrayList<Personaa> listPersonas = new ArrayList<>();
    private ArrayList<Citaa> citasReservadasC = new ArrayList<>();
    private ArrayList<Citaa> citasReservadasD = new ArrayList<>();
    private ArrayList<Citaa> citasReservadas = new ArrayList<>();
    private Personaa cliente = new Personaa();
    private Personaa doctor = new Personaa();
    int indexD, indexC;

    public AgregaCitaFrame() {
        initComponents();
        agrega();

    }

    public AgregaCitaFrame(Personaa doctor, int indexD, Personaa cliente, int indexC, ArrayList<Personaa> listPersonas, ArrayList<Citaa> citasReservadas) {
        initComponents();
        this.doctor = doctor;
        this.indexC = indexC;
        this.cliente = cliente;
        this.indexD = indexD;
        this.listPersonas = listPersonas;
        this.citasReservadas = citasReservadas;
        agrega();
        //cargarCitasReservadas();
        JFrame IniciarsesionFrame = new JFrame();

        // Establece el color de fondo
        //Color color = new Color(255, 211, 211);
        //IniciarsesionFrame.getContentPane().setBackground(color);
        // Actualiza el JFrame para que se muestren los cambios
        IniciarsesionFrame.repaint();
        getContentPane().setBackground(new Color(180, 205, 230));
    }

    private void agrega() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Disponibilidad de Citas");
        setLayout(new BorderLayout());

        // Inicializar lista de citas reservadas
        // citasReservadasC=citasReservadas();
        // Calendario para seleccionar la fecha
        calendar = new JCalendar();
        add(calendar, BorderLayout.CENTER);
        calendar.setBackground(new Color(180, 205, 230));

        // ComboBox para seleccionar la hora
        comboBoxHora = new JComboBox<>();
        for (int hora = 9; hora <= 17; hora++) {
            if (hora == 9) {
                comboBoxHora.addItem("0" + hora + ":00");
            } else {
                comboBoxHora.addItem(hora + ":00");
            }
        }
        add(comboBoxHora, BorderLayout.SOUTH);
        comboBoxHora.setBackground(new Color(180, 205, 230));
        // Botón para reservar cita
        JButton btnReservar = new JButton("Reservar Cita");
        btnReservar.addActionListener(e -> reservarCita());
        add(btnReservar, BorderLayout.NORTH);
        btnReservar.setBackground(new Color(180, 205, 230));
        pack();
        setLocationRelativeTo(null); // Centrar la ventana en la pantalla
        setVisible(true);
    }

    private void reservarCita() {

        LocalDate fechaSeleccionada = calendar.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        String horaSeleccionada = (String) comboBoxHora.getSelectedItem();
        System.out.println("primera x");
        // Combinar fecha y hora seleccionadas
        LocalDateTime citaSeleccionada = LocalDateTime.of(fechaSeleccionada, LocalTime.parse(horaSeleccionada));
        System.out.println("segunda x");
        LocalDate fechaActual = LocalDate.now();
        LocalDate fechaLimite = fechaActual.plusDays(30);
        System.out.println("tercerax");
        System.out.println("FL: " + fechaLimite);
        System.out.println("FA: " + fechaActual);
        System.out.println("FS: " + fechaSeleccionada);
        System.out.println("CS: " + citaSeleccionada);

        if (fechaSeleccionada.isAfter(fechaLimite)) {
            JOptionPane.showMessageDialog(this, "Solo se pueden reservar citas dentro de los próximos 30 días.");
            return;
        }
        if (fechaSeleccionada.isBefore(LocalDate.now())) {
            JOptionPane.showMessageDialog(this, "Esta fecha ya pasó. Por favor, selecciona otra fecha");
            return;
        }
        // Comprobar si la cita ya está reservada
        if (citasReservadas.contains(citaSeleccionada)) {
            JOptionPane.showMessageDialog(this, "Esta cita ya está reservada. Por favor, selecciona otra fecha u hora.");
            return;
        }
        //String a = "", m = "", d = "", hora = "";
        for (int i = 0; i < citasReservadas.size(); i++) {
            System.out.println("CR: " + citasReservadas.get(i).getFecha());
            boolean java = true;
            for (int k = 0; k < citasReservadas.get(i).getFecha().length(); k++) {
                if (citasReservadas.get(i).getFecha().charAt(k) == '-') {
                    java = true;
                    break;
                } else {
                    java = false;
                }
            }
            String fecha = citasReservadas.get(i).getFecha();
            String a = "", m = "", d = "", hora = "";
            if (!java) {
                //String a = "", m = "", d = "", hora = "";
                for (int h = 0, g = 0; h < fecha.length() && g <= 3; h++) {
                    if (fecha.charAt(h) != '/' && g == 0 && fecha.charAt(h) != ' ' && fecha.charAt(h) != '-' && fecha.charAt(h) != 'T') {
                        d = d + fecha.charAt(h);
                        System.out.println("d: " + d);
                    } else if (fecha.charAt(h) != '/' && g == 1 && fecha.charAt(h) != ' ' && fecha.charAt(h) != '-' && fecha.charAt(h) != 'T') {
                        m = m + fecha.charAt(h);
                        System.out.println("m: " + m);
                    } else if (fecha.charAt(h) != '/' && g == 2 && fecha.charAt(h) != ' ' && fecha.charAt(h) != '-' && fecha.charAt(h) != 'T') {
                        a = a + fecha.charAt(h);
                        System.out.println("a: " + a);
                    } else if (g == 3) {
                        hora = hora + fecha.charAt(h);
                        System.out.println("h: " + hora);
                    } else {
                        g++;
                    }
                }
                System.out.println("a: " + a);
                System.out.println("m: " + m);
                System.out.println("d: " + d);

                DateTimeFormatter formatter;
                LocalDateTime fechaD = LocalDateTime.now();
                String horaF = "";
// Convertir a fecha
                for (int l = 0, f = 0; l < hora.length() && f < 2; l++) {
                    if (hora.charAt(l) != ':') {
                        horaF = horaF + hora.charAt(l);
                        System.out.println("horaF: " + horaF);
                    } else {
                        if (f == 0) {
                            horaF = horaF + hora.charAt(l);
                        }
                        System.out.println("horaF: " + horaF);
                        f++;
                    }
                }
                System.out.println("a2: " + a);
                System.out.println("m2: " + m);
                System.out.println("d2: " + d);
                System.out.println("horaF: " + horaF);
                String horaFF=" ";
                switch (horaF) {
                    case "01:00":
                        horaFF = "13:00";
                        break;
                    case "02:00":
                        horaFF = "14:00";
                        break;
                    case "03:00":
                        horaFF = "15:00";
                        break;
                    case "04:00":
                        horaFF = "16:00";
                        break;
                    case "05:00":
                        horaFF = "17:00";
                        break;
                    default:
                        horaFF=horaF;
                }
                System.out.println("antes: "+horaF+" y ahora: "+horaFF);
                fecha = a + "-" + m + "-" + d + "T" + horaFF;
                System.out.println("fecha: " + fecha);
                formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
                fechaD = LocalDateTime.parse(fecha, formatter);
                if (fechaD.equals(citaSeleccionada)) {
                    JOptionPane.showMessageDialog(this, "Esta cita ya está reservada. Por favor, selecciona otra fecha u hora.");
                    return;
                } else {
                    System.out.println("continuamos con la siguiente cita");
                }
            } else {
                if (citaSeleccionada.equals(citasReservadas.get(i).getFecha())) {
                    JOptionPane.showMessageDialog(this, "Esta cita ya está reservada. Por favor, selecciona otra fecha u hora.");
                    return;
                } else {
                    System.out.println("continuamos con la siguiente cita");
                }
            }
        }

        if (fechaSeleccionada.getDayOfWeek() == DayOfWeek.SUNDAY) {
            JOptionPane.showMessageDialog(this, "No se pueden reservar citas en domingo.");
            return;
        }
        for (int i = 0; i < citasReservadas.size(); i++) {
            if (citasReservadas.get(i).getCliente().equals(cliente)) {
                JOptionPane.showMessageDialog(null, "No se puede reservar cita si ya tiene una");
                return;
            }

        }

        //System.out.println("5 x");
        Citaa cita1 = new Citaa();
        cita1.setDoctor(doctor);
        cita1.setCliente(cliente);
        System.out.println("6 x");
        cita1.setPrecio(doctor.getCostoConsulta());
        cita1.setFecha("" + citaSeleccionada);
        // citasReservadasC.add(cita1);
        //citasReservadasD.add(cita1);
        //citasReservadas.add(cita1);

        listPersonas.set(indexD, doctor);
        listPersonas.set(indexC, cliente);

        //guardarCitasReservadas(); // Guardar las citas reservadas
        
        //cambiar(7);*/
        try {
                    if (db.addC(cita1) > 0) {
                        citasReservadas.add(cita1);
                        JOptionPane.showMessageDialog(this, "Cita reservada con éxito para el " + citaSeleccionada);
                    } else {
                        JOptionPane.showMessageDialog(null, "No se agregó");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "        ");

                }
        JOptionPane.showMessageDialog(this, "Cita reservada con éxito para el " + citaSeleccionada);
        dispose();
    }

    public ArrayList<String> cambiar() {
        ArrayList<String> citasString = new ArrayList<>();
        for (int i = 0; i < citasReservadas.size(); i++) {
            citasString.add("" + citasReservadas.get(i));
        }
        System.out.println(citasString.toString());
        return citasString;
    }

    public ArrayList<Citaa> getCitasReservadas() {
        return citasReservadas;
    }

    public void setCitasReservadas(ArrayList<Citaa> citasReservadas) {
        this.citasReservadas = citasReservadas;
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

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AgregaCitaFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AgregaCitaFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AgregaCitaFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AgregaCitaFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AgregaCitaFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
