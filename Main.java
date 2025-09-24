import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        BaseDeDatos.inicializarBase(); // crea la tabla si no existe
        SwingUtilities.invokeLater(() -> {
            new FormPersona().setVisible(true);
        });
    }
}
