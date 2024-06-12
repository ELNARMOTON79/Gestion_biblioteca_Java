import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import java.awt.Dialog.ModalityType;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BarraBusquedaAdmin extends JDialog {

	private final JPanel contentPanel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			BarraBusquedaAdmin dialog = new BarraBusquedaAdmin();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public BarraBusquedaAdmin() {
		setUndecorated(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setResizable(false);
		setModalityType(ModalityType.APPLICATION_MODAL);
		setBounds(100, 100, 1024, 531);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		this.setLocationRelativeTo(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 0, 324, 530);
		panel_1.setLayout(null);
		panel_1.setBackground(new Color(0, 0, 160));
		contentPanel.add(panel_1);
		
		JLabel lblLogo = new JLabel("");
		lblLogo.setIcon(new ImageIcon(BarraBusquedaAdmin.class.getResource("/src/LogoMini (1).png")));
		lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogo.setForeground(Color.WHITE);
		lblLogo.setFont(new Font("Arial Black", Font.BOLD, 21));
		lblLogo.setBackground(Color.WHITE);
		lblLogo.setBounds(86, 11, 148, 56);
		panel_1.add(lblLogo);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 72, 304, 2);
		panel_1.add(separator);
		
		JButton BtnBusqueda = new JButton("Captura");
		BtnBusqueda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Captura Capturar = new Captura();
				Capturar.setVisible(true);
			}
		});
		BtnBusqueda.setIcon(new ImageIcon(BarraBusquedaAdmin.class.getResource("/src/icons8-abajo-2-50.png")));
		BtnBusqueda.setForeground(Color.WHITE);
		BtnBusqueda.setFont(new Font("Arial Black", Font.BOLD, 18));
		BtnBusqueda.setBorderPainted(false);
		BtnBusqueda.setBorder(null);
		BtnBusqueda.setBackground(new Color(0, 128, 192));
		BtnBusqueda.setBounds(0, 85, 324, 56);
		panel_1.add(BtnBusqueda);
		
		JLabel lblLogo_1 = new JLabel("");
		lblLogo_1.setIcon(new ImageIcon(BarraBusquedaAdmin.class.getResource("/src/Logo1.jpg")));
		lblLogo_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogo_1.setForeground(Color.WHITE);
		lblLogo_1.setFont(new Font("Arial Black", Font.BOLD, 21));
		lblLogo_1.setBackground(Color.WHITE);
		lblLogo_1.setBounds(475, 259, 345, 224);
		contentPanel.add(lblLogo_1);
		
	
		
		JButton btnInventario = new JButton("Venta");
		btnInventario.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Menu_VentasJ Ventas = new Menu_VentasJ();
				Ventas.setVisible(true);
			}
		});
		btnInventario.setIcon(new ImageIcon(BarraBusquedaAdmin.class.getResource("/src/Dinero.png")));
		btnInventario.setForeground(Color.WHITE);
		btnInventario.setFont(new Font("Arial Black", Font.BOLD, 18));
		btnInventario.setBorderPainted(false);
		btnInventario.setBorder(null);
		btnInventario.setBackground(new Color(0, 128, 192));
		btnInventario.setBounds(0, 269, 324, 56);
		panel_1.add(btnInventario);
		
		JButton btnUsuarios = new JButton("Usuarios");
		btnUsuarios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				MenuUsuarios Usuarios = new MenuUsuarios();
				Usuarios.setVisible(true);
			}
		});
		btnUsuarios.setIcon(new ImageIcon(BarraBusquedaAdmin.class.getResource("/src/icons8-usuario-50.png")));
		btnUsuarios.setForeground(Color.WHITE);
		btnUsuarios.setFont(new Font("Arial Black", Font.BOLD, 18));
		btnUsuarios.setBorderPainted(false);
		btnUsuarios.setBorder(null);
		btnUsuarios.setBackground(new Color(0, 128, 192));
		btnUsuarios.setBounds(0, 369, 324, 56);
		panel_1.add(btnUsuarios);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(324, 70, 700, 139);
		panel_2.setLayout(null);
		panel_2.setBackground(new Color(138, 186, 244));
		contentPanel.add(panel_2);
		
		JLabel lblBienvenidoAlSistema = new JLabel("Bienvenido al Sistema \"Libreria Tec\"");
		lblBienvenidoAlSistema.setForeground(Color.WHITE);
		lblBienvenidoAlSistema.setFont(new Font("Arial Black", Font.BOLD, 21));
		lblBienvenidoAlSistema.setBackground(Color.WHITE);
		lblBienvenidoAlSistema.setBounds(26, 11, 622, 50);
		panel_2.add(lblBienvenidoAlSistema);
		
		JLabel lblUsuarioRegistrado = new JLabel("Usuario en Linea");
		lblUsuarioRegistrado.setForeground(new Color(0, 128, 0));
		lblUsuarioRegistrado.setFont(new Font("Arial Black", Font.ITALIC, 21));
		lblUsuarioRegistrado.setBackground(Color.WHITE);
		lblUsuarioRegistrado.setBounds(26, 72, 382, 50);
		panel_2.add(lblUsuarioRegistrado);
		
		JLabel lblAdministradorControl = new JLabel("Administrador / Control Global");
		lblAdministradorControl.setForeground(Color.BLACK);
		lblAdministradorControl.setFont(new Font("Arial Black", Font.BOLD, 21));
		lblAdministradorControl.setBackground(Color.WHITE);
		lblAdministradorControl.setBounds(334, 11, 455, 50);
		contentPanel.add(lblAdministradorControl);
		
		
		JPanel PanelBusqueda = new JPanel();
		PanelBusqueda.setBackground(new Color(255, 255, 255));
		PanelBusqueda.setBounds(324, 208, 700, 322);
		contentPanel.add(PanelBusqueda);
		PanelBusqueda.setLayout(null);
	
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
		btnX.setBounds(948, 0, 60, 50);
		contentPanel.add(btnX);
		
		JButton btnVenta = new JButton("Busqueda");
		btnVenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				BusquedaDialog Busqueda = new BusquedaDialog();
				Busqueda.setVisible(true);
				
			}
		});
		btnVenta.setIcon(new ImageIcon(BarraBusquedaAdmin.class.getResource("/src/Lupa.png")));
		btnVenta.setForeground(Color.WHITE);
		btnVenta.setFont(new Font("Arial Black", Font.BOLD, 18));
		btnVenta.setBorderPainted(false);
		btnVenta.setBorder(null);
		btnVenta.setBackground(new Color(0, 128, 192));
		btnVenta.setBounds(0, 174, 324, 56);
		panel_1.add(btnVenta);
		
		JButton btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				mod Modificar = new mod();
				Modificar.setVisible(true);
			}
		});
		btnModificar.setIcon(new ImageIcon(BarraBusquedaAdmin.class.getResource("/src/Modificar.png")));
		btnModificar.setForeground(Color.WHITE);
		btnModificar.setFont(new Font("Arial Black", Font.BOLD, 18));
		btnModificar.setBorderPainted(false);
		btnModificar.setBorder(null);
		btnModificar.setBackground(new Color(0, 128, 192));
		btnModificar.setBounds(0, 455, 324, 56);
		panel_1.add(btnModificar);
	}
}
