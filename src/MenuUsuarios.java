import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Dialog.ModalityType;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFormattedTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MenuUsuarios extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable TblUsuarios;
	private Connection Conexion = null;
	DefaultTableModel ModeloT = new DefaultTableModel();
	DefaultTableModel ModeloG = new DefaultTableModel();
	DefaultComboBoxModel<String> ModeloC = new DefaultComboBoxModel<>();
	JLabel lblRegistrso = new JLabel("Registros");
	
	
	JFormattedTextField TxtContra = new JFormattedTextField();
	JFormattedTextField TxtUsuario = new JFormattedTextField();
	JFormattedTextField TxtContra_1 = new JFormattedTextField();
	JFormattedTextField TxtUsuario_1 = new JFormattedTextField();
	JLabel lblUsuario_1 = new JLabel("Usuario:");
	JLabel lblContrasea_1 = new JLabel("Contraseña:");
	JButton btnAgregarCliente = new JButton("Agregar Cliente");
	JButton BtnModificarUsuario_1 = new JButton("Modificar Cliente");
	JButton BtnEliminarUsuario_1 = new JButton("Eliminar Cliente");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			MenuUsuarios dialog = new MenuUsuarios();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public MenuUsuarios() {
		setUndecorated(true);
		setModalityType(ModalityType.APPLICATION_MODAL);
		setResizable(false);
		setBounds(100, 100, 910, 494);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		this.setLocationRelativeTo(null);
		
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 0, 160));
		panel.setBounds(0, 0, 908, 85);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setForeground(new Color(255, 255, 255));
		separator.setBounds(168, 11, 16, 63);
		panel.add(separator);
		
		JLabel lblSistemaModificacinDe = new JLabel("Sistema Modificación de Usuarios");
		lblSistemaModificacinDe.setForeground(Color.WHITE);
		lblSistemaModificacinDe.setFont(new Font("Arial Black", Font.BOLD, 21));
		lblSistemaModificacinDe.setBackground(Color.WHITE);
		lblSistemaModificacinDe.setBounds(276, 11, 428, 50);
		panel.add(lblSistemaModificacinDe);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setIcon(new ImageIcon(MenuUsuarios.class.getResource("/src/LogoMini (1).png")));
		lblNewLabel.setBounds(10, 11, 183, 50);
		panel.add(lblNewLabel);
		
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
		btnX.setBounds(838, 11, 60, 50);
		panel.add(btnX);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setBounds(0, 85, 908, 407);
		contentPanel.add(panel_1);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(366, 27, 513, 303);
		panel_1.add(scrollPane);
		
		TblUsuarios = new JTable(ModeloT);
		ModeloT.addColumn("Id");
		ModeloT.addColumn("Usuario");
		ModeloT.addColumn("Contraseña");
		scrollPane.setViewportView(TblUsuarios);
		lblRegistrso.setFont(new Font("Arial", Font.BOLD, 12));
		
		lblRegistrso.setBounds(554, 12, 113, 13);
		panel_1.add(lblRegistrso);
		
		JButton btnAgregarUsuario = new JButton("Agregar Admin");
		btnAgregarUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int numero = 0;
				
				
				if(TxtUsuario.getText().equals("") || TxtContra.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null, "Porfavor Ingrese todos los datos del Administrador","Error",0);
				}
				else
				{
					numero = 1;
				}
				
				
				if(numero == 1)
				{
				
				String Usuario= TxtUsuario.getText();
				String Contra = TxtContra.getText();
				int RegInsertado = 0;
				
				
				try
				{
					
					
					Conexion = DriverManager.getConnection("jdbc:ucanaccess://base//Libreria.accdb");
					Statement Sentencia = Conexion.createStatement();
					
					//String SQL = "INSERT INTO Libreria (Titulo, Autor, Anio_Publicacion, Genero, Precio, Stock) VALUES ('"+Titulo+"','"+Autor+"',"+Anio2+",'"+Genero+"',"+Precio2+","+Stock2+")";
					
					//Insertar un registro
					RegInsertado = Sentencia.executeUpdate("INSERT INTO Admin (Usuario, Password) VALUES ('"+Usuario+"','"+Contra+"')");
					if(RegInsertado>=1) {
						JOptionPane.showMessageDialog(null, "El Administrador se ha Ingresado");
						Clear_Table1();
						MostrarRegistros();
					}
					else {
						JOptionPane.showMessageDialog(null, "El Administrador no se ha Ingresado");
					}
					
					TxtUsuario.setText("");
					TxtContra.setText("");

					Sentencia.close();
					Conexion.close();
					

					
					
					
				}catch(SQLException Error)
				{
					JOptionPane.showMessageDialog(null, Error.getMessage());
				}
				}
			}
		});
		btnAgregarUsuario.setForeground(Color.WHITE);
		btnAgregarUsuario.setFont(new Font("Arial Black", Font.BOLD, 18));
		btnAgregarUsuario.setBorderPainted(false);
		btnAgregarUsuario.setBorder(null);
		btnAgregarUsuario.setBackground(new Color(0, 0, 160));
		btnAgregarUsuario.setBounds(10, 183, 183, 37);
		panel_1.add(btnAgregarUsuario);
		
		JButton BtnEliminarUsuario = new JButton("Eliminar Usuario");		
		BtnEliminarUsuario.setEnabled(false);
		BtnEliminarUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				// Obtener el índice de la fila seleccionada
				int selectedRow = TblUsuarios.getSelectedRow();

				// Verificar si se seleccionó una fila
				if (selectedRow != -1) {
				    // Se ha seleccionado una fila
					int respuesta = JOptionPane.showInternalConfirmDialog(null, "Desea Borrar El Registro", "Advertencia",
	            			JOptionPane.WARNING_MESSAGE,JOptionPane.YES_NO_OPTION);
	            	if(respuesta == JOptionPane.YES_OPTION)
	            	{
	            		JOptionPane.showMessageDialog(null, "El registro se ha eliminado", "Éxito",
	                            JOptionPane.INFORMATION_MESSAGE);
	            		EliminarCliente();
	            	}
	            	else
	            	{
	            		JOptionPane.showMessageDialog(null, "El registro no se ha eliminado", "Error",
	                            JOptionPane.ERROR_MESSAGE);
	            	}
				} else {
				    // No se ha seleccionado ninguna fila
				    JOptionPane.showMessageDialog(null, "Por favor, seleccione una fila.", "Error", JOptionPane.ERROR_MESSAGE);
				}
				
				
			}
		});
		BtnEliminarUsuario.setForeground(Color.WHITE);
		BtnEliminarUsuario.setFont(new Font("Arial Black", Font.BOLD, 18));
		BtnEliminarUsuario.setBorderPainted(false);
		BtnEliminarUsuario.setBorder(null);
		BtnEliminarUsuario.setBackground(new Color(0, 0, 160));
		BtnEliminarUsuario.setBounds(696, 341, 183, 37);
		panel_1.add(BtnEliminarUsuario);
		
		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setForeground(new Color(0, 0, 160));
		lblUsuario.setFont(new Font("Arial Black", Font.BOLD, 18));
		lblUsuario.setBackground(new Color(0, 0, 160));
		lblUsuario.setBounds(10, 44, 94, 37);
		panel_1.add(lblUsuario);
		TxtUsuario.addKeyListener(new KeyAdapter() {
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
				
				
				if(TxtUsuario.getText().length() >= 50)
				{
					Toolkit.getDefaultToolkit().beep();
					e.consume();
				}
			}
		});
		
		
		TxtUsuario.setBackground(Color.LIGHT_GRAY);
		TxtUsuario.setBounds(102, 55, 242, 20);
		panel_1.add(TxtUsuario);
		
		JLabel lblContrasea = new JLabel("Contraseña:");
		lblContrasea.setForeground(new Color(0, 0, 160));
		lblContrasea.setFont(new Font("Arial Black", Font.BOLD, 18));
		lblContrasea.setBackground(new Color(0, 0, 160));
		lblContrasea.setBounds(10, 116, 134, 37);
		panel_1.add(lblContrasea);
		TxtContra.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar(); // Obtener el carácter ingresado por el usuario
		        if (!Character.isDigit(c)) { // Verificar si el carácter no es un dígito
		            e.consume(); // Consumir el evento para evitar que se ingrese el carácter no válido
		        }
		        
		        if (TxtContra.getText().length() >= 9) { // Verificar si la longitud del texto en el campo de año es mayor o igual a 4
		            JOptionPane.showMessageDialog(null, "Solo se aceptan 9 dígitos"); // Mostrar un cuadro de diálogo con el mensaje de error
		            Toolkit.getDefaultToolkit().beep();
		            e.consume(); // Consumir el evento para evitar que se ingrese más de 9 dígitos
		        }
			}
		});
		
		TxtContra.setBackground(Color.LIGHT_GRAY);
		TxtContra.setBounds(154, 127, 190, 20);
		panel_1.add(TxtContra);
		
		JButton BtnModificarUsuario = new JButton("Modificar");
		BtnModificarUsuario.setEnabled(false);
		BtnModificarUsuario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Obtener el índice de la fila seleccionada
				int selectedRow = TblUsuarios.getSelectedRow();

				// Verificar si se seleccionó una fila
				if (selectedRow != -1) {
				    // Se ha seleccionado una fila
					int respuesta = JOptionPane.showInternalConfirmDialog(null, "Desea Modificar El Registro", "Advertencia",
	            			JOptionPane.INFORMATION_MESSAGE,JOptionPane.YES_NO_OPTION);
	            	if(respuesta == JOptionPane.YES_OPTION)
	            	{
	            		
	            		if(TxtUsuario.getText().equals("") || TxtContra.getText().equals(""))
	            		{
	            			JOptionPane.showMessageDialog(null, "Porfavor No deje campos vacios", "Éxito",
		                            JOptionPane.INFORMATION_MESSAGE);
	            		}
	            		else
	            		{
		            		ModificarUsuario();
		    				Clear_Table1();
		    				MostrarRegistros();
		    				TxtUsuario.setText("");
		    				TxtContra.setText("");
	            		}

	            		
	            	}
	            	else
	            	{
	            		JOptionPane.showMessageDialog(null, "El registro no se ha modificado", "Error",
	                            JOptionPane.ERROR_MESSAGE);
	            	}
				} else {
				    // No se ha seleccionado ninguna fila
				    JOptionPane.showMessageDialog(null, "Por favor, seleccione una fila.", "Error", JOptionPane.ERROR_MESSAGE);
				}
				
				
			}
		});
		BtnModificarUsuario.setForeground(Color.WHITE);
		BtnModificarUsuario.setFont(new Font("Arial Black", Font.BOLD, 18));
		BtnModificarUsuario.setBorderPainted(false);
		BtnModificarUsuario.setBorder(null);
		BtnModificarUsuario.setBackground(new Color(0, 0, 160));
		BtnModificarUsuario.setBounds(366, 341, 198, 37);
		panel_1.add(BtnModificarUsuario);
		
		JButton BtnRefrscar = new JButton("Cargar Admin");
		BtnRefrscar.setBackground(new Color(0, 0, 160));
		BtnRefrscar.setForeground(new Color(255, 255, 255));
		BtnRefrscar.setFont(new Font("Arial Black", Font.BOLD, 14));
		BtnRefrscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Clear_Table1();
				MostrarRegistros();
				btnAgregarUsuario.setVisible(true);
				BtnEliminarUsuario.setVisible(true);
				BtnModificarUsuario.setVisible(true);
				BtnModificarUsuario_1.setVisible(false);
				BtnEliminarUsuario_1.setVisible(false);
				
				btnAgregarCliente.setVisible(false);
				lblContrasea_1.setVisible(false);
				TxtContra_1.setVisible(false);
				lblUsuario_1.setVisible(false);
				TxtUsuario_1.setVisible(false);
				
				btnAgregarUsuario.setVisible(true);
				lblUsuario.setVisible(true);
				TxtUsuario.setVisible(true);
				lblContrasea.setVisible(true);
				TxtContra.setVisible(true);
			}

		});
		BtnRefrscar.setBounds(366, 6, 148, 21);
		panel_1.add(BtnRefrscar);
		
		JButton BtnMostrarUsuarios = new JButton("Cargar Clientes");
		BtnMostrarUsuarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Boolean Cambiar = true;
				
				if(Cambiar)
				{
					Clear_Table1();
					MostrarClientes();
					btnAgregarUsuario.setVisible(false);
					BtnEliminarUsuario.setVisible(false);
					BtnModificarUsuario.setVisible(false);
					Cambiar = false;
					
					btnAgregarCliente.setVisible(true);
					lblContrasea_1.setVisible(true);
					TxtContra_1.setVisible(true);
					lblUsuario_1.setVisible(true);
					TxtUsuario_1.setVisible(true);
					BtnModificarUsuario_1.setVisible(true);
					BtnEliminarUsuario_1.setVisible(true);
					
					btnAgregarUsuario.setVisible(false);
					lblUsuario.setVisible(false);
					TxtUsuario.setVisible(false);
					lblContrasea.setVisible(false);
					TxtContra.setVisible(false);

				}
				
			}
		});
		BtnMostrarUsuarios.setForeground(Color.WHITE);
		BtnMostrarUsuarios.setFont(new Font("Arial Black", Font.BOLD, 14));
		BtnMostrarUsuarios.setBorderPainted(false);
		BtnMostrarUsuarios.setBorder(null);
		BtnMostrarUsuarios.setBackground(new Color(0, 0, 160));
		BtnMostrarUsuarios.setBounds(696, 4, 183, 21);
		panel_1.add(BtnMostrarUsuarios);
		
		JButton BtnVolver = new JButton("Volver");
		BtnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				BarraBusquedaAdmin Admin = new BarraBusquedaAdmin();
				Admin.setVisible(true);
			}
		});
		BtnVolver.setForeground(Color.WHITE);
		BtnVolver.setFont(new Font("Arial Black", Font.BOLD, 18));
		BtnVolver.setBorderPainted(false);
		BtnVolver.setBorder(null);
		BtnVolver.setBackground(new Color(0, 0, 160));
		BtnVolver.setBounds(10, 341, 172, 37);
		panel_1.add(BtnVolver);
		
		
		btnAgregarCliente.setVisible(false);
		btnAgregarCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int numero = 0;
				
				
				if(TxtUsuario_1.getText().equals("") || TxtContra_1.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null, "Porfavor Ingrese todos los datos de los Usuarios","Error",0);
				}
				else
				{
					numero = 1;
				}
				
				
				if(numero == 1)
				{
				
				String Usuario= TxtUsuario_1.getText();
				String Contra = TxtContra_1.getText();
				int RegInsertado = 0;
				
				
				try
				{
					
					
					Conexion = DriverManager.getConnection("jdbc:ucanaccess://base//Libreria.accdb");
					Statement Sentencia = Conexion.createStatement();
					
					//String SQL = "INSERT INTO Libreria (Titulo, Autor, Anio_Publicacion, Genero, Precio, Stock) VALUES ('"+Titulo+"','"+Autor+"',"+Anio2+",'"+Genero+"',"+Precio2+","+Stock2+")";
					
					//Insertar un registro
					RegInsertado = Sentencia.executeUpdate("INSERT INTO Clientes (Usuario, Password) VALUES ('"+Usuario+"','"+Contra+"')");
					if(RegInsertado>=1) {
						JOptionPane.showMessageDialog(null, "El Cliente se ha Ingresado");
						Clear_Table1();
						MostrarClientes();
					}
					else {
						JOptionPane.showMessageDialog(null, "El Cliente no se ha Ingresado");
					}
					
					TxtUsuario_1.setText("");
					TxtContra_1.setText("");

					Sentencia.close();
					Conexion.close();
					

					
					
					
				}catch(SQLException Error)
				{
					JOptionPane.showMessageDialog(null, Error.getMessage());
				}
				}
			}
		});
		btnAgregarCliente.setForeground(Color.WHITE);
		btnAgregarCliente.setFont(new Font("Arial Black", Font.BOLD, 18));
		btnAgregarCliente.setBorderPainted(false);
		btnAgregarCliente.setBorder(null);
		btnAgregarCliente.setBackground(new Color(0, 0, 160));
		btnAgregarCliente.setBounds(10, 183, 183, 37);
		panel_1.add(btnAgregarCliente);
		

		lblContrasea_1.setVisible(false);
		lblContrasea_1.setForeground(new Color(0, 0, 160));
		lblContrasea_1.setFont(new Font("Arial Black", Font.BOLD, 18));
		lblContrasea_1.setBackground(new Color(0, 0, 160));
		lblContrasea_1.setBounds(10, 116, 134, 37);
		panel_1.add(lblContrasea_1);
		TxtContra_1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar(); // Obtener el carácter ingresado por el usuario
		        if (!Character.isDigit(c)) { // Verificar si el carácter no es un dígito
		            e.consume(); // Consumir el evento para evitar que se ingrese el carácter no válido
		        }
		        
		        if (TxtContra.getText().length() >= 9) { // Verificar si la longitud del texto en el campo de año es mayor o igual a 4
		            JOptionPane.showMessageDialog(null, "Solo se aceptan 9 dígitos"); // Mostrar un cuadro de diálogo con el mensaje de error
		            Toolkit.getDefaultToolkit().beep();
		            e.consume(); // Consumir el evento para evitar que se ingrese más de 9 dígitos
		        }
			}
		});
		TxtContra_1.setVisible(false);
		
		
		TxtContra_1.setBackground(Color.LIGHT_GRAY);
		TxtContra_1.setBounds(154, 127, 190, 20);
		panel_1.add(TxtContra_1);
		
		
		lblUsuario_1.setVisible(false);
		lblUsuario_1.setForeground(new Color(0, 0, 160));
		lblUsuario_1.setFont(new Font("Arial Black", Font.BOLD, 18));
		lblUsuario_1.setBackground(new Color(0, 0, 160));
		lblUsuario_1.setBounds(10, 44, 94, 37);
		panel_1.add(lblUsuario_1);
		TxtUsuario_1.addKeyListener(new KeyAdapter() {
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
				
				
				if(TxtUsuario.getText().length() >= 50)
				{
					Toolkit.getDefaultToolkit().beep();
					e.consume();
				}
			}
		});
		TxtUsuario_1.setVisible(false);
		
		
		TxtUsuario_1.setBackground(Color.LIGHT_GRAY);
		TxtUsuario_1.setBounds(102, 55, 242, 20);
		panel_1.add(TxtUsuario_1);
		
		
		BtnModificarUsuario_1.setVisible(false);
		BtnModificarUsuario_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Obtener el índice de la fila seleccionada
				int selectedRow = TblUsuarios.getSelectedRow();

				// Verificar si se seleccionó una fila
				if (selectedRow != -1) {
				    // Se ha seleccionado una fila
					int respuesta = JOptionPane.showInternalConfirmDialog(null, "Desea Modificar El Registro", "Advertencia",
	            			JOptionPane.INFORMATION_MESSAGE,JOptionPane.YES_NO_OPTION);
	            	if(respuesta == JOptionPane.YES_OPTION)
	            	{
	            		
	            		if(TxtUsuario.getText().equals("") || TxtContra.getText().equals(""))
	            		{
	            			JOptionPane.showMessageDialog(null, "Porfavor No deje campos vacios", "Éxito",
		                            JOptionPane.INFORMATION_MESSAGE);
	            		}
	            		else
	            		{
		            		ModificarCliente();
		    				Clear_Table1();
		    				MostrarClientes();
		    				TxtUsuario_1.setText("");
		    				TxtContra_1.setText("");
	            		}

	            		
	            	}
	            	else
	            	{
	            		JOptionPane.showMessageDialog(null, "El registro no se ha modificado", "Error",
	                            JOptionPane.ERROR_MESSAGE);
	            	}
				} else {
				    // No se ha seleccionado ninguna fila
				    JOptionPane.showMessageDialog(null, "Por favor, seleccione una fila.", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		BtnModificarUsuario_1.setForeground(Color.WHITE);
		BtnModificarUsuario_1.setFont(new Font("Arial Black", Font.BOLD, 18));
		BtnModificarUsuario_1.setEnabled(false);
		BtnModificarUsuario_1.setBorderPainted(false);
		BtnModificarUsuario_1.setBorder(null);
		BtnModificarUsuario_1.setBackground(new Color(0, 0, 160));
		BtnModificarUsuario_1.setBounds(366, 341, 198, 37);
		panel_1.add(BtnModificarUsuario_1);
		
		
		BtnEliminarUsuario_1.setVisible(false);
		BtnEliminarUsuario_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Obtener el índice de la fila seleccionada
				int selectedRow = TblUsuarios.getSelectedRow();

				// Verificar si se seleccionó una fila
				if (selectedRow != -1) {
				    // Se ha seleccionado una fila
					int respuesta = JOptionPane.showInternalConfirmDialog(null, "Desea Borrar El Registro", "Advertencia",
	            			JOptionPane.WARNING_MESSAGE,JOptionPane.YES_NO_OPTION);
	            	if(respuesta == JOptionPane.YES_OPTION)
	            	{
	            		JOptionPane.showMessageDialog(null, "El registro se ha eliminado", "Éxito",
	                            JOptionPane.INFORMATION_MESSAGE);
	            		EliminarCliente2();
	            	}
	            	else
	            	{
	            		JOptionPane.showMessageDialog(null, "El registro no se ha eliminado", "Error",
	                            JOptionPane.ERROR_MESSAGE);
	            	}
				} else {
				    // No se ha seleccionado ninguna fila
				    JOptionPane.showMessageDialog(null, "Por favor, seleccione una fila.", "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		BtnEliminarUsuario_1.setForeground(Color.WHITE);
		BtnEliminarUsuario_1.setFont(new Font("Arial Black", Font.BOLD, 18));
		BtnEliminarUsuario_1.setEnabled(false);
		BtnEliminarUsuario_1.setBorderPainted(false);
		BtnEliminarUsuario_1.setBorder(null);
		BtnEliminarUsuario_1.setBackground(new Color(0, 0, 160));
		BtnEliminarUsuario_1.setBounds(696, 341, 183, 37);
		panel_1.add(BtnEliminarUsuario_1);
		
		 TblUsuarios.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
	            public void valueChanged(ListSelectionEvent e) {
	            	int fila = TblUsuarios.getSelectedRow();
	            	// Obtener la fila seleccionada
	                int selectedRow = TblUsuarios.getSelectedRow();
	                if (selectedRow >= 0) {
	                    // Obtener los valores de la fila seleccionada
	                	String Usuario = TblUsuarios.getValueAt(fila, 1).toString();
	            		String Contra = TblUsuarios.getValueAt(fila, 2).toString();
	            		
	            		TxtUsuario.setText(Usuario);
	            		TxtContra.setText(Contra);
	            		TxtUsuario_1.setText(Usuario);
	            		TxtContra_1.setText(Contra);
	                    
	                    BtnModificarUsuario.setEnabled(true);
	                    btnAgregarUsuario.setEnabled(false);
	                    BtnEliminarUsuario.setEnabled(true);
	                    
	                    BtnModificarUsuario_1.setEnabled(true);
	                    btnAgregarCliente.setEnabled(false);
	                    BtnEliminarUsuario_1.setEnabled(true);

	                }
	                else
	                {
	                	TxtUsuario.setText("");
	            		TxtContra.setText("");
	            		TxtUsuario_1.setText("");
	            		TxtContra_1.setText("");
	                	BtnModificarUsuario.setEnabled(false);
	                    btnAgregarUsuario.setEnabled(true);
	                    BtnEliminarUsuario.setEnabled(false);
	                    
	                    BtnModificarUsuario_1.setEnabled(false);
	                    btnAgregarCliente.setEnabled(true);
	                    BtnEliminarUsuario_1.setEnabled(false);
	                }
	            }
		 });
	            
		
		
		MostrarRegistros();
		
	}
	
	public void EliminarCliente(){
		int fila = TblUsuarios.getSelectedRow();
		String valor = TblUsuarios.getValueAt(fila, 0).toString();
		
		try {
			PreparedStatement Eliminar = Conexion.prepareStatement("DELETE FROM Admin WHERE Id = '"+valor+"'");
			Eliminar.executeUpdate();
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error " + e.getMessage());
		}
	}
	
	public void EliminarCliente2(){
		int fila = TblUsuarios.getSelectedRow();
		String valor = TblUsuarios.getValueAt(fila, 0).toString();
		
		try {
			PreparedStatement Eliminar = Conexion.prepareStatement("DELETE FROM Clientes WHERE Id = '"+valor+"'");
			Eliminar.executeUpdate();
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error " + e.getMessage());
		}
	}
	
	public void ModificarUsuario(){
		int fila = TblUsuarios.getSelectedRow();
		int id = Integer.parseInt(this.TblUsuarios.getValueAt(fila, 0).toString());
		Statement sentencia = null;
        int regModificados = 0;
        
        try {
            Conexion = DriverManager.getConnection("jdbc:ucanaccess://base/Libreria.accdb");
            sentencia = Conexion.createStatement();

            // Modificar el registro en la base de datos
            String Usuario = TxtUsuario.getText();
            String Contra = TxtContra.getText();


            String consulta = "UPDATE Admin SET Usuario = '"+Usuario+"', Password = '"+Contra+"' WHERE Id = '"+id+"'";

            regModificados = sentencia.executeUpdate(consulta);

            if (regModificados >= 1) {
                JOptionPane.showMessageDialog(null, "La información se ha actualizado", "Éxito",
                        JOptionPane.INFORMATION_MESSAGE);

                // Actualizar los valores en la tabla directamente
                int rowCount = ModeloT.getRowCount();
                for (int i = 0; i < rowCount; i++) {
                    String currentTitulo = ModeloT.getValueAt(i, 0).toString();
                    
                    if (currentTitulo.equals(id)) {
                        ModeloT.setValueAt(Usuario, i, 0);
                        ModeloT.setValueAt(Contra, i, 1);

                        break;
                    }
                    TxtUsuario.setText("");
                    TxtContra.setText("");

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
	
	public void ModificarCliente(){
		int fila = TblUsuarios.getSelectedRow();
		int id = Integer.parseInt(this.TblUsuarios.getValueAt(fila, 0).toString());
		Statement sentencia = null;
        int regModificados = 0;
        
        try {
            Conexion = DriverManager.getConnection("jdbc:ucanaccess://base/Libreria.accdb");
            sentencia = Conexion.createStatement();

            // Modificar el registro en la base de datos
            String Usuario = TxtUsuario_1.getText();
            String Contra = TxtContra_1.getText();


            String consulta = "UPDATE Clientes SET Usuario = '"+Usuario+"', Password = '"+Contra+"' WHERE Id = '"+id+"'";

            regModificados = sentencia.executeUpdate(consulta);

            if (regModificados >= 1) {
                JOptionPane.showMessageDialog(null, "La información se ha actualizado", "Éxito",
                        JOptionPane.INFORMATION_MESSAGE);

                // Actualizar los valores en la tabla directamente
                int rowCount = ModeloT.getRowCount();
                for (int i = 0; i < rowCount; i++) {
                    String currentTitulo = ModeloT.getValueAt(i, 0).toString();
                    
                    if (currentTitulo.equals(id)) {
                        ModeloT.setValueAt(Usuario, i, 0);
                        ModeloT.setValueAt(Contra, i, 1);

                        break;
                    }
                    TxtUsuario.setText("");
                    TxtContra.setText("");

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
	
	private void Clear_Table1(){
		for (int i = 0; i < TblUsuarios.getRowCount(); i++) {
		ModeloT.removeRow(i);
		i-=1;
		}
		}
	private void MostrarClientes() {
		//Recuperar la informacion de la BD y mostrarla en el JTable
		Statement SenteciaSQL = null;
		ResultSet Rs,RL;
		String[] Datos = new String[3];
		
		try {
		    Conexion = DriverManager.getConnection("jdbc:ucanaccess://base/Libreria.accdb");
		    SenteciaSQL = Conexion.createStatement();
		    Rs = SenteciaSQL.executeQuery("SELECT * FROM Clientes");
		   

		    while (Rs.next()) {
		        Datos[0] = Rs.getString("Id");
		        Datos[1] = Rs.getString("Usuario");
		        Datos[2] = Rs.getString("Password");


		        ModeloT.addRow(Datos);
		    }
		    RL = SenteciaSQL.executeQuery("SELECT COUNT(*) FROM Clientes");
		    if (RL.next()) {
		        int totalRegistros = RL.getInt(1);
		        lblRegistrso.setText("Total de registros: " + totalRegistros);
		    }

		} catch (SQLException e) {
		    JOptionPane.showMessageDialog(null, "Error " + e.getMessage());
		}
	}
	
	
	
	private void MostrarRegistros() {
		//Recuperar la informacion de la BD y mostrarla en el JTable
		Statement SenteciaSQL = null;
		ResultSet Rs,RL;
		String[] Datos = new String[3];
		
		try {
		    Conexion = DriverManager.getConnection("jdbc:ucanaccess://base/Libreria.accdb");
		    SenteciaSQL = Conexion.createStatement();
		    Rs = SenteciaSQL.executeQuery("SELECT * FROM Admin");
		   

		    while (Rs.next()) {
		        Datos[0] = Rs.getString("Id");
		        Datos[1] = Rs.getString("Usuario");
		        Datos[2] = Rs.getString("Password");


		        ModeloT.addRow(Datos);
		    }
		    RL = SenteciaSQL.executeQuery("SELECT COUNT(*) FROM Admin");
		    if (RL.next()) {
		        int totalRegistros = RL.getInt(1);
		        lblRegistrso.setText("Total de registros: " + totalRegistros);
		    }

		} catch (SQLException e) {
		    JOptionPane.showMessageDialog(null, "Error " + e.getMessage());
		}
		
	}
}
