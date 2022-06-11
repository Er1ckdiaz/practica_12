
package bd_datos;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Ivan Luis Jimenez
 */
public class conexion {
        Connection con = null;
    public Connection conex (){
        try{
        Class.forName("com.mysql.jdbc.Driver");
        con= DriverManager.getConnection("jdbc:mysql://localhost/sd","root","");
            System.out.println("[Servidor BD] Conexion establecida");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return con;
    }
}
