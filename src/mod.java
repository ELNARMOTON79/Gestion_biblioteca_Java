import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JComboBox;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.ImageIcon;
import javax.swing.JSeparator;

public class mod extends JFrame {

    private final JPanel contentPanel = new JPanel();
    private JPanel contentPane;
    private JTable table;
    private Connection Conexion = null;
    DefaultTableModel ModeloT = new DefaultTableModel() {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false; // Bloquear todas las celdas para edición
        }
    };
    private JTextField txt_titulo;
    private JTextField txt_autor;
    private JTextField txt_anio;
    private JTextField txt_precio;
    private JTextField txt_stock;
    DefaultComboBoxModel<String> ModeloC = new DefaultComboBoxModel<>();
    JComboBox CmbGenero = new JComboBox(ModeloC);

    public static void main(String[] args) {
        try {
            mod frame = new mod();
            frame.setVisible(true);
            // Driver
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public mod() {
        setUndecorated(true);
        CargarGeneros();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 955, 509);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(255, 255, 255));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        this.setLocationRelativeTo(null);

        JLabel lbl_mod = new JLabel("Modificación de Libros");
        lbl_mod.setBounds(370, 2, 371, 31);
        lbl_mod.setForeground(new Color(0, 0, 160));
        lbl_mod.setFont(new Font("Arial Black", Font.BOLD, 26));
        lbl_mod.setBackground(Color.WHITE);
        lbl_mod.setHorizontalAlignment(SwingConstants.CENTER);
        contentPane.add(lbl_mod);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(261, 45, 681, 454);
        contentPane.add(scrollPane);

        ModeloT.addColumn("Titulo");
        ModeloT.addColumn("Autor");
        ModeloT.addColumn("Año Publicacion");
        ModeloT.addColumn("Genero");
        ModeloT.addColumn("Precio");
        ModeloT.addColumn("Stock");

        table = new JTable();
        table.setModel(ModeloT);
        table.getTableHeader().setReorderingAllowed(false); // Bloquear el reordenamiento de columnas
        scrollPane.setViewportView(table);

        // Ajustar automáticamente el ancho de las columnas "Titulo" y "Autor"
        table.getColumnModel().getColumn(0).setMinWidth(50); // Establecer un ancho mínimo para la columna "Titulo"
        table.getColumnModel().getColumn(0).setMaxWidth(350); // Establecer un ancho máximo para la columna "Titulo"
        table.getColumnModel().getColumn(0).setPreferredWidth(200); // Establecer un ancho preferido inicial para la columna "Titulo"
		table.getColumnModel().getColumn(1).setMinWidth(180); // Establecer un ancho mínimo para la columna "Autor"
		table.getColumnModel().getColumn(1).setMaxWidth(200); // Establecer un ancho máximo para la columna "Autor"
		table.getColumnModel().getColumn(1).setPreferredWidth(150); // Establecer un ancho preferido inicial para la columna "Autor"
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); // Desactivar el ajuste automático del ancho de las columnas
		table.doLayout(); // Actualizar el diseño de la tabla
		table.getColumnModel().getColumn(0).setPreferredWidth(table.getColumnModel().getColumn(1).getPreferredWidth()); // Ajustar el ancho preferido de la columna "Titulo" al contenido
		table.getColumnModel().getColumn(1).setPreferredWidth(table.getColumnModel().getColumn(2).getPreferredWidth()); // Ajustar el ancho preferido de la columna "Autor" al contenido


        txt_titulo = new JTextField();
        txt_titulo.setEnabled(false);
        txt_titulo.addKeyListener(new KeyAdapter() {
        	@Override
        	public void keyTyped(KeyEvent e) {
        		int texto = e.getKeyChar();
				
				boolean Mayusculas = texto >= 65 && texto <=90;
				boolean Minusculas = texto >= 97 && texto <=122;
				boolean Espacio = texto == 32;
				
				if(!(Minusculas || Mayusculas || Espacio))
				{
					e.consume();
				}
				
				
				if(txt_titulo.getText().length() >= 50)
				{
					Toolkit.getDefaultToolkit().beep();
					e.consume();
				}
        	}
        });
        txt_titulo.setColumns(10);
        txt_titulo.setBounds(71, 129, 168, 19);
        contentPane.add(txt_titulo);

        JLabel lbl_titulo = new JLabel("Titulo:");
        lbl_titulo.setHorizontalAlignment(SwingConstants.CENTER);
        lbl_titulo.setForeground(new Color(255, 255, 255));
        lbl_titulo.setFont(new Font("Arial Black", Font.BOLD, 14));
        lbl_titulo.setBackground(Color.WHITE);
        lbl_titulo.setBounds(3, 121, 64, 31);
        contentPane.add(lbl_titulo);

        txt_autor = new JTextField();
        txt_autor.setEnabled(false);
        txt_autor.addKeyListener(new KeyAdapter() {
        	@Override
        	public void keyTyped(KeyEvent e) {
        		int texto = e.getKeyChar();
				
				boolean Mayusculas = texto >= 65 && texto <=90;
				boolean Minusculas = texto >= 97 && texto <=122;
				boolean Espacio = texto == 32;
				
				if(!(Minusculas || Mayusculas || Espacio))
				{
					e.consume();
				}
				
				
				if(txt_autor.getText().length() >= 50)
				{
					Toolkit.getDefaultToolkit().beep();
					e.consume();
				}
        	}
        });
        txt_autor.setColumns(10);
        txt_autor.setBounds(71, 169, 168, 19);
        contentPane.add(txt_autor);

        JLabel lbl_autor = new JLabel("Autor:");
        lbl_autor.setHorizontalAlignment(SwingConstants.CENTER);
        lbl_autor.setForeground(new Color(255, 255, 255));
        lbl_autor.setFont(new Font("Arial Black", Font.BOLD, 14));
        lbl_autor.setBackground(Color.WHITE);
        lbl_autor.setBounds(0, 161, 64, 31);
        contentPane.add(lbl_autor);

        txt_anio = new JTextField();
        txt_anio.setEnabled(false);
        txt_anio.addKeyListener(new KeyAdapter() {
        	@Override
        	public void keyTyped(KeyEvent e) {
        		char c = e.getKeyChar(); // Obtener el carácter ingresado por el usuario
		        if (!Character.isDigit(c)) { // Verificar si el carácter no es un dígito
		            e.consume(); // Consumir el evento para evitar que se ingrese el carácter no válido
		        }
		        
		        if (txt_anio.getText().length() >= 4) { // Verificar si la longitud del texto en el campo de año es mayor o igual a 4
		            JOptionPane.showMessageDialog(null, "Solo se aceptan 4 dígitos"); // Mostrar un cuadro de diálogo con el mensaje de error
		            Toolkit.getDefaultToolkit().beep();
		            e.consume(); // Consumir el evento para evitar que se ingrese más de 4 dígitos
		        }
        	}
        });
        txt_anio.setColumns(10);
        txt_anio.setBounds(146, 209, 93, 19);
        contentPane.add(txt_anio);

        JLabel lbl_id_3 = new JLabel("Año Publicación:");
        lbl_id_3.setHorizontalAlignment(SwingConstants.CENTER);
        lbl_id_3.setForeground(new Color(255, 255, 255));
        lbl_id_3.setFont(new Font("Arial Black", Font.BOLD, 14));
        lbl_id_3.setBackground(Color.WHITE);
        lbl_id_3.setBounds(0, 201, 148, 31);
        contentPane.add(lbl_id_3);

        JLabel lbl_genero = new JLabel("Genero:");
        lbl_genero.setHorizontalAlignment(SwingConstants.CENTER);
        lbl_genero.setForeground(new Color(255, 255, 255));
        lbl_genero.setFont(new Font("Arial Black", Font.BOLD, 14));
        lbl_genero.setBackground(Color.WHITE);
        lbl_genero.setBounds(3, 243, 72, 31);
        contentPane.add(lbl_genero);

        txt_precio = new JTextField();
        txt_precio.setEnabled(false);
        txt_precio.addKeyListener(new KeyAdapter() {
        	public void keyTyped(KeyEvent e) {
        		char c = e.getKeyChar(); // Obtener el carácter ingresado por el usuario
		        if (!Character.isDigit(c)) { // Verificar si el carácter no es un dígito
		            e.consume(); // Consumir el evento para evitar que se ingrese el carácter no válido
		        }
		        
		        if (txt_precio.getText().length() >= 4) { // Verificar si la longitud del texto en el campo de año es mayor o igual a 4
		            JOptionPane.showMessageDialog(null, "Solo se aceptan 4 dígitos"); // Mostrar un cuadro de diálogo con el mensaje de error
		            Toolkit.getDefaultToolkit().beep();
		            e.consume(); // Consumir el evento para evitar que se ingrese más de 4 dígitos
		        }
        	}
        });
        txt_precio.setColumns(10);
        txt_precio.setBounds(85, 288, 96, 19);
        contentPane.add(txt_precio);

        JLabel lbl_precio = new JLabel("Precio:");
        lbl_precio.setHorizontalAlignment(SwingConstants.CENTER);
        lbl_precio.setForeground(new Color(255, 255, 255));
        lbl_precio.setFont(new Font("Arial Black", Font.BOLD, 14));
        lbl_precio.setBackground(Color.WHITE);
        lbl_precio.setBounds(0, 280, 72, 31);
        contentPane.add(lbl_precio);

        JLabel lbl_stock = new JLabel("Stock:");
        lbl_stock.setHorizontalAlignment(SwingConstants.CENTER);
        lbl_stock.setForeground(new Color(255, 255, 255));
        lbl_stock.setFont(new Font("Arial Black", Font.BOLD, 14));
        lbl_stock.setBackground(Color.WHITE);
        lbl_stock.setBounds(0, 321, 64, 31);
        contentPane.add(lbl_stock);

        txt_stock = new JTextField();
        txt_stock.setEnabled(false);
        txt_stock.addKeyListener(new KeyAdapter() {
        	@Override
        	public void keyTyped(KeyEvent e) {
        		char c = e.getKeyChar(); // Obtener el carácter ingresado por el usuario
		        if (!Character.isDigit(c)) { // Verificar si el carácter no es un dígito
		            e.consume(); // Consumir el evento para evitar que se ingrese el carácter no válido
		        }
		        
		        if (txt_stock.getText().length() >= 4) { // Verificar si la longitud del texto en el campo de año es mayor o igual a 4
		            JOptionPane.showMessageDialog(null, "Solo se aceptan 4 dígitos"); // Mostrar un cuadro de diálogo con el mensaje de error
		            Toolkit.getDefaultToolkit().beep();
		            e.consume(); // Consumir el evento para evitar que se ingrese más de 4 dígitos
		        }
        	}
        });
        txt_stock.setColumns(10);
        txt_stock.setBounds(71, 329, 96, 19);
        contentPane.add(txt_stock);
        
        JButton btnX = new JButton("X");
        btnX.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		dispose();
        	}
        });
        btnX.setForeground(Color.WHITE);
        btnX.setFont(new Font("Arial Black", Font.BOLD, 18));
        btnX.setBorderPainted(false);
        btnX.setBorder(null);
        btnX.setBackground(Color.RED);
        btnX.setBounds(897, 0, 45, 39);
        contentPane.add(btnX);
        
        
		CmbGenero.setBounds(84, 250, 125, 20);
		contentPane.add(CmbGenero);
		CmbGenero.setEnabled(false);
		CmbGenero.setBackground(new Color(255, 255, 255));
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 0, 160));
		panel.setBounds(0, 0, 261, 509);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setIcon(new ImageIcon(mod.class.getResource("/src/LogoMini (1).png")));
		lblNewLabel.setBounds(48, 11, 157, 50);
		panel.add(lblNewLabel);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 78, 241, 11);
		panel.add(separator);
		
		JButton BtnVolver = new JButton("Volver");
		BtnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
        		BarraBusquedaAdmin Admin = new BarraBusquedaAdmin();
        		Admin.setVisible(true);
			}
		});
		BtnVolver.setForeground(new Color(0, 0, 160));
		BtnVolver.setFont(new Font("Arial Black", Font.BOLD, 18));
		BtnVolver.setBorderPainted(false);
		BtnVolver.setBorder(null);
		BtnVolver.setBackground(new Color(255, 255, 255));
		BtnVolver.setBounds(5, 461, 118, 37);
		panel.add(BtnVolver);
		
		JButton btn_delate = new JButton("Eliminar");
		btn_delate.setBounds(10, 380, 113, 31);
		panel.add(btn_delate);
		btn_delate.setBackground(new Color(255, 255, 255));
		btn_delate.setForeground(new Color(0, 0, 160));
		btn_delate.setEnabled(false);
		btn_delate.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		    	int respuesta = JOptionPane.showInternalConfirmDialog(null, "Desea Borrar El Registro", "Advertencia",
		    			JOptionPane.WARNING_MESSAGE,JOptionPane.YES_NO_OPTION);
		    	if(respuesta == JOptionPane.YES_OPTION)
		    	{
		    		Statement sentencia = null;
		            int regEliminados = 0;
		            
		            try {
		                Conexion = DriverManager.getConnection("jdbc:ucanaccess://base/Libreria.accdb");
		                sentencia = Conexion.createStatement();

		                // Eliminar el registro de la base de datos
		                String titulo = txt_titulo.getText();

		                String consulta = "DELETE FROM Libreria WHERE Titulo = '" + titulo + "'";

		                regEliminados = sentencia.executeUpdate(consulta);

		                if (regEliminados >= 1) {
		                    JOptionPane.showMessageDialog(null, "El registro se ha eliminado", "Éxito",
		                            JOptionPane.INFORMATION_MESSAGE);

		                    // Eliminar la fila correspondiente en la tabla
		                    int rowCount = ModeloT.getRowCount();
		                    for (int i = 0; i < rowCount; i++) {
		                        String currentTitulo = ModeloT.getValueAt(i, 0).toString();
		                        if (currentTitulo.equals(titulo)) {
		                            ModeloT.removeRow(i);
		                            break;
		                        }
		                        txt_titulo.setText("");
		                        txt_autor.setText("");
		                        txt_anio.setText("");
		                        txt_precio.setText("");
		                        txt_stock.setText("");
		                    }
		                } else {
		                    JOptionPane.showMessageDialog(null, "El registro no se ha eliminado", "Error",
		                            JOptionPane.ERROR_MESSAGE);
		                }

		                Conexion.close();
		            } catch (Exception e1) {
		                JOptionPane.showMessageDialog(null, "Ocurrió un error: " + e1.toString(), "Error",
		                        JOptionPane.ERROR_MESSAGE);
		            }
		        }else
		        {
		        	JOptionPane.showMessageDialog(null, "Operacion Cancelada", "Cancelado",
		                    JOptionPane.INFORMATION_MESSAGE);
		        }
		    }
		});
		btn_delate.setFont(new Font("Arial Black", Font.BOLD, 14));
		
		        JButton btn_mod = new JButton("Modificar");
		        btn_mod.setBounds(133, 380, 113, 31);
		        panel.add(btn_mod);
		        btn_mod.setBackground(new Color(255, 255, 255));
		        btn_mod.setForeground(new Color(0, 0, 160));
		        btn_mod.setEnabled(false);
		        btn_mod.addActionListener(new ActionListener() {
		            public void actionPerformed(ActionEvent e) {
		            	
		            	if(txt_titulo.getText().equals("") || txt_autor.getText().equals("") || txt_anio.getText().equals("") || txt_precio.getText().equals("") || txt_stock.getText().equals(""))
		                {
		                	JOptionPane.showMessageDialog(null, "Porfavor No deje campos vacios", "Éxito",
		                            JOptionPane.INFORMATION_MESSAGE);
		                }
		                else
		                {
		                	Statement sentencia = null;
		                    int regModificados = 0;
		                    
		                    try {
		                        Conexion = DriverManager.getConnection("jdbc:ucanaccess://base/Libreria.accdb");
		                        sentencia = Conexion.createStatement();

		                        // Modificar el registro en la base de datos
		                        String titulo = txt_titulo.getText();
		                        String autor = txt_autor.getText();
		                        String año = txt_anio.getText();
		                        String genero = CmbGenero.getSelectedItem().toString();
		                        String precio = txt_precio.getText();
		                        String stock = txt_stock.getText();

		                        String consulta = "UPDATE Libreria SET Titulo = '" + titulo + "', Autor = '" + autor +
		                                "', Anio_Publicacion = '" + año + "', Genero_Id = '" + genero + "', Precio = '" +
		                                precio + "', Stock = '" + stock + "' WHERE Titulo = '" + titulo + "'";

		                        regModificados = sentencia.executeUpdate(consulta);
		                        
		                        

		                        if (regModificados >= 1) {
		                            JOptionPane.showMessageDialog(null, "La información se ha actualizado", "Éxito",
		                                    JOptionPane.INFORMATION_MESSAGE);

		                            // Actualizar los valores en la tabla directamente
		                            int rowCount = ModeloT.getRowCount();
		                            for (int i = 0; i < rowCount; i++) {
		                                String currentTitulo = ModeloT.getValueAt(i, 0).toString();
		                                
		                                if (currentTitulo.equals(titulo)) {
		                                    ModeloT.setValueAt(titulo, i, 0);
		                                    ModeloT.setValueAt(autor, i, 1);
		                                    ModeloT.setValueAt(año, i, 2);
		                                    ModeloT.setValueAt(genero, i, 3);
		                                    ModeloT.setValueAt(precio, i, 4);
		                                    ModeloT.setValueAt(stock, i, 5);
		                                    break;
		                                }
		                                txt_titulo.setText("");
		                                txt_autor.setText("");
		                                txt_anio.setText("");
		                                txt_precio.setText("");
		                                txt_stock.setText("");
		                            }
		                        } else {
		                            JOptionPane.showMessageDialog(null, "La información no se ha actualizado", "Error",
		                                    JOptionPane.ERROR_MESSAGE);
		                        }

		                        Conexion.close();
		                    } catch (Exception e1) {
		                        JOptionPane.showMessageDialog(null, "Ocurrió un error: " + e1.toString(), "Error",
		                                JOptionPane.ERROR_MESSAGE);
		                    }
		                }
		                
		            }
		        });
		        btn_mod.setFont(new Font("Arial Black", Font.BOLD, 14));
		
        // Llamar al método para mostrar los registros automáticamente
        MostrarRegistros();

        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            public void valueChanged(ListSelectionEvent e) {
                // Obtener la fila seleccionada
                int selectedRow = table.getSelectedRow();
                if (selectedRow >= 0) {
                    // Obtener los valores de la fila seleccionada
                    String titulo = ModeloT.getValueAt(selectedRow, 0).toString();
                    String autor = ModeloT.getValueAt(selectedRow, 1).toString();
                    String año = ModeloT.getValueAt(selectedRow, 2).toString();
                    String genero = ModeloT.getValueAt(selectedRow, 3).toString();
                    String precio = ModeloT.getValueAt(selectedRow, 4).toString();
                    String stock = ModeloT.getValueAt(selectedRow, 5).toString();

                    // Establecer los valores en los campos de texto
                    txt_titulo.setText(titulo);
                    txt_autor.setText(autor);
                    txt_anio.setText(año);
                    CmbGenero.setSelectedItem(genero);
                    txt_precio.setText(precio);
                    txt_stock.setText(stock);
                    
                    btn_mod.setEnabled(true);
                    btn_delate.setEnabled(true);
                    txt_titulo.setEnabled(true);
                    txt_autor.setEnabled(true);
                    txt_anio.setEnabled(true);
                    txt_precio.setEnabled(true);
                    txt_stock.setEnabled(true);
                    CmbGenero.setEnabled(true);
                }
            }
        });
    }
    private void MostrarRegistros() {
        try {
            Connection conexion = DriverManager.getConnection("jdbc:ucanaccess://base/Libreria.accdb");
            Statement sentencia = conexion.createStatement();
            String consulta = "SELECT * FROM Libreria";
            ResultSet rs = sentencia.executeQuery(consulta);

            // Eliminar todas las filas de la tabla
            while (ModeloT.getRowCount() > 0) {
                ModeloT.removeRow(0);
            }

            // Agregar los registros a la tabla
            while (rs.next()) {
                Object[] fila = new Object[6];
                fila[0] = rs.getString("Titulo");
                fila[1] = rs.getString("Autor");
                fila[2] = rs.getString("Anio_Publicacion");
                fila[3] = rs.getString("Genero_Id");
                fila[4] = rs.getString("Precio");
                fila[5] = rs.getString("Stock");
                ModeloT.addRow(fila);
            }
            
            

            conexion.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al mostrar los registros: " + e.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
    private void CargarGeneros() {
	    try {
	        Conexion = DriverManager.getConnection("jdbc:ucanaccess://base/Libreria.accdb");
	        Statement sentencia = Conexion.createStatement();
	        String consulta = "SELECT Genero FROM Generos";
	        ResultSet rs = sentencia.executeQuery(consulta);

	        // Agregar un espacio en blanco al principio del ComboBox
	        CmbGenero.addItem("");

	        while (rs.next()) {
	            String genero = rs.getString("Genero");
	            CmbGenero.addItem(genero);
	        }

	        Conexion.close();
	    } catch (SQLException e) {
	        JOptionPane.showMessageDialog(null, "Ocurrió un error al cargar los géneros: " + e.toString(),
	                "Error", JOptionPane.ERROR_MESSAGE);
	    }
    }
}