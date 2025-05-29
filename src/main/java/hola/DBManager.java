/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hola;

import com.mycompany.proyecto_tap.Citaa;
import com.mycompany.proyecto_tap.Personaa;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author papay
 */
public class DBManager {

    private String db = "proyecto";
    private String user = "root";
    private String password = null;
    private Connection connection;
    private Statement stm;

    public DBManager() {    
        
    }

    private void open() throws Exception{
     connection=DriverManager.getConnection(
     "jdbc:mysql://localhost:3306/"+db, user, password
     );  
     stm=connection.createStatement();
   }

    private void close() throws Exception {
        stm.close();
        connection.close();
    }

    public Personaa find(String usr, String pass) throws Exception {
        
        String sql = "SELECT * FROM persona WHERE usuario=? AND contrasena=sha1(?)";
        Personaa usuario = null;
        
        open();
        PreparedStatement ps=connection.prepareStatement(sql);
       ps.setString(1, usr);
       ps.setString(2,pass);
       
       ResultSet rs=ps.executeQuery();
        
        if (rs.next()) {
            usuario = new Personaa();
            usuario.setIdpersona(rs.getInt("idpersona"));
            usuario.setNombre(rs.getString("nombre"));
            usuario.setApellido(rs.getString("apellido"));
            usuario.setUsuario(rs.getString("usuario"));
            usuario.setContrasena(rs.getString("contrasena"));
            usuario.setDoctor(1 == rs.getShort("doctor"));
            usuario.setBloqueado(rs.getBoolean("bloqueado"));
            usuario.setCostoConsulta(rs.getInt("costoConsulta"));
            
        }
        
        close();
        
        
        return usuario;
    }

    public ArrayList<Personaa> llenarP(ArrayList<Citaa> citasReservadas) throws Exception {
         ArrayList<Personaa> listPersonas = new ArrayList<>();
         Personaa p =null;
        String sql = "SELECT * FROM persona";
        open();
       

        ResultSet rs = stm.executeQuery(sql);
        while (rs.next()) {
             p = new Personaa(rs.getString("nombre"), rs.getString("apellido"),
                    rs.getString("contrasena"), rs.getString("usuario"), rs.getBoolean("doctor"),
                    rs.getBoolean("bloqueado"));
            p.setCostoConsulta(rs.getInt("costoConsulta"));
            p.setIdpersona(rs.getInt("idpersona"));

            listPersonas.add(p);
        }

        close();
        llenarC(listPersonas,citasReservadas);

        return listPersonas;
    }

    public void llenarC(ArrayList<Personaa> listPersonas,ArrayList<Citaa> citasReservadas) throws Exception {
        //JOptionPane.showMessageDialog(null,listPersonas.get(2).getUsuario());
        String sql = "SELECT * FROM cita";
        Citaa a=null;
        int c=0;
        citasReservadas.clear();
        open();

        ResultSet rs = stm.executeQuery(sql);

        while (rs.next()) {
            a = new Citaa();
            a.setPrecio(rs.getInt("precio"));
            a.setAvancePago(rs.getInt("avancePago"));
            a.setCitaPagada(rs.getBoolean("citaPagada"));
            a.setFecha(rs.getString("fecha"));
            a.setIdcita(rs.getInt("idcita"));
            String cliente = rs.getString("cliente");
            String doctor = rs.getString("doctor");
            //System.out.println(listPersonas.size()+"");
            
            for (int i = 0; i < listPersonas.size(); i++) {
                //System.out.println("i="+i+" "+"dc="+doctor+"   cl="+cliente);
                if (listPersonas.get(i).getUsuario().equals(cliente)) {
                    //System.out.println("lp="+listPersonas.get(i).getUsuario()+"   "+cliente);
                    a.setCliente(listPersonas.get(i));
                }
                if (listPersonas.get(i).getUsuario().equals(doctor)) {
                   // System.out.println("lp="+listPersonas.get(i).getUsuario()+"   "+doctor);
                    a.setDoctor(listPersonas.get(i));
                }
                
                if (a.getCliente() != null && a.getDoctor() != null) {
                    
                    c++;
                    //System.out.println("cita add="+c);
                }
               
            }
            citasReservadas.add(a);
            System.out.println("list: "+citasReservadas.size());
            for (int i = 0; i < citasReservadas.size(); i++) {
                System.out.println("entro a ver las citas");
                System.out.println("list: "+citasReservadas.get(i));
            }
        }
        close();
        
    }

    public int addC(Citaa cita) throws Exception {
        String sql = "INSERT INTO cita VALUES(NULL,'";
        sql += cita.getCliente().getUsuario() + "','" + cita.getDoctor().getUsuario() + "','" + cita.getFecha() 
                + "'," + cita.isCitaPagada() + "," + cita.getAvancePago() + "," + cita.getPrecio() + ");";
        
        open();
        int resultado = stm.executeUpdate(sql);
        close();
        return resultado;
    }

    public int addU(Personaa persona) throws Exception {
        open();

        String sql = "INSERT INTO persona VALUES(NULL,'";
        sql += persona.getNombre() + "','" + persona.getApellido() + "',sha1('" + persona.getContrasena() + "'),'" + persona.getUsuario()
                + "'," + persona.isDoctor() + "," + persona.isBloqueado() + "," + persona.getCostoConsulta() + ");";
        int resultado = stm.executeUpdate(sql);
        close();
        return resultado;
    }

    public int removeA(String id) throws Exception {
        open();
        String sql = "DELETE FROM cita WHERE cliente='" + id+"';";

        int resultado = stm.executeUpdate(sql);
        close();
        return resultado;
    }

    public int removeU(int id, Personaa usuario) throws Exception {
        open();
        String sql = "DELETE FROM persona WHERE idpersona=" + id;

        int resultado = stm.executeUpdate(sql);
        close();
        return resultado;
    }

    public int update(Personaa persona) throws Exception {
        boolean b;
        if(persona.isBloqueado())
            b=false;
        else
            b=true;
        
        System.out.println("idpersona="+persona.getIdpersona());
        String sql = "UPDATE persona SET  bloqueado=? WHERE idpersona=?";
        open();
        
        PreparedStatement ps=connection.prepareStatement(sql);
        ps.setBoolean(1,b);
        ps.setInt(2, persona.getIdpersona());
        int resultado = ps.executeUpdate();
        close();
        return resultado;
    }
}
