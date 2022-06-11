/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client_rpc.rmi;

import Frame.Ventana_D;
import Stub.Stub;
import Frame.Ventana_E;
import java.io.IOException;
import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;
import java.util.Vector;
import org.apache.xmlrpc.XmlRpcClient;

/
public class Client_RPCRMI {

    public static void main(String[] args) throws IOException {
        Write("Conectando . . .");
        int estado = conectar_A();
        if (estado == 3) {
             Write("Finalizó correctamente");
        } else {
            if(estado == 2){
                int estado2 = conectar_B();
                if(estado2 == 3){
                    Write("Finalizó correctamente");
                }else{
                    
                }
            }
           
        }
    }

    public static int conectar_A() {
        int estado = 0;
        String ruta_RPC = "http://localhost:81/";
        String ruta_RMI = "rmi://localhost:1099/ServerRMI";
        try {
            XmlRpcClient cliente = new XmlRpcClient(ruta_RPC);
            Registry reg = LocateRegistry.getRegistry("localhost", 1099);
            Stub cliente2 = (Stub) Naming.lookup(ruta_RMI);
            estado = 5;
            Write("Conectado a servidor A");
            /*
             *Vectores para almacenar los argumentos a enviar al servidor RPC
             */
            Vector<Integer> entero = new Vector<Integer>();
            Vector<String> cadena = new Vector<String>();
            Vector<String> resultados = new Vector<String>();

            /*
             *Vectores para almacenar los resultados de RMI
             */
            Vector<Vector<String>> cuadran = new Vector<Vector<String>>();
            Vector<Vector<String>> coxy = new Vector<Vector<String>>();

            /*
             *Atributos para leer del teclado para el menú
             */
            Scanner opcion1 = new Scanner(System.in);
            Scanner opcion2 = new Scanner(System.in);
            int op1 = 0;

            while (!(op1 == 3)) {
                if (op1 == 3) {
                    estado = 3;
                }
                Write("Seleccione la opción deceada\n [3] para salir"
                        + "\n Servidor RPC (1)"
                        + "\n Servidor RMI (2)");
                op1 = Integer.parseInt(opcion1.nextLine());
                /*
                 /RPC
                 */
                if (op1 == 1) {
                    Write("Conectado a Servidor RPC");
                    Write("Servidor RPC:\nSeleccione la opción deceada:"
                            + "\n (A) Tres delegaciones con mayor número de delitos en un año\n"
                            + "determinado."
                            + "\n (B) Número de delitos en una delegación determinada y de un tipo de delito\n"
                            + "determinado."
                            + "\n (C) Mes del año con mayor número de un delito determinado");
                    String op2 = opcion2.nextLine();
                    if (op2.equals("A")) {
                        Write("Teclee el año:");
                        int elemento = opcion2.nextInt();
                        entero.addElement(elemento);
                        Object result = cliente.execute("ServerRPC.mayor_year", entero);
                        resultados = (Vector) result;
                        if (resultados.isEmpty()) {
                            Write("No se encontraron datos");
                        } else {

                            Write("Las tres delegaciones con mayor delito en el año:"+elemento+"\n(A)::" + resultados.get(0)
                                    + "\n(B)::" + resultados.get(1)
                                    + "\n(C)::" + resultados.get(2));

                        }
                        entero.clear();
                        resultados.clear();
                    }
                    if (op2.equals("B")) {
                        Write("Teclee la delegación:");
                        String del = opcion2.nextLine();
                        Write("Teclee el tipo:");
                        String tipo = opcion2.nextLine();
                        cadena.addElement(del);
                        cadena.addElement(tipo);
                        int resuli = 0;
                        Object result = cliente.execute("ServerRPC.num_dele", cadena);
                        resuli = (Integer) result;
                        if (resuli == 0) {
                            Write("No se encontraron datos");
                        } else {
                            Write(cadena.get(0) + " tiene " + resuli + " delitos de " + cadena.get(1));
                        }
                        cadena.clear();
                        resultados.clear();
                    }
                    if (op2.equals("C")) {
                        Write("Teclee el tipo de delito:");
                        String tipo = opcion2.nextLine();
                        cadena.addElement(tipo);
                        String resuli = "";
                        Object result = cliente.execute("ServerRPC.num_del", cadena);
                        resuli = result.toString();
                        if (resuli.equals("Nada") || resuli == null) {
                            Write("No se encontraron datos");
                        } else {
                            Write("El mes con mayor delito de tipo " + tipo+" es:"+resuli);
                        }
                        cadena.clear();
                        resultados.clear();
                    }
                }
                /*
                 *RMI
                 */
                if (op1 == 2) {
                    Scanner read = new Scanner(System.in);
                    Write("Conectado a Servidor RMI");
                    Write("Servidor RMI:\nSeleccione la opción deceada:"
                            + "\n (D) Datos(fecha,colonia,tipo) de una CoordX y CoordY determinada" 
                            + "\n (E) Datos(hora,calles,delegación,tipo) de un cuadrante determinado");
                    String op2 = read.nextLine();
                    if (op2.equals("D")) {
                        Write("Teclee Coordenada X:");
                        String cx = read.nextLine();
                        Write("Teclee Coordenada Y:");
                        String cy = read.nextLine();
                        coxy = cliente2.Dcuad(cx, cy);
                        Ventana_D send = new Ventana_D(coxy);
                        send.coord_r(cx, cy);
                        send.visualizar();
                    }
                    if (op2.equals("E")) {
                        Write("Teclee el cuadrante");
                        String cuadrante = read.nextLine();
                        cuadran = cliente2.Dcoor(cuadrante);

                        Ventana_E send = new Ventana_E(cuadran);
                        send.cuadrante_r(cuadrante);
                        send.visualizar();

                        cuadran.clear();
                    }
                }
            }

        } catch (Exception e) {
            //System.out.println("Error " + e.getMessage());
            Write("No se pudo conectar al servidor A");
            Write("Conectando al servidor B");
            estado = 2;
        }

        return estado;
    }

