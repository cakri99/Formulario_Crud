import java.awt.*;
import java.awt.event.*;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class FormPersona extends JFrame {
    private JTextField txtId, txtNombre, txtCorreo;
    private final JButton btnAñadir, btnModificar, btnBorrar, btnLimpiar;
    private JTable tabla;
    private DefaultTableModel modelo;
    private final PersonaDAO personaDAO;

    public FormPersona() {
        personaDAO = new PersonaDAO();
        setTitle("Formulario CRUD");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Formulario
        JPanel panelFormulario = new JPanel(new GridLayout(3, 2, 5, 5));
        panelFormulario.setBorder(BorderFactory.createTitledBorder("Datos de la Persona"));

        panelFormulario.add(new JLabel("ID:"));
        txtId = new JTextField();
        txtId.setEnabled(false);
        panelFormulario.add(txtId);

        panelFormulario.add(new JLabel("Nombre:"));
        txtNombre = new JTextField();
        panelFormulario.add(txtNombre);

        panelFormulario.add(new JLabel("Correo:"));
        txtCorreo = new JTextField();
        panelFormulario.add(txtCorreo);

        // Botones
        btnAñadir = new JButton("Añadir");
        btnModificar = new JButton("Modificar");
        btnBorrar = new JButton("Borrar");
        btnLimpiar = new JButton("Limpiar");

        JPanel panelBotones = new JPanel();
        panelBotones.add(btnAñadir);
        panelBotones.add(btnModificar);
        panelBotones.add(btnBorrar);
        panelBotones.add(btnLimpiar);

        // Tabla
        modelo = new DefaultTableModel(new Object[]{"ID", "Nombre", "Correo"}, 0);
        tabla = new JTable(modelo);
        JScrollPane scroll = new JScrollPane(tabla);

        setLayout(new BorderLayout());
        add(panelFormulario, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);

        cargarTabla();

        // Eventos
        btnAñadir.addActionListener(e -> añadir());
        btnModificar.addActionListener(e -> modificar());
        btnBorrar.addActionListener(e -> borrar());
        btnLimpiar.addActionListener(e -> limpiar());

        tabla.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int fila = tabla.getSelectedRow();
                if (fila != -1) {
                    txtId.setText(modelo.getValueAt(fila, 0).toString());
                    txtNombre.setText(modelo.getValueAt(fila, 1).toString());
                    txtCorreo.setText(modelo.getValueAt(fila, 2).toString());
                }
            }
        });
    }

    private void cargarTabla() {
        modelo.setRowCount(0);
        List<Persona> lista = personaDAO.obtenerPersonas();
        for (Persona persona : lista) {
            modelo.addRow(new Object[]{persona.getId(), persona.getNombre(), persona.getCorreo()});
        }
    }

    private void añadir() {
        if (txtNombre.getText().isEmpty() || txtCorreo.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Complete todos los campos.");
            return;
        }
        personaDAO.agregarPersona(new Persona(txtNombre.getText(), txtCorreo.getText()));
        cargarTabla();
        limpiar();
    }

    private void modificar() {
        if (txtId.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Seleccione una fila.");
            return;
        }
        Persona persona = new Persona(
                Integer.parseInt(txtId.getText()),
                txtNombre.getText(),
                txtCorreo.getText()
        );
        personaDAO.actualizarPersona(persona);
        cargarTabla();
        limpiar();
    }

    private void borrar() {
        if (txtId.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Seleccione una fila.");
            return;
        }
        personaDAO.eliminarPersona(Integer.parseInt(txtId.getText()));
        cargarTabla();
        limpiar();
    }

    private void limpiar() {
        txtId.setText("");
        txtNombre.setText("");
        txtCorreo.setText("");
        tabla.clearSelection();
    }
}