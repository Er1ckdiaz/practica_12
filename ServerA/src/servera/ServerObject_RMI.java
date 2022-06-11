/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servera;

import bd_datos.conexion;
import java.rmi.server.UnicastRemoteObject;
import Stub.Stub;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.Vector;


public class ServerObject_RMI extends UnicastRemoteObject implements Stub {
    /*
     *Parametros para la conexion y consultas a la base de datos
     */

    conexion C = new conexion();
    Connection CC = C.conex();
    PreparedStatement us;

    /*
     *Vectores para almacenar los resultados
     */
    Vector<Vector<String>> Dcoo = new Vector<Vector<String>>();
    Vector<String> Dcua = new Vector<String>();
    Vector<Vector<String>> vv = new Vector<Vector<String>>();

    /*
     *Vectores para método E
     */
    Vector<String> hora = new Vector<String>();
    Vector<String> calle1 = new Vector<String>();
    Vector<String> calle2 = new Vector<String>();
    Vector<String> delegacion = new Vector<String>();
    Vector<String> delito = new Vector<String>();

    /*
     *Vectores para método D    
     */
    Vector<String> fecha = new Vector<String>();
    Vector<String> colonia = new Vector<String>();
    Vector<String> tipo = new Vector<String>();

    public ServerObject_RMI() throws java.rmi.RemoteException {
        super();
    }

    @Override
    public Vector<Vector<String>> Dcoor(String cuadrante) throws RemoteException {
        String HORA, CALLE1, CALLE2, DELEGACION, DELITO;
        limpiar_E();
        try {
            String consulta = "SELECT HORA, CALLE1, CALLE2, DELEGACION, DELITO FROM datos WHERE CUADRANTE='" + cuadrante + "'";
            Statement st = CC.createStatement();
            ResultSet rs = st.executeQuery(consulta);
            while (rs.next()) {
                HORA = rs.getString("HORA");
                CALLE1 = rs.getString("CALLE1");
                CALLE2 = rs.getString("CALLE2");
                DELEGACION = rs.getString("DELEGACION");
                DELITO = rs.getString("DELITO");

                hora.addElement(HORA);
                calle1.addElement(CALLE1);
                calle2.addElement(CALLE2);
                delegacion.addElement(DELEGACION);
                delito.addElement(DELITO);

                Dcua.addElement((HORA + " |"
                        + "             " + CALLE1 + ""
                        + "             | " + CALLE2 + " "
                        + "             | " + DELEGACION + " "
                        + "             | " + DELITO));
            }
            for (int i = 0; i < Dcua.size(); i++) {
                ServerA.Write(Dcua.get(i));
            }

            vv.addElement(hora);
            vv.addElement(calle1);
            vv.addElement(calle2);
            vv.addElement(delegacion);
            vv.addElement(delito);
            
           
        } catch (Exception e) {
            ServerA.Write("Error [RMI]" + e.getMessage());
        }
        ServerA.Write("[RMI] Cliente ejecutó método E");
        return vv;        
    }
    
    public void limpiar_E(){
        hora.clear();
        calle1.clear();
        calle2.clear();
        delegacion.clear();
        delito.clear();
    }

    @Override
    public Vector<Vector<String>> Dcuad(String CoordX, String CoordY) throws RemoteException {
        limpiar_D();
        try {
            String consulta = "SELECT FECHA, COLONIA, DELITO FROM datos WHERE COORDX='"+CoordX+"' AND COORDY='"+CoordY+"'";
            Statement st = CC.createStatement();
            ResultSet rs = st.executeQuery(consulta);
            while (rs.next()) {
                fecha.addElement(rs.getString("FECHA"));
                colonia.addElement(rs.getString("COLONIA"));
                tipo.addElement(rs.getString("DELITO"));
            }
            Dcoo.addElement(fecha);
            Dcoo.addElement(colonia);
            Dcoo.addElement(tipo);
            
            for (int i = 0; i < fecha.size(); i++) {
                ServerA.Write(fecha.get(i));
            }
            
        } catch (Exception e) {
            ServerA.Write("Error [RMI] método D:"+e.getMessage());
        }
        ServerA.Write("[RMI] Cliente ejecutó método D");
        return Dcoo;
    }
    
    public void limpiar_D(){
        fecha.clear();
        colonia.clear();
        tipo.clear();
    }
}
