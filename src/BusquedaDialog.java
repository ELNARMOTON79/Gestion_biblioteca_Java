import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.DefaultComboBoxModel;
import java.awt.Dialog.ModalityType;
import javax.swing.JSeparator;
import javax.swing.ImageIcon;

public class BusquedaDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField TxtTitulo;
	private JTextField TxtAutor;
	private JTextField TxtAnio;
	private JTable tb_datos;
	private Connection Conexion = null;
	DefaultTableModel ModeloT = new DefaultTableModel();
	DefaultTableModel ModeloG = new DefaultTableModel();
	DefaultComboBoxModel<String> ModeloC = new DefaultComboBoxModel<>();
	JComboBox CmbGenero = new JComboBox();
	JLabel lblRegistrso = new JLabel("Registros");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			BusquedaDialog dialog = new BusquedaDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public BusquedaDialog() {
		setUndecorated(true);
		CargarGeneros();
		setModalityType(ModalityType.APPLICATION_MODAL);
		setBounds(100, 100, 848, 497);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		this.setLocationRelativeTo(null);
			
			JPanel panel = new JPanel();
			panel.setBackground(new Color(0, 0, 160));
			panel.setBounds(0, 0, 848, 45);
			contentPanel.add(panel);
			panel.setLayout(null);
			
				JLabel lblBusqueda = new JLabel("BUSQUEDA");
				lblBusqueda.setBounds(198, 11, 302, 31);
				panel.add(lblBusqueda);
				lblBusqueda.setHorizontalAlignment(SwingConstants.CENTER);
				lblBusqueda.setForeground(new Color(255, 255, 255));
				lblBusqueda.setFont(new Font("Arial Black", Font.BOLD, 21));
				lblBusqueda.setBackground(Color.WHITE);
					
					JSeparator separator = new JSeparator();
					separator.setForeground(new Color(255, 255, 255));
					separator.setOrientation(SwingConstants.VERTICAL);
					separator.setBounds(173, 11, 62, 23);
					panel.add(separator);
					
					JLabel lblNewLabel = new JLabel("");
					lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
					lblNewLabel.setIcon(new ImageIcon(BusquedaDialog.class.getResource("/src/LogoMini (1).png")));
					lblNewLabel.setBounds(0, 0, 146, 45);
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
					btnX.setBounds(778, 0, 70, 42);
					panel.add(btnX);
		
			JPanel contentPane = new JPanel();
			contentPane.setBackground(new Color(255, 255, 255));
			contentPane.setLayout(null);
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			contentPane.setBounds(0, 10, 848, 487);
			contentPanel.add(contentPane);
						
				ModeloT.addColumn("Id");
				ModeloT.addColumn("Titulo");
				ModeloT.addColumn("Autor");
				ModeloT.addColumn("Año Publicacion");
				ModeloT.addColumn("Genero");
				ModeloT.addColumn("Stock");
				ModeloT.addColumn("Precio");

				JTable tb_datos = new JTable(ModeloT);

				// Agregar los datos al modelo de la tabla
				// Aquí debes agregar tus datos al modelo de la tabla

				// Ajustar automáticamente el ancho de las columnas "Titulo" y "Autor"
				tb_datos.getColumnModel().getColumn(1).setMinWidth(50); // Establecer un ancho mínimo para la columna "Titulo"
				tb_datos.getColumnModel().getColumn(1).setMaxWidth(350); // Establecer un ancho máximo para la columna "Titulo"
				tb_datos.getColumnModel().getColumn(1).setPreferredWidth(200); // Establecer un ancho preferido inicial para la columna "Titulo"
				tb_datos.getColumnModel().getColumn(2).setMinWidth(50); // Establecer un ancho mínimo para la columna "Autor"
				tb_datos.getColumnModel().getColumn(2).setMaxWidth(250); // Establecer un ancho máximo para la columna "Autor"
				tb_datos.getColumnModel().getColumn(2).setPreferredWidth(150); // Establecer un ancho preferido inicial para la columna "Autor"
				tb_datos.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); // Desactivar el ajuste automático del ancho de las columnas
				tb_datos.doLayout(); // Actualizar el diseño de la tabla
				tb_datos.getColumnModel().getColumn(1).setPreferredWidth(tb_datos.getColumnModel().getColumn(1).getPreferredWidth()); // Ajustar el ancho preferido de la columna "Titulo" al contenido
				tb_datos.getColumnModel().getColumn(2).setPreferredWidth(tb_datos.getColumnModel().getColumn(2).getPreferredWidth()); // Ajustar el ancho preferido de la columna "Autor" al contenido

				// Añadir la tabla a un JScrollPane
				JScrollPane scrollPane = new JScrollPane(tb_datos);
				scrollPane.setBounds(130, 36, 667, 402);
				contentPane.add(scrollPane);

				
			
			
				JLabel lblTitulo = new JLabel("Titulo");
				lblTitulo.setForeground(new Color(0, 0, 160));
				lblTitulo.setFont(new Font("Arial Black", Font.BOLD, 14));
				lblTitulo.setBounds(0, 69, 71, 13);
				contentPane.add(lblTitulo);
			
			
				JLabel lblAutor = new JLabel("Autor");
				lblAutor.setForeground(new Color(0, 0, 160));
				lblAutor.setFont(new Font("Arial Black", Font.BOLD, 14));
				lblAutor.setBounds(0, 123, 45, 13);
				contentPane.add(lblAutor);
			
			
				JLabel lblAnio = new JLabel("Año");
				lblAnio.setForeground(new Color(0, 0, 160));
				lblAnio.setFont(new Font("Arial Black", Font.BOLD, 14));
				lblAnio.setBounds(0, 177, 45, 13);
				contentPane.add(lblAnio);
			
			
				JLabel lblGenero = new JLabel("Genero");
				lblGenero.setForeground(new Color(0, 0, 160));
				lblGenero.setFont(new Font("Arial Black", Font.BOLD, 14));
				lblGenero.setBounds(0, 231, 71, 13);
				contentPane.add(lblGenero);
			
			
				TxtTitulo = new JTextField();
				TxtTitulo.addKeyListener(new KeyAdapter() {
					@Override
					public void keyTyped(KeyEvent e) {
						if(TxtTitulo.getText().length() >= 20)
					    {
					        e.consume();
					    }
						int key = e.getKeyChar();

					    boolean mayusculas = key >= 65 && key <= 90;
					    boolean minusculas = key >= 97 && key <= 122;
					    boolean numero = key >= 48 && key <= 57; 
					    boolean espacio = key == 32;
					            
					     if (!(minusculas || mayusculas || espacio || numero))
					    {
					        e.consume();
					    }
					}
				});
				TxtTitulo.setColumns(10);
				TxtTitulo.setBounds(0, 93, 120, 19);
				contentPane.add(TxtTitulo);
			
			
				TxtAutor = new JTextField();
				TxtAutor.addKeyListener(new KeyAdapter() {
					@Override
					public void keyTyped(KeyEvent e) {
						if(TxtAutor.getText().length() >= 20)
					    {
					        e.consume();
					    }
						int key = e.getKeyChar();

					    boolean mayusculas = key >= 65 && key <= 90;
					    boolean minusculas = key >= 97 && key <= 122;
					    boolean espacio = key == 32;
					            
					     if (!(minusculas || mayusculas || espacio))
					    {
					        e.consume();
					    }
					}
				});
				TxtAutor.setColumns(10);
				TxtAutor.setBounds(0, 147, 120, 19);
				contentPane.add(TxtAutor);
			
			
				TxtAnio = new JTextField();
				TxtAnio.addKeyListener(new KeyAdapter() {
					@Override
				    public void keyTyped(KeyEvent e) {
						
						if(TxtAnio.getText().length() >= 4)
					    {
					        e.consume();
					    }
						int key = e.getKeyChar();
						
					    boolean numero = key >= 48 && key <= 57; 
					            
					     if (!(numero))
					    {
					        e.consume();
					    }
				        
				        if (TxtAnio.getText().length() >= 4) { // Verificar si la longitud del texto en el campo de año es mayor o igual a 4
				            JOptionPane.showMessageDialog(null, "Solo se aceptan 4 dígitos"); // Mostrar un cuadro de diálogo con el mensaje de error
				            e.consume(); // Consumir el evento para evitar que se ingrese más de 4 dígitos
				        }
				    }
				});
				TxtAnio.setColumns(10);
				TxtAnio.setBounds(0, 201, 120, 19);
				contentPane.add(TxtAnio);
				
				CmbGenero.setBounds(0, 255, 120, 28);
				contentPane.add(CmbGenero);
				JButton BtnBuscar = new JButton("Buscar");
				BtnBuscar.setBounds(10, 326, 110, 44);
				contentPane.add(BtnBuscar);
				BtnBuscar.setBackground(new Color(0, 0, 160));
				BtnBuscar.setForeground(new Color(255, 255, 255));
				BtnBuscar.setFont(new Font("Arial Black", Font.BOLD, 12));
				BtnBuscar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Statement SentenciaSQL = null;
						ResultSet Rs1 = null;
						String[] Datos = new String[8];
						int totalRegistros = 0;

						try {
						    Connection Conexion = DriverManager.getConnection("jdbc:ucanaccess://base/Libreria.accdb");
						    SentenciaSQL = Conexion.createStatement();
						    DefaultTableModel ModeloT = new DefaultTableModel();

						    ModeloT.addColumn("Id");
						    ModeloT.addColumn("Titulo");
						    ModeloT.addColumn("Autor");
						    ModeloT.addColumn("Año Publicacion");
						    ModeloT.addColumn("Genero");
						    ModeloT.addColumn("Stock");
						    ModeloT.addColumn("Precio");

						    String titulo = TxtTitulo.getText();
						    String autor = TxtAutor.getText();
						    String anio = TxtAnio.getText();
						    String genero = CmbGenero.getSelectedItem().toString();

						    String query = "SELECT * FROM Libreria WHERE ";

						    if (!titulo.isEmpty()) {
						        query += "Titulo LIKE '%" + titulo + "%' ";
						    }

						    if (!autor.isEmpty()) {
						        if (!query.endsWith("WHERE ")) {
						            query += "OR ";
						        }
						        query += "Autor LIKE '%" + autor + "%' ";
						    }

						    if (!anio.isEmpty()) {
						        if (!query.endsWith("WHERE ")) {
						            query += "OR ";
						        }
						        query += "Anio_Publicacion LIKE '%" + anio + "%' ";
						    }

						    if (!genero.isEmpty()) {
						        if (!query.endsWith("WHERE ")) {
						            query += "OR ";
						        }
						        query += "Genero_id = '" + genero + "' ";
						    }

						    if (query.endsWith("WHERE ")) {
						        JOptionPane.showMessageDialog(null, "No se registró ninguna variable");
						        TxtTitulo.setText("");
						        TxtAutor.setText("");
						        TxtAnio.setText("");
						    } else {
						        Rs1 = SentenciaSQL.executeQuery(query);
						        while (Rs1.next()) {
						            Datos[0] = Rs1.getString("Id");
						            Datos[1] = Rs1.getString("Titulo");
						            Datos[2] = Rs1.getString("Autor");
						            Datos[3] = Rs1.getString("Anio_Publicacion");
						            Datos[4] = Rs1.getString("Genero_id");
						            Datos[6] = Rs1.getString("Precio");
						            Datos[5] = Rs1.getString("Stock");
						            ModeloT.addRow(Datos);
						            totalRegistros++;
						        }

						        tb_datos.setModel(ModeloT);
						        lblRegistrso.setText("Total de registros buscados: " + totalRegistros);
						    }
						} catch (SQLException e2) {
						    JOptionPane.showMessageDialog(null, "Error: " + e2.getMessage());
						}
						return;

					}
				});
				BtnBuscar.setActionCommand("");
				getRootPane().setDefaultButton(BtnBuscar);
				JButton BtnSalir = new JButton("Salir");
				BtnSalir.setBounds(701, 449, 96, 27);
				contentPane.add(BtnSalir);
				BtnSalir.setBackground(new Color(0, 0, 160));
				BtnSalir.setForeground(new Color(255, 255, 255));
				BtnSalir.setFont(new Font("Arial Black", Font.BOLD, 12));
				BtnSalir.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
						BarraBusquedaAdmin Admin = new BarraBusquedaAdmin();
						Admin.setVisible(true);
					}
				});
				BtnSalir.setActionCommand("Cancel");
				
				
					JButton BtnRefrscar = new JButton("Recargar Libros");
					BtnRefrscar.setBounds(130, 452, 162, 21);
					contentPane.add(BtnRefrscar);
					BtnRefrscar.setBackground(new Color(0, 0, 160));
					BtnRefrscar.setForeground(new Color(255, 255, 255));
					BtnRefrscar.setFont(new Font("Arial Black", Font.BOLD, 11));
					lblRegistrso.setBounds(352, 438, 519, 13);
					contentPane.add(lblRegistrso);
					lblRegistrso.setForeground(new Color(0, 0, 0));
					lblRegistrso.setFont(new Font("Arial Black", Font.BOLD, 14));
					BtnRefrscar.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							Statement SenteciaSQL = null;
							ResultSet Rs = null;
							String[] Datos = new String[8];

							try {
							    Conexion = DriverManager.getConnection("jdbc:ucanaccess://base/Libreria.accdb");
							    SenteciaSQL = Conexion.createStatement();
							    Rs = SenteciaSQL.executeQuery("SELECT * FROM Libreria");

							    while (Rs.next()) {
							    	TxtTitulo.setText("");
							    	TxtAutor.setText("");
							    	TxtAnio.setText("");
							    	
							        Datos[0] = Rs.getString("Id");
							        Datos[1] = Rs.getString("Titulo");
							        Datos[2] = Rs.getString("Autor");
							        Datos[3] = Rs.getString("Anio_Publicacion");
							        Datos[4] = Rs.getString("Genero_id");
							        Datos[6] = Rs.getString("Precio");
							        Datos[5] = Rs.getString("Stock");

							        // Crear una instancia de DefaultTableModel dentro del bucle
							        DefaultTableModel ModeloT = new DefaultTableModel();
							        ModeloT.addRow(Datos);
							        
							    }
							    ResultSet RL = SenteciaSQL.executeQuery("SELECT COUNT(*) FROM Libreria");
							    if (RL.next()) {
							        int totalRegistros = RL.getInt(1);
							        lblRegistrso.setText("Total de registros: " + totalRegistros);
							    }
							    tb_datos.setModel(ModeloT);
							} catch (SQLException e2) {
							    JOptionPane.showMessageDialog(null, "Error " + e2.getMessage());
							}
							return;
								}

					});
			
			{
				
				MostrarRegistros();
			}
		}
		
		
	

	private void MostrarRegistros() {
		//Recuperar la informacion de la BD y mostrarla en el JTable
		Statement SenteciaSQL = null;
		ResultSet Rs,RL;
		String[] Datos = new String[7];
		
		try {
		    Conexion = DriverManager.getConnection("jdbc:ucanaccess://base/Libreria.accdb");
		    SenteciaSQL = Conexion.createStatement();
		    Rs = SenteciaSQL.executeQuery("SELECT * FROM Libreria");
		   

		    while (Rs.next()) {
		        Datos[0] = Rs.getString("Id");
		        Datos[1] = Rs.getString("Titulo");
		        Datos[2] = Rs.getString("Autor");
		        Datos[3] = Rs.getString("Anio_Publicacion");
		        Datos[4] = Rs.getString("Genero_id");
		        Datos[6] = Rs.getString("Precio");
		        Datos[5] = Rs.getString("Stock");

		        ModeloT.addRow(Datos);
		    }
		    RL = SenteciaSQL.executeQuery("SELECT COUNT(*) FROM Libreria");
		    if (RL.next()) {
		        int totalRegistros = RL.getInt(1);
		        lblRegistrso.setText("Total de registros: " + totalRegistros);
		    }

		} catch (SQLException e) {
		    JOptionPane.showMessageDialog(null, "Error " + e.getMessage());
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
