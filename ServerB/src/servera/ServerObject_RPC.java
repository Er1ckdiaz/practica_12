/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servera;

import bd_datos.conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;


public class ServerObject_RPC {

    conexion C = new conexion();
    Connection CC = C.conex();
    PreparedStatement us;

    Vector<String> tres_del = new Vector<String>();
    Vector<String> Delegaciones = new Vector<String>();
    int num_del;

    String mes;

    public Vector<String> mayor_year(int year) {
        tres_del.clear();
        Delegaciones.clear();
        int[] arreglo;
        int mayor1 = 0;
        int mayor2 = 0;
        int mayor3 = 0;
        int dele1 = 0;
        int dele2 = 0;
        int dele3 = 0;
        try {
            String consulta = "SELECT DISTINCT DELEGACION FROM datos WHERE YEAR=" + year;
            Statement st = CC.createStatement();
            ResultSet rs = st.executeQuery(consulta);
            int del = 0;
            while (rs.next()) {
                Delegaciones.add(del, rs.getString("DELEGACION"));
                del++;
            }

            arreglo = new int[del];
            for (int b = 0; b < arreglo.length; b++) {
                Statement st2 = CC.createStatement();
                ResultSet rs2 = st2.executeQuery("SELECT COUNT(DELITO) FROM datos WHERE DELEGACION LIKE '" + Delegaciones.get(b) + "' AND YEAR=" + year);
                while (rs2.next()) {
                    arreglo[b] = Integer.parseInt(rs2.getString("COUNT(DELITO)"));
                    if ((arreglo[b] >= mayor1) && (dele1 != b)) {
                        mayor1 = arreglo[b];
                        dele1 = b;
                    }                   
                }

            }
            for (int i = 0; i < arreglo.length; i++) {
                if((arreglo[i]< mayor1) && (arreglo[i]>mayor2) && (arreglo[i]!=mayor1)){
                    mayor2 = arreglo[i];
                    dele2 = i;
                }
            }
            
            for (int i = 0; i < arreglo.length; i++) {
                if(((arreglo[i] < mayor1) && (arreglo[i]<mayor2)) && (arreglo[i]>mayor3) && (arreglo[i]!=mayor1) && (arreglo[i]!=mayor2)){
                    mayor3 = arreglo[i];
                    dele3 = i;
                }
            }
            ServerA.Write("[RPC]Los mas grandes:" +dele1+" "+ mayor1 + " " +dele2+" "+ mayor2 + " " +dele3+" "+ mayor3);
            ServerA.Write(Delegaciones.get(dele1)+" "+Delegaciones.get(dele2)+" "+Delegaciones.get(dele3));
            tres_del.addElement(Delegaciones.get(dele1)+" ("+mayor1+")");
            tres_del.addElement(Delegaciones.get(dele2)+" ("+mayor2+")");
            tres_del.addElement(Delegaciones.get(dele3)+" ("+mayor3+")");            

        } catch (Exception e) {
            ServerA.Write("" + e.getMessage());
        }
        ServerA.Write("[RPC]El cliente ejecutó método A");
        return tres_del;
    }

    public int num_dele(String del, String tipo) {
        num_del=0;
        try {
            String consulta = "SELECT DELEGACION,COUNT(DELITO) FROM datos"
                    + " WHERE DELEGACION LIKE '" + del + "' AND DELITO LIKE '" + tipo + "'";
            Statement st = CC.createStatement();
            ResultSet rs = st.executeQuery(consulta);
            while (rs.next()) {
                num_del = Integer.parseInt(rs.getString("COUNT(DELITO)"));
            }

        } catch (Exception e) {
            ServerA.Write("[RPC]Error:" + e.getMessage());
        }
        ServerA.Write("[RPC]El cliente ejecutó método B");
        return num_del;
    }

    public String num_del(String delito) {
        mes = "Nada";
        int[] arreglo = new int[12];
        int mayor = 0;
        int mes_m = 0;
        Vector<String> meses = new Vector<String>();
        meses.addElement("Enero");
        meses.addElement("Febrero");
        meses.addElement("Marzo");
        meses.addElement("Abril");
        meses.addElement("Mayo");
        meses.addElement("Junio");
        meses.addElement("Julio");
        meses.addElement("Agosto");
        meses.addElement("Septiembre");
        meses.addElement("Octubre");
        meses.addElement("Noviembre");
        meses.addElement("Diciembre");
        try {
            for (int i = 0; i < 12; i++) {
                String consulta = "SELECT COUNT(DELITO) FROM datos WHERE DELITO LIKE '" + delito + "' AND MES=" + i;
                Statement st = CC.createStatement();
                ResultSet rs = st.executeQuery(consulta);

                while (rs.next()) {
                    arreglo[i] = Integer.parseInt(rs.getString("COUNT(DELITO)"));
                    if (arreglo[i] >= mayor) {
                        mayor = arreglo[i];
                        mes_m = i;
                    }
                }
            }

            if (mayor == 0) {
                mes = "Nada";
            } else {
                ServerA.Write("[RPC]Mes mayor:" + mes_m + " con " + mayor + " delitos");
                mes = meses.get(mes_m - 1);
            }
        } catch (Exception e) {
            ServerA.Write("[RPC]Error:" + e.getMessage());
        }
        return mes;
    }
}
