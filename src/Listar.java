import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Listar extends JFrame {

	private JPanel contentPane;
	private JTable tb_datos;
	
	private Connection Conexion = null;
	DefaultTableModel ModeloT = new DefaultTableModel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Listar frame = new Listar();
					frame.setVisible(true);
					
					//Driver de la BD
					Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Listar() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 644, 474);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblBusqueda = new JLabel("BUSQUEDA");
		lblBusqueda.setBounds(266, 10, 136, 31);
		lblBusqueda.setForeground(Color.BLACK);
		lblBusqueda.setFont(new Font("Arial Black", Font.BOLD, 21));
		lblBusqueda.setBackground(Color.WHITE);
		lblBusqueda.setHorizontalAlignment(SwingConstants.CENTER); // Centra el JLabel horizontalmente
		contentPane.add(lblBusqueda);

		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(226, 61, 394, 366);
		contentPane.add(scrollPane);
		
		ModeloT.addColumn("Id Libro");
		ModeloT.addColumn("Titulo");
		ModeloT.addColumn("Autor");
		ModeloT.addColumn("Año Publicacion");	
		ModeloT.addColumn("Genero");
		ModeloT.addColumn("Existencia");
		
		tb_datos = new JTable();
		tb_datos.setModel(ModeloT);
		scrollPane.setViewportView(tb_datos);
		
		// Llamar al método para mostrar los registros automáticamente
		MostrarRegistros();
	}
	
	private void MostrarRegistros() {
		//Recuperar la informacion de la BD y mostrarla en el JTable
		Statement SenteciaSQL = null;
		ResultSet Rs;
		
		String[] Datos= new String[6];
		try {
			Conexion= DriverManager.getConnection("jdbc:ucanaccess://base/Libreria.accdb");
			SenteciaSQL= Conexion.createStatement();
			Rs = SenteciaSQL.executeQuery("SELECT * FROM Libreria");
			
			while (Rs.next())
			{
				Datos[0]=Rs.getString("Id_Libro");
				Datos[1]=Rs.getString("Titulo");
				Datos[2]=Rs.getString("Autor");
				Datos[3]=Rs.getString("Anio_Publicacion");
				Datos[4]=Rs.getString("Genero");
				Datos[5]=Rs.getString("Existencias");
				
				ModeloT.addRow(Datos);
			}
			}catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error "+e.getMessage());
		}
	}
}
