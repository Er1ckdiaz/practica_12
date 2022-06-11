package servera;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import org.apache.xmlrpc.*;


public class ServerA {
    /*
     *Atributos de Servidor RPC
     */

    WebServer serverRPC = new WebServer(81);
    ServerObject_RPC op = new ServerObject_RPC();

    public static void main(String[] args) {
        ServerA ob = new ServerA();
        ob.iniciar_RPC();
        ob.iniciar_RMI();

    }

    public void iniciar_RPC() {
        try {
            serverRPC.addHandler("ServerRPC", op);
            serverRPC.start();
            Write("RPC ejectandose correctamente");
        } catch (Exception e) {
            Write("[RPC]:" + e.getMessage());
        }

    }

    public void iniciar_RMI() {
        Registry reg;       
        try {            
            reg = LocateRegistry.createRegistry(1099);
            reg.rebind("ServerRMI", new ServerObject_RMI());
            Write("RMI ejectandose correctamente");
        } catch (Exception e) {
            Write("[RMI]:" + e.getMessage());
        }
    }

    public static void Write(String msg) {
        System.out.println("[Servidor]" + msg);
    }

}
