package testeback;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConexaoBD {

   static {
      try {
         Class.forName("com.mysql.jdbc.Driver");
      } 
      catch (ClassNotFoundException e) {
         throw new RuntimeException(e);
      }
   }

   public Connection conectar() throws SQLException {
      String servidor = "127.0.0.1";
      String porta = "3306";
      String database = "bdback";
      String usuario = "root";
      String senha = "Jesus-cristo10";
      return DriverManager
         	.getConnection("jdbc:mysql://"+servidor+":"+porta+
            "/"+database+"?user="+usuario+"&password="+senha);
   }
   public static void desconectar(Connection conn) throws SQLException {
      conn.close();
   }
}