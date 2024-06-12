import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Dialog.ModalityType;
import java.awt.Dialog.ModalExclusionType;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.time.LocalDate;

import javax.swing.SwingConstants;
import javax.swing.JSeparator;
import java.awt.Cursor;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BaraBusqueda extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private final JPanel panel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			BaraBusqueda dialog = new BaraBusqueda();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
			dialog.setLocationRelativeTo(null);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public BaraBusqueda() {
		setUndecorated(true);
		setResizable(false);
		setModal(true);
		setModalityType(ModalityType.APPLICATION_MODAL);
		setBounds(100, 100, 1011, 532);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(0, 0, 1008, 530);
		contentPanel.add(panel);
		panel.setLayout(null);
		this.setLocationRelativeTo(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(0, 0, 160));
		panel_1.setBounds(0, 0, 324, 530);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblLogo = new JLabel("");
		lblLogo.setIcon(new ImageIcon(BaraBusqueda.class.getResource("/src/LogoMini (1).png")));
		lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogo.setForeground(Color.WHITE);
		lblLogo.setFont(new Font("Arial Black", Font.BOLD, 21));
		lblLogo.setBackground(Color.WHITE);
		lblLogo.setBounds(91, 11, 123, 50);
		panel_1.add(lblLogo);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 72, 304, 2);
		panel_1.add(separator);
		
		JButton BtnBusqueda = new JButton("Busqueda");
		BtnBusqueda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				BusquedaDialogUsuario Busqueda = new BusquedaDialogUsuario();
				Busqueda.setVisible(true);
			}
		});
		BtnBusqueda.setIcon(new ImageIcon(BaraBusqueda.class.getResource("/src/Lupa.png")));
		BtnBusqueda.setForeground(new Color(255, 255, 255));
		BtnBusqueda.setBorderPainted(false);
		BtnBusqueda.setBorder(null);
		BtnBusqueda.setBackground(new Color(0, 128, 192));
		BtnBusqueda.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		BtnBusqueda.setFont(new Font("Arial Black", Font.BOLD, 18));
		BtnBusqueda.setBounds(0, 207, 324, 125);
		panel_1.add(BtnBusqueda);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(138, 186, 244));
		panel_2.setBounds(324, 54, 684, 135);
		panel.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblBienvenidoAlSistema = new JLabel("Bienvenido al Sistema \"Libreria Tec\"");
		lblBienvenidoAlSistema.setForeground(Color.WHITE);
		lblBienvenidoAlSistema.setFont(new Font("Arial Black", Font.BOLD, 21));
		lblBienvenidoAlSistema.setBackground(Color.WHITE);
		lblBienvenidoAlSistema.setBounds(26, 11, 622, 50);
		panel_2.add(lblBienvenidoAlSistema);
		
		JLabel lblBienvenido = new JLabel("Usuario/ Busqueda de libros");
		lblBienvenido.setForeground(Color.BLACK);
		lblBienvenido.setFont(new Font("Arial Black", Font.BOLD, 21));
		lblBienvenido.setBackground(Color.WHITE);
		lblBienvenido.setBounds(324, 0, 348, 50);
		panel.add(lblBienvenido);
		
		JLabel lblLogo_1 = new JLabel("");
		lblLogo_1.setIcon(new ImageIcon(BaraBusqueda.class.getResource("/src/Logo1.jpg")));
		lblLogo_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogo_1.setForeground(Color.WHITE);
		lblLogo_1.setFont(new Font("Arial Black", Font.BOLD, 21));
		lblLogo_1.setBackground(Color.WHITE);
		lblLogo_1.setBounds(485, 252, 345, 224);
		panel.add(lblLogo_1);
		
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
		btnX.setBounds(948, 2, 60, 50);
		panel.add(btnX);
		
		
	}
}
