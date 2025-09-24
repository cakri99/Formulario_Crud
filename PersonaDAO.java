import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonaDAO {

    public void agregarPersona(Persona persona) {
        String sql = "INSERT INTO personas(nombre, correo) VALUES(?, ?)";
        try (Connection conn = BaseDeDatos.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, persona.getNombre());
            stmt.setString(2, persona.getCorreo());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void actualizarPersona(Persona persona) {
        String sql = "UPDATE personas SET nombre=?, correo=? WHERE id=?";
        try (Connection conn = BaseDeDatos.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, persona.getNombre());
            stmt.setString(2, persona.getCorreo());
            stmt.setInt(3, persona.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void eliminarPersona(int id) {
        String sql = "DELETE FROM personas WHERE id=?";
        try (Connection conn = BaseDeDatos.obtenerConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Persona> obtenerPersonas() {
        List<Persona> lista = new ArrayList<>();
        String sql = "SELECT * FROM personas";
        try (Connection conn = BaseDeDatos.obtenerConexion();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Persona persona = new Persona(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("correo")
                );
                lista.add(persona);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
}