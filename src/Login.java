import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import java.awt.ComponentOrientation;
import java.awt.Dialog.ModalExclusionType;

public class Login {

	private JFrame frame;
	private JTextField TxtUsuario;
	private JPasswordField TxtContra;
	
	
	
	//Variables Globales
	private Connection Conexion = null;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setResizable(false);
		frame.setBounds(100, 100, 668, 389);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 0, 160));
		panel.setBounds(378, 0, 274, 350);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setIcon(new ImageIcon(Login.class.getResource("/src/Logo1.jpg")));
		lblNewLabel_1.setBounds(54, 101, 177, 137);
		panel.add(lblNewLabel_1);
		
		JLabel lblLibreriaTec = new JLabel("LIBRERIA TEC");
		lblLibreriaTec.setForeground(new Color(255, 255, 255));
		lblLibreriaTec.setFont(new Font("Arial Black", Font.BOLD, 21));
		lblLibreriaTec.setBackground(Color.WHITE);
		lblLibreriaTec.setBounds(54, 250, 184, 50);
		panel.add(lblLibreriaTec);
		
		JLabel BtnAdmin = new JLabel("Administrador");
		BtnAdmin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				JPasswordField passwordField = new JPasswordField();
		        int opcion = JOptionPane.showConfirmDialog(null, passwordField, "Ingrese su codigo de Administrador", JOptionPane.OK_CANCEL_OPTION);
		        
		        if (opcion == JOptionPane.OK_OPTION) {
		            char[] contraseñaIngresada = passwordField.getPassword();
		            String contraseña = new String(contraseñaIngresada);
		            
		            if (contraseña.equals("Tecoman")) {
		                JOptionPane.showMessageDialog(BtnAdmin, "Contraseña Correcta. Acceso Valido", "Bienvenido", 1);
		                LoginAdmin Admin = new LoginAdmin();
						Admin.setVisible(true);
		            } else {
		            	JOptionPane.showMessageDialog(BtnAdmin, "Contraseña Incorrecta. Acceso Invalido", "Error", 0);
		            }
		        } else {
		        	JOptionPane.showMessageDialog(BtnAdmin, "Cuadro Cerrado. Acceso Invalido", "Error", 0);
		        }

			}
		});
		BtnAdmin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		BtnAdmin.setBounds(160, 306, 104, 33);
		panel.add(BtnAdmin);
		BtnAdmin.setBackground(new Color(255, 255, 255));
		BtnAdmin.setHorizontalAlignment(SwingConstants.CENTER);
		BtnAdmin.setForeground(new Color(255, 255, 255));
		BtnAdmin.setFont(new Font("Arial Black", Font.PLAIN, 11));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setBounds(0, 0, 378, 350);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("INICIAR SESIÓN");
		lblNewLabel.setFont(new Font("Arial Black", Font.BOLD, 21));
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setBackground(new Color(255, 255, 255));
		lblNewLabel.setBounds(10, 51, 211, 50);
		panel_1.add(lblNewLabel);
		
		JLabel lblUsuario = new JLabel("USUARIO");
		lblUsuario.setForeground(Color.BLACK);
		lblUsuario.setFont(new Font("Arial Black", Font.BOLD, 12));
		lblUsuario.setBackground(Color.WHITE);
		lblUsuario.setBounds(10, 126, 211, 24);
		panel_1.add(lblUsuario);
		
		TxtUsuario = new JTextField();
		TxtUsuario.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				
			}
		});
		TxtUsuario.setBorder(null);
		TxtUsuario.setForeground(new Color(128, 128, 128));
		TxtUsuario.setBounds(10, 161, 211, 20);
		panel_1.add(TxtUsuario);
		TxtUsuario.setColumns(10);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(new Color(0, 0, 0));
		separator.setBounds(10, 192, 211, 8);
		panel_1.add(separator);
		
		JLabel lblContrasea = new JLabel("CONTRASEÑA");
		lblContrasea.setForeground(Color.BLACK);
		lblContrasea.setFont(new Font("Arial Black", Font.BOLD, 12));
		lblContrasea.setBackground(Color.WHITE);
		lblContrasea.setBounds(10, 211, 211, 24);
		panel_1.add(lblContrasea);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(Color.BLACK);
		separator_1.setBounds(10, 277, 211, 8);
		panel_1.add(separator_1);
		
		TxtContra = new JPasswordField();
		TxtContra.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				
			}
		});
		TxtContra.setForeground(new Color(128, 128, 128));
		TxtContra.setBorder(null);
		TxtContra.setBounds(10, 246, 211, 20);
		panel_1.add(TxtContra);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(0, 0, 160));
		panel_2.setBounds(10, 306, 104, 33);
		panel_1.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel BtnIngresar = new JLabel("Ingresar");
		BtnIngresar.setBounds(0, 0, 104, 33);
		panel_2.add(BtnIngresar);
		BtnIngresar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				BtnIngresar.setBackground(new Color(85, 240, 253));
			}
			@Override
			public void mousePressed(MouseEvent e) {
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				
				Statement Sentencia = null;
				ResultSet Rs;
				
				try
				{
					Conexion = DriverManager.getConnection("jdbc:ucanaccess://base//Libreria.accdb");
					String Usser = TxtUsuario.getText();
					String Contra = TxtContra.getText();
					
					String SQL = "SELECT Usuario, Password FROM Clientes WHERE Usuario = '"+Usser+"' AND Password = '"+Contra+"'";
					
					Sentencia = Conexion.createStatement();
					//Enviar la sentencia SQL a la base de datos
					Rs = Sentencia.executeQuery(SQL); 
					if(Rs.next())
					{
						JOptionPane.showMessageDialog(BtnIngresar, "Bienvenido al sistema  " +Usser,"Bienvenido", 1);
						BaraBusqueda Busqueda = new BaraBusqueda();
						Busqueda.setVisible(true);
						
					}else
					{
						JOptionPane.showMessageDialog(BtnIngresar, "Usuario o contraseña Incorrectos, verifique sus datos", "Error", 0);
					}
					
				}catch(SQLException Error)
				{
					JOptionPane.showMessageDialog(BtnIngresar, Error.getMessage());
				}

				
			}
		});
		BtnIngresar.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		BtnIngresar.setForeground(new Color(255, 255, 255));
		BtnIngresar.setFont(new Font("Arial Black", Font.PLAIN, 11));
		BtnIngresar.setHorizontalAlignment(SwingConstants.CENTER);
	}
	
	

	protected void setVisible(boolean b) {
		// TODO Apéndice de método generado automáticamente
		
	}
}
