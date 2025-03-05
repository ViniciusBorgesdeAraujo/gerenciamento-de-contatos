import java.sql.Connection;
import java.sql.DriverManager;

public class Conexao {
    private static final String URL = "jdbc:mysql://localhost:3306/teste";
    private static final String USUARIO = "root";
    private static final String SENHA = "12345678";

    public static Connection getConexao() throws Exception {
        return DriverManager.getConnection(URL, USUARIO, SENHA);
    }   
}





