    public static int conectar_B() {
        int estado = 0;
        String ruta_RPC = "http://192.168.43.156:81/";
        String ruta_RMI = "rmi://192.168.43.156:1099/ServerRMI";
        try {
            XmlRpcClient cliente = new XmlRpcClient(ruta_RPC);
            //Registry reg = LocateRegistry.getRegistry("192.168.1.73", 5000);
            Registry reg = LocateRegistry.getRegistry("192.168.43.156", 1099);
            Stub cliente2 = (Stub) Naming.lookup(ruta_RMI);
            estado =5;
            Write("Conectado a servidor B");
            /*
             *Vectores para almacenar los argumentos a enviar al servidor RPC
             */
            Vector<Integer> entero = new Vector<Integer>();
            Vector<String> cadena = new Vector<String>();
            Vector<String> resultados = new Vector<String>();

            /*
             *Vectores para almacenar los resultados de RMI
             */
            Vector<Vector<String>> cuadran = new Vector<Vector<String>>();
            Vector<Vector<String>> coxy = new Vector<Vector<String>>();

            /*
             *Atributos para leer del teclado para el menú
             */
            Scanner opcion1 = new Scanner(System.in);
            Scanner opcion2 = new Scanner(System.in);
            int op1 = 0;

            while (!(op1 == 3)) {
                if (op1 == 3) {
                    estado =3;
                }
                Write("Seleccione la opción deceada\n [3] para salir"
                        + "\n Servidor RPC (1)"
                        + "\n Servidor RMI (2)");
                op1 = Integer.parseInt(opcion1.nextLine());
                /*
                 /RPC
                 */
                if (op1 == 1) {
                    Write("Conectado a Servidor RPC");
                    Write("Servidor RPC:\nSeleccione la opción deceada:"
                            + "\n (A) Tres delegaciones con mayor número de delitos en un año\n"
                            + "determinado."
                            + "\n (B) Número de delitos en una delegación determinada y de un tipo de delito\n"
                            + "determinado."
                            + "\n (C) Mes del año con mayor número de un delito determinado");
                    String op2 = opcion2.nextLine();
                    if (op2.equals("A")) {
                        Write("Teclee el año:");
                        int elemento = opcion2.nextInt();
                        entero.addElement(elemento);
                        Object result = cliente.execute("ServerRPC.mayor_year", entero);
                        resultados = (Vector) result;
                        if (resultados.isEmpty()) {
                            Write("No se encontraron datos");
                        } else {

                            Write("Las tres delegaciones son:\n1::" + resultados.get(0)
                                    + "\n2::" + resultados.get(1)
                                    + "\n3::" + resultados.get(2));

                        }
                        entero.clear();
                        resultados.clear();
                    }
                    if (op2.equals("B")) {
                        Write("Teclee la delegación:");
                        String del = opcion2.nextLine();
                        Write("Teclee el tipo:");
                        String tipo = opcion2.nextLine();
                        cadena.addElement(del);
                        cadena.addElement(tipo);
                        int resuli = 0;
                        Object result = cliente.execute("ServerRPC.num_dele", cadena);
                        resuli = (Integer) result;
                        if (resuli == 0) {
                            Write("No se encontraron datos");
                        } else {
                            Write(cadena.get(0) + " tiene " + resuli + " delitos de " + cadena.get(1));
                        }
                        cadena.clear();
                        resultados.clear();
                    }
                    if (op2.equals("C")) {
                        Write("Teclee el tipo de delito:");
                        String tipo = opcion2.nextLine();
                        cadena.addElement(tipo);
                        String resuli = "";
                        Object result = cliente.execute("ServerRPC.num_del", cadena);
                        resuli = result.toString();
                        if (resuli.equals("Nada") || resuli == null) {
                            Write("No se encontraron datos");
                        } else {
                           Write("El mes con mayor delito de tipo " + tipo+" es:"+resuli);
                        }
                        cadena.clear();
                        resultados.clear();
                    }
                }
                /*
                 *RMI
                 */
                if (op1 == 2) {
                    Scanner read = new Scanner(System.in);
                    Write("Conectado a Servidor RMI");
                    Write("Servidor RMI:\nSeleccione la opción deceada:"
                            + "\n (D) Datos(fecha,colonia,tipo) de una CoordX y CoordY determinada" 
                            + "\n (E) Datos(hora,calles,delegación,tipo) de un cuadrante determinado");
                    String op2 = read.nextLine();
                    if (op2.equals("D")) {
                        Write("Teclee Coordenada X:");
                        String cx = read.nextLine();
                        Write("Teclee Coordenada Y:");
                        String cy = read.nextLine();
                        coxy = cliente2.Dcuad(cx, cy);
                        Ventana_D send = new Ventana_D(coxy);
                        send.coord_r(cx, cy);
                        send.visualizar();
                    }
                    if (op2.equals("E")) {
                        Write("Teclee el cuadrante");
                        String cuadrante = read.nextLine();
                        cuadran = cliente2.Dcoor(cuadrante);

                        Ventana_E send = new Ventana_E(cuadran);
                        send.cuadrante_r(cuadrante);
                        send.visualizar();

                        cuadran.clear();
                    }
                }
            }

        } catch (Exception e) {
            //System.out.println("Error " + e.getMessage());
            Write("No se pudo conectar al servidor B");
            estado = 2;
        }
        return estado;
    }

    public static void Write(String msg) {
        System.out.println(msg);
    }

}
