

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Window;

import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.Dialog.ModalExclusionType;
import java.awt.Dialog.ModalityType;
import java.awt.Cursor;

public class LoginAdmin extends JDialog {
	private JTextField TxtUsuario;
	private JPasswordField TxtContra;
	
	

	//Variables Globales
	private Connection Conexion = null;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			LoginAdmin dialog = new LoginAdmin();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public LoginAdmin() {
		setUndecorated(true);
		setModalityType(ModalityType.APPLICATION_MODAL);
		setResizable(false);
		setBounds(100, 100, 655, 351);
		getContentPane().setLayout(null);
		this.setLocationRelativeTo(null);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(0, 0, 160));
		panel.setBounds(380, 0, 274, 350);
		getContentPane().add(panel);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(LoginAdmin.class.getResource("/src/Logo1.jpg")));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(54, 101, 177, 137);
		panel.add(lblNewLabel_1);
		
		JLabel lblLibreriaTecAdministrador = new JLabel("LIBRERIA TEC \r\nADMIN");
		lblLibreriaTecAdministrador.setForeground(Color.WHITE);
		lblLibreriaTecAdministrador.setFont(new Font("Arial Black", Font.BOLD, 21));
		lblLibreriaTecAdministrador.setBackground(Color.WHITE);
		lblLibreriaTecAdministrador.setBounds(10, 250, 264, 50);
		panel.add(lblLibreriaTecAdministrador);
		
		JLabel BtnVolver = new JLabel("X");
		BtnVolver.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		BtnVolver.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		BtnVolver.setBounds(196, 11, 68, 33);
		panel.add(BtnVolver);
		BtnVolver.setBackground(new Color(255, 0, 0));
		BtnVolver.setHorizontalAlignment(SwingConstants.CENTER);
		BtnVolver.setForeground(new Color(255, 255, 255));
		BtnVolver.setFont(new Font("Arial Black", Font.PLAIN, 23));
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(0, 0, 378, 350);
		getContentPane().add(panel_1);
		
		JLabel lblNewLabel = new JLabel("INICIAR SESIÓN");
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setFont(new Font("Arial Black", Font.BOLD, 21));
		lblNewLabel.setBackground(Color.WHITE);
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
		TxtUsuario.setForeground(new Color(128, 128, 128));
		TxtUsuario.setColumns(10);
		TxtUsuario.setBorder(null);
		TxtUsuario.setBounds(10, 161, 211, 20);
		panel_1.add(TxtUsuario);
		
		JSeparator separator = new JSeparator();
		separator.setForeground(Color.BLACK);
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
		panel_2.setLayout(null);
		panel_2.setBackground(new Color(0, 0, 160));
		panel_2.setBounds(10, 306, 104, 33);
		panel_1.add(panel_2);
		
		JLabel BtnIngresar = new JLabel("Ingresar");
		BtnIngresar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Statement Sentencia = null;
				ResultSet Rs;
				
				try
				{
					Conexion = DriverManager.getConnection("jdbc:ucanaccess://base//Libreria.accdb");
					String Usser = TxtUsuario.getText();
					String Contra = TxtContra.getText();
					
					String SQL = "SELECT Usuario, Password FROM Admin WHERE Usuario = '"+Usser+"' AND Password = '"+Contra+"'";
					
					Sentencia = Conexion.createStatement();
					//Enviar la sentencia SQL a la base de datos
					Rs = Sentencia.executeQuery(SQL); 
					if(Rs.next())
					{
						JOptionPane.showMessageDialog(BtnIngresar, "Bienvenido al sistema de Administrador  " +Usser,"Bienvenido", 1);
						BarraBusquedaAdmin Admin = new BarraBusquedaAdmin();
						setVisible(false);
						Admin.setVisible(true);
						
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
		BtnIngresar.setHorizontalAlignment(SwingConstants.CENTER);
		BtnIngresar.setForeground(Color.WHITE);
		BtnIngresar.setFont(new Font("Arial Black", Font.PLAIN, 11));
		BtnIngresar.setBounds(0, 0, 104, 33);
		panel_2.add(BtnIngresar);
	}
}
