import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BaseDeDatos {
    private static final String URL = "jdbc:sqlite:personas.db";

    public static Connection obtenerConexion() throws SQLException {
        return DriverManager.getConnection(URL);
    }

    // Crear tabla si no existe
    public static void inicializarBase() {
        String sql = "CREATE TABLE IF NOT EXISTS personas (" +
                     "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                     "nombre TEXT NOT NULL," +
                     "correo TEXT NOT NULL)";
        try (Connection conn = obtenerConexion();
             var stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}