import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.MaskFormatter;

import java.awt.Dialog.ModalityType;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Captura extends JDialog {

	private final JPanel contentPanel = new JPanel();
	
	DefaultComboBoxModel<String> ModeloC = new DefaultComboBoxModel<>();
	private Connection Conexion = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Captura dialog = new Captura();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Captura() {
		setUndecorated(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setResizable(false);
		setModalityType(ModalityType.APPLICATION_MODAL);
		setBounds(100, 100, 584, 569);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 0, 160));
		panel.setBounds(0, 0, 584, 68);
		contentPanel.add(panel);
		panel.setLayout(null);
		
		JLabel lblCapturaDeLibros = new JLabel("Captura de Libros");
		lblCapturaDeLibros.setForeground(Color.WHITE);
		lblCapturaDeLibros.setFont(new Font("Arial Black", Font.BOLD, 21));
		lblCapturaDeLibros.setBackground(Color.WHITE);
		lblCapturaDeLibros.setBounds(164, 11, 226, 50);
		panel.add(lblCapturaDeLibros);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Captura.class.getResource("/src/LogoMini (1).png")));
		lblNewLabel.setBounds(10, 11, 85, 37);
		panel.add(lblNewLabel);
		ModeloC.addElement("Novela");
		ModeloC.addElement("Novela Negra");
		ModeloC.addElement("Historica");
		ModeloC.addElement("Terror");
		ModeloC.addElement("Infanit");
		ModeloC.addElement("Distopía");
		ModeloC.addElement("Aventura");
		ModeloC.addElement("Teatral");
		ModeloC.addElement("Suspenso");
		ModeloC.addElement("Poesía");
		ModeloC.addElement("Jazz");
		ModeloC.addElement("Ficcion");
		ModeloC.addElement("Comica");
		ModeloC.addElement("Iniciacion");
		ModeloC.addElement("Ensayo");
		ModeloC.addElement("Romantica");
		ModeloC.addElement("Arabe");
		ModeloC.addElement("Realista");
		
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
		btnX.setBackground(new Color(255, 0, 0));
		btnX.setBounds(496, 0, 70, 37);
		panel.add(btnX);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setBounds(0, 69, 584, 500);
		contentPanel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblTitulo = new JLabel("Titulo:");
		lblTitulo.setBounds(164, 36, 94, 37);
		panel_1.add(lblTitulo);
		lblTitulo.setForeground(new Color(0, 0, 160));
		lblTitulo.setFont(new Font("Arial Black", Font.BOLD, 18));
		lblTitulo.setBackground(new Color(0, 0, 160));
		
		JLabel lblAutor = new JLabel("Autor:");
		lblAutor.setBounds(171, 96, 94, 37);
		panel_1.add(lblAutor);
		lblAutor.setForeground(new Color(0, 0, 160));
		lblAutor.setFont(new Font("Arial Black", Font.BOLD, 18));
		lblAutor.setBackground(Color.WHITE);
		
		JLabel lblFechaDePublicacin = new JLabel("Año de Publicación:");
		lblFechaDePublicacin.setBounds(10, 159, 255, 37);
		panel_1.add(lblFechaDePublicacin);
		lblFechaDePublicacin.setForeground(new Color(0, 0, 160));
		lblFechaDePublicacin.setFont(new Font("Arial Black", Font.BOLD, 18));
		lblFechaDePublicacin.setBackground(Color.WHITE);
		
		JFormattedTextField TxtTitulo = new JFormattedTextField();
		TxtTitulo.setBackground(new Color(192, 192, 192));
		TxtTitulo.setBounds(248, 47, 266, 20);
		panel_1.add(TxtTitulo);
		
		JFormattedTextField TxtAutor = new JFormattedTextField();
		TxtAutor.setBackground(new Color(192, 192, 192));
		TxtAutor.setBounds(248, 107, 266, 20);
		panel_1.add(TxtAutor);
		
		
		JFormattedTextField TxtAnio = new JFormattedTextField();
		TxtAnio.setBackground(new Color(192, 192, 192));
		TxtAnio.setBounds(248, 170, 70, 20);
		panel_1.add(TxtAnio);
		
				
				JLabel lblGenero = new JLabel("Genero:");
				lblGenero.setBounds(156, 215, 94, 37);
				panel_1.add(lblGenero);
				lblGenero.setForeground(new Color(0, 0, 160));
				lblGenero.setFont(new Font("Arial Black", Font.BOLD, 18));
				lblGenero.setBackground(Color.WHITE);
				
				JComboBox CmbGenero = new JComboBox(ModeloC);
				CmbGenero.setBounds(248, 226, 125, 20);
				panel_1.add(CmbGenero);
				CmbGenero.setBackground(new Color(255, 255, 255));
				
				JLabel lblPrecioIndividual = new JLabel("Precio Individual:");
				lblPrecioIndividual.setBounds(59, 274, 181, 37);
				panel_1.add(lblPrecioIndividual);
				lblPrecioIndividual.setForeground(new Color(0, 0, 160));
				lblPrecioIndividual.setFont(new Font("Arial Black", Font.BOLD, 18));
				lblPrecioIndividual.setBackground(Color.WHITE);
				
				JFormattedTextField TxtPrecio = new JFormattedTextField();
				TxtPrecio.setBackground(new Color(192, 192, 192));
				TxtPrecio.setBounds(248, 285, 70, 20);
				panel_1.add(TxtPrecio);
				
				JLabel lblStock = new JLabel("Stock:");
				lblStock.setBounds(171, 323, 94, 37);
				panel_1.add(lblStock);
				lblStock.setForeground(new Color(0, 0, 160));
				lblStock.setFont(new Font("Arial Black", Font.BOLD, 18));
				lblStock.setBackground(Color.WHITE);
				
				JFormattedTextField TxtStock = new JFormattedTextField();
				TxtStock.setBackground(new Color(192, 192, 192));
				TxtStock.setBounds(248, 334, 70, 20);
				panel_1.add(TxtStock);
				
				JButton BtnCapturar = new JButton("Capturar");
				BtnCapturar.setBounds(379, 438, 172, 37);
				panel_1.add(BtnCapturar);
				BtnCapturar.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						int numero = 0;
						
						
						if(TxtTitulo.getText().equals("") || TxtAutor.getText().equals("") || TxtAnio.getText().equals("") || TxtPrecio.getText().equals("") || TxtStock.getText().equals(""))
						{
							JOptionPane.showMessageDialog(null, "Porfavor Ingrese todos los datos","Error",0);
						}
						else
						{
							numero = 1;
						}
						
						
						if(numero == 1)
						{
						
						String Titulo= TxtTitulo.getText();
						String Autor = TxtAutor.getText();
						String Anio = TxtAnio.getText();
						int Anio2 = Integer.parseInt(Anio);
						String Genero = (String) CmbGenero.getSelectedItem();
						String Precio = TxtPrecio.getText();
						Double Precio2 = Double.parseDouble(Precio);
						String Stock = TxtStock.getText();
						int Stock2 = Integer.parseInt(Stock);
						int RegInsertado = 0;
						
						
						try
						{
							
							
							Conexion = DriverManager.getConnection("jdbc:ucanaccess://base//Libreria.accdb");
							Statement Sentencia = Conexion.createStatement();
							
							//String SQL = "INSERT INTO Libreria (Titulo, Autor, Anio_Publicacion, Genero, Precio, Stock) VALUES ('"+Titulo+"','"+Autor+"',"+Anio2+",'"+Genero+"',"+Precio2+","+Stock2+")";
							
							PreparedStatement Guardar = Conexion.prepareStatement("INSERT INTO Libreria (Titulo, Autor, Anio_Publicacion, Genero_id, Precio, Stock) VALUES (?,?,?,?,?,?)");
							Guardar.setString(1, Titulo); 
							Guardar.setString(2, Autor); 
							Guardar.setString(3, Anio); 
							Guardar.setString(4, Genero); 
							Guardar.setString(5, Precio); 
							Guardar.setString(6, Stock); 
							RegInsertado = Guardar.executeUpdate();
							
							if(RegInsertado>=1) {
								JOptionPane.showMessageDialog(null, "El Libro se ha almacenado");
								TxtTitulo.setText("");
								TxtAutor.setText("");
								TxtAnio.setText("");
								TxtPrecio.setText("");
								TxtStock.setText("");
							}
							else {
								JOptionPane.showMessageDialog(null, "El libro no se ha almacenado");
							}
							Sentencia.close();
							Conexion.close();
							
							
							//Insertar un registro
							/*RegInsertado = Sentencia.executeUpdate("INSERT INTO Libreria (Titulo, Autor, Anio_Publicacion, Genero_id, Precio, Stock) VALUES ('"+Titulo+"','"+Autor+"',"+Anio2+",'"+Genero+"',"+Precio2+","+Stock2+")");
							if(RegInsertado>=1) {
								JOptionPane.showMessageDialog(null, "El Libro se ha almacenado");
							}
							else {
								JOptionPane.showMessageDialog(null, "El libro no se ha almacenado");
							}

							Sentencia.close();
							Conexion.close();¨*/

							
							
							
						}catch(SQLException Error)
						{
							JOptionPane.showMessageDialog(null, Error.getMessage());
						}
						}
					}
				});
				BtnCapturar.setForeground(Color.WHITE);
				BtnCapturar.setFont(new Font("Arial Black", Font.BOLD, 18));
				BtnCapturar.setBorderPainted(false);
				BtnCapturar.setBorder(null);
				BtnCapturar.setBackground(new Color(0, 0, 160));
				
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
				BtnVolver.setBounds(10, 438, 172, 37);
				panel_1.add(BtnVolver);
				TxtStock.addKeyListener(new KeyAdapter() {
					@Override
					public void keyTyped(KeyEvent e) {
						char c = e.getKeyChar(); // Obtener el carácter ingresado por el usuario
				        if (!Character.isDigit(c)) { // Verificar si el carácter no es un dígito
				            e.consume(); // Consumir el evento para evitar que se ingrese el carácter no válido
				        }
				        
				        if (TxtStock.getText().length() >= 3) { // Verificar si la longitud del texto en el campo de año es mayor o igual a 4
				            JOptionPane.showMessageDialog(null, "Solo se aceptan 4 dígitos"); // Mostrar un cuadro de diálogo con el mensaje de error
				        	Toolkit.getDefaultToolkit().beep();
				            e.consume(); // Consumir el evento para evitar que se ingrese más de 4 dígitos
				        }
					}
				});
				TxtPrecio.addKeyListener(new KeyAdapter() {
					@Override
					public void keyTyped(KeyEvent e) {
						char c = e.getKeyChar(); // Obtener el carácter ingresado por el usuario
				        if (!Character.isDigit(c)) { // Verificar si el carácter no es un dígito
				            e.consume(); // Consumir el evento para evitar que se ingrese el carácter no válido
				        }
				        
				        if (TxtPrecio.getText().length() >= 3) { // Verificar si la longitud del texto en el campo de año es mayor o igual a 4
				            JOptionPane.showMessageDialog(null, "Solo se aceptan 4 dígitos"); // Mostrar un cuadro de diálogo con el mensaje de error
				            Toolkit.getDefaultToolkit().beep();
				            e.consume(); // Consumir el evento para evitar que se ingrese más de 4 dígitos
				        }
					}
				});
		TxtAnio.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar(); // Obtener el carácter ingresado por el usuario
		        if (!Character.isDigit(c)) { // Verificar si el carácter no es un dígito
		            e.consume(); // Consumir el evento para evitar que se ingrese el carácter no válido
		        }
		        
		        if (TxtAnio.getText().length() >= 4) { // Verificar si la longitud del texto en el campo de año es mayor o igual a 4
		            JOptionPane.showMessageDialog(null, "Solo se aceptan 4 dígitos"); // Mostrar un cuadro de diálogo con el mensaje de error
		            Toolkit.getDefaultToolkit().beep();
		            e.consume(); // Consumir el evento para evitar que se ingrese más de 4 dígitos
		        }
			}
		});
		TxtAutor.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				int texto = e.getKeyChar();
				
				boolean Mayusculas = texto >= 65 && texto <=90;
				boolean Minusculas = texto >= 97 && texto <=122;
				boolean Espacio = texto == 32;
				boolean Enes = texto == 39;
				
				if(!(Minusculas || Mayusculas || Espacio) || Enes)
				{
					e.consume();
				}
				
				if(TxtAutor.getText().length() >= 50)
				{
					Toolkit.getDefaultToolkit().beep();
					e.consume();
				}
			}
		});
		TxtTitulo.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				int texto = e.getKeyChar();
				
				boolean Mayusculas = texto >= 65 && texto <=90;
				boolean Minusculas = texto >= 97 && texto <=122;
				boolean Espacio = texto == 32;
				boolean Enes = texto == 39;
				
				if(!(Minusculas || Mayusculas || Espacio || Enes))
				{
					e.consume();
				}
				
				
				if(TxtTitulo.getText().length() >= 50)
				{
					Toolkit.getDefaultToolkit().beep();
					e.consume();
				}
			}
		});
		this.setLocationRelativeTo(null);
	}
}
