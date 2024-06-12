import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JLabel;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.Color;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.JSeparator;


public class Menu_VentasJ extends JDialog {

	/**
	 * Launch the application.
	 */
	JComboBox Cmb_Libros = new JComboBox();
	public static JTable Tb_Carrito;
	
	private DefaultTableModel Modelo;
	//Lista
	ArrayList<DetalleVenta> listalibro= new ArrayList<>();
	private DetalleVenta libro;
	private int id_libro = 0;
	private int idLibro = 0;
	private String Titulo = "";
	private int stock = 0;
	private double precio_unidad = 0.0;
	
	private int cantidad = 0;//Cantidad de libros a comprar
	private double total = 0.0;
	private JTextField Txt_Cantidad ;
	
	private int auxIdDetalle = 1;
	private double totalgeneral = 0.0;
	private double totalconIVA = 0.0;
	private double IVA = 0.0;
	public static JTextField Txt_Total;
	private JTextField Txt_Pago;
	private JButton Btn_Calcular;
	private JTextField Txt_Cambio;
	private JButton Btn_RegistrarVenta;
	private JTextField Txt_Buscar;
	JLabel Lbl_Stock = new JLabel("Existencias : ");
	public static JTextField Txt_IVA;
	public static JTextField TotalsinIVA;
	JLabel Lbl_PrecioU = new JLabel("Precio por unidad : ");
	private JLabel Lbl_Pago;
	private JLabel Lbl_IVA;
	private JLabel Lbl_TotalsinIVA;
	private JLabel Lbl_TotalG;
	private JLabel Lbl_Cambio;
	private JLabel Lbl_Buscar;
	private JLabel Libro;
	private JLabel LblCantidad;
	private JPanel panel_2;
	private final JPanel panel_3 = new JPanel();
	private JSeparator separator;
	private JButton BtnVolver;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu_VentasJ dialog = new Menu_VentasJ();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the dialog.
	 */
	
	public Menu_VentasJ() {
		setUndecorated(true);
		
		
		
		this.CargarComboLibros();
		
       
		setBounds(100, 100, 921, 550);
		getContentPane().setLayout(null);
		this.setLocationRelativeTo(null);
		Cmb_Libros.setFont(new Font("Arial", Font.PLAIN, 11));
		Cmb_Libros.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				registrosactuales();
			}
		});
		Cmb_Libros.setToolTipText("Seleccione en libro aqui");
		Cmb_Libros.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
			}
			@Override
			public void keyTyped(KeyEvent e) {
			}
		});
		
		
		Cmb_Libros.setBounds(104, 47, 137, 22);
		
		getContentPane().add(Cmb_Libros);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 0, 160));
		panel.setBounds(48, 96, 812, 223);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		Tb_Carrito = new JTable();
		
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 792, 201);
		panel.add(scrollPane);
		
		
		
		Tb_Carrito.addMouseListener(new MouseAdapter() {
			int idArrayList = 0;
			@Override
			public void mouseClicked(MouseEvent e) {
				int fila_point = Tb_Carrito.rowAtPoint(e.getPoint());
				int columna_point = 0;
				if(fila_point > -1) {
					idArrayList = (int) Modelo.getValueAt(fila_point, columna_point);
				}
				int opcion = JOptionPane.showConfirmDialog(null, "¿Estás seguro de quitar el producto?","Venta", JOptionPane.YES_OPTION,JOptionPane.NO_OPTION);
				//Opciones de confirm dialog si = 0, no = 1, cancel = 2, close = -1
				switch(opcion) {
				case 0:
				listalibro.remove(idArrayList - 1);
				CalcularTotalPagar();
				listatablalibros();
				Txt_Cambio.setText("");
				Txt_Pago.setText("");
				TotalsinIVA.setText("");
				Txt_Total.setText("0.0");
				Txt_IVA.setText("");
				Lbl_Stock.setText("Existencias : ");
				Lbl_PrecioU.setText("Precio por unidad : ");
				break;
				case 1://Presiona no
				break;
				default://Sea que presione cancel o close 
				break;
				
				
				
				}
				
			}
		});
		scrollPane.setViewportView(Tb_Carrito);
		
		Txt_Total = new JTextField();
		Txt_Total.setText("0.0");
		Txt_Total.setEditable(false);
		Txt_Total.setBounds(665, 415, 121, 20);
		getContentPane().add(Txt_Total);
		Txt_Total.setColumns(10);
		
		Txt_Pago = new JTextField();
		Txt_Pago.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
			char c = e.getKeyChar();
			if(((c < '0') || c > '9') && (c!=KeyEvent.VK_BACK_SPACE) && (c!='.' || Txt_Pago.getText().contains("."))) {
				e.consume();
			}
			}
	
		});
		Txt_Pago.setEnabled(false);
		Txt_Pago.setBounds(665, 330, 121, 20);
		getContentPane().add(Txt_Pago);
		Txt_Pago.setColumns(10);
		
		Btn_Calcular = new JButton("Calcular");
		Btn_Calcular.setBackground(new Color(255, 255, 255));
		Btn_Calcular.setForeground(new Color(0, 0, 160));
		Btn_Calcular.setFont(new Font("Arial Black", Font.BOLD, 14));
		Btn_Calcular.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!Txt_Pago.getText().isEmpty()) {
					//Validar que el usuario no ingrese otro caracter que no sea numerico
					boolean validacion = validarcambio(Txt_Pago.getText());
					if(validacion == true) {
						//Validar pago sea mayor a 0
						double pago = Double.parseDouble(Txt_Pago.getText().trim());
						double total = Double.parseDouble(Txt_Total.getText().trim());
						if(pago < total) {
							JOptionPane.showMessageDialog(null, "Dinero insuficiente, no se puede realizar el pago");
						}
						else {
							double cambio = (pago - total);
							double cambi =(double) Math.round(cambio*100d)/100;
							String camb = String.valueOf(cambi);
							Txt_Cambio.setText(camb);
							
						}
					}
					else {
						JOptionPane.showMessageDialog(null, "No se admiten caracteres no numericos");
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "Ingrese pago para calcular cambio");
				}
			}
		});
		Btn_Calcular.setEnabled(false);
		Btn_Calcular.setBounds(665, 477, 121, 23);
		getContentPane().add(Btn_Calcular);
		
		Txt_Cambio = new JTextField();
		Txt_Cambio.setEditable(false);
		Txt_Cambio.setBounds(665, 446, 121, 20);
		getContentPane().add(Txt_Cambio);
		Txt_Cambio.setColumns(10);
		Lbl_Stock.setForeground(new Color(255, 255, 255));
		Lbl_Stock.setFont(new Font("Arial Black", Font.BOLD, 14));
		
		
		Lbl_Stock.setBounds(81, 80, 160, 14);
		getContentPane().add(Lbl_Stock);
		
		Txt_IVA = new JTextField();
		Txt_IVA.setEditable(false);
		Txt_IVA.setBounds(665, 354, 121, 20);
		getContentPane().add(Txt_IVA);
		Txt_IVA.setColumns(10);
		
		TotalsinIVA = new JTextField();
		TotalsinIVA.setEditable(false);
		TotalsinIVA.setBounds(665, 385, 121, 20);
		getContentPane().add(TotalsinIVA);
		TotalsinIVA.setColumns(10);
		
		Lbl_Pago = new JLabel("Pago : ");
		Lbl_Pago.setFont(new Font("Arial Black", Font.BOLD, 14));
		Lbl_Pago.setBounds(580, 333, 59, 14);
		getContentPane().add(Lbl_Pago);
		
		Lbl_IVA = new JLabel("IVA(16%) :");
		Lbl_IVA.setFont(new Font("Arial Black", Font.BOLD, 14));
		Lbl_IVA.setBounds(563, 357, 89, 14);
		getContentPane().add(Lbl_IVA);
		
		Lbl_TotalsinIVA = new JLabel("Total sin IVA : ");
		Lbl_TotalsinIVA.setFont(new Font("Arial Black", Font.BOLD, 14));
		Lbl_TotalsinIVA.setBounds(528, 388, 137, 14);
		getContentPane().add(Lbl_TotalsinIVA);
		
		Lbl_TotalG = new JLabel("Total :");
		Lbl_TotalG.setFont(new Font("Arial Black", Font.BOLD, 14));
		Lbl_TotalG.setBounds(584, 418, 68, 14);
		getContentPane().add(Lbl_TotalG);
		
		Lbl_Cambio = new JLabel("Cambio : ");
		Lbl_Cambio.setFont(new Font("Arial Black", Font.BOLD, 14));
		Lbl_Cambio.setBounds(580, 449, 75, 14);
		getContentPane().add(Lbl_Cambio);
		
		Lbl_Buscar = new JLabel("Buscar :");
		Lbl_Buscar.setForeground(new Color(255, 255, 255));
		Lbl_Buscar.setFont(new Font("Arial Black", Font.BOLD, 14));
		Lbl_Buscar.setBounds(251, 51, 68, 14);
		getContentPane().add(Lbl_Buscar);
		
		Libro = new JLabel("Libro :");
		Libro.setFont(new Font("Arial Black", Font.BOLD, 14));
		Libro.setForeground(new Color(255, 255, 255));
		Libro.setBounds(35, 51, 59, 14);
		getContentPane().add(Libro);
		
		LblCantidad = new JLabel("Cantidad : ");
		LblCantidad.setForeground(new Color(255, 255, 255));
		LblCantidad.setFont(new Font("Arial Black", Font.BOLD, 14));
		LblCantidad.setBounds(439, 51, 86, 14);
		getContentPane().add(LblCantidad);
		
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
		btnX.setBounds(851, 11, 60, 50);
		getContentPane().add(btnX);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(0, 0, 160));
		panel_1.setBounds(0, 0, 921, 97);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		Txt_Buscar = new JTextField();
		Txt_Buscar.setBounds(321, 48, 108, 20);
		panel_1.add(Txt_Buscar);
		Txt_Buscar.setToolTipText("En caso de querer buscar un libro en especifico");
		Txt_Buscar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				busqueda();
				Lbl_PrecioU.setText("Precio por unidad : ");
				Lbl_Stock.setText("Existencias : ");
			}
		});
		Txt_Buscar.setColumns(10);
		
		JButton Btn_Agregar = new JButton("Agregar");
		Btn_Agregar.setBackground(new Color(255, 255, 255));
		Btn_Agregar.setForeground(new Color(0, 0, 160));
		Btn_Agregar.setFont(new Font("Arial Black", Font.BOLD, 14));
		Btn_Agregar.setBounds(614, 47, 117, 23);
		panel_1.add(Btn_Agregar);
		
		Txt_Cantidad = new JTextField();
		Txt_Cantidad.setBounds(532, 48, 41, 20);
		panel_1.add(Txt_Cantidad);
		Txt_Cantidad.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if(c<'0' || c>'9') {
					e.consume();
				}
			}
		});
		Txt_Cantidad.setColumns(10);
		Lbl_PrecioU.setForeground(new Color(255, 255, 255));
		Lbl_PrecioU.setFont(new Font("Arial Black", Font.BOLD, 14));
		Lbl_PrecioU.setBounds(242, 83, 273, 14);
		panel_1.add(Lbl_PrecioU);
		
		panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 255, 255));
		panel_2.setBounds(0, 96, 921, 454);
		getContentPane().add(panel_2);
		panel_2.setLayout(null);
		panel_3.setBackground(new Color(0, 0, 160));
		panel_3.setBounds(0, 412, 921, 42);
		panel_2.add(panel_3);
		panel_3.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setIcon(new ImageIcon(Menu_VentasJ.class.getResource("/src/LogoMini (1).png")));
		lblNewLabel.setBounds(0, 0, 114, 42);
		panel_3.add(lblNewLabel);
		
		JLabel lblVentaDeLibros = new JLabel("VENTA DE LIBROS");
		lblVentaDeLibros.setHorizontalAlignment(SwingConstants.CENTER);
		lblVentaDeLibros.setForeground(Color.WHITE);
		lblVentaDeLibros.setFont(new Font("Arial Black", Font.BOLD, 21));
		lblVentaDeLibros.setBackground(Color.WHITE);
		lblVentaDeLibros.setBounds(305, 11, 302, 31);
		panel_3.add(lblVentaDeLibros);
		
		separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setForeground(Color.WHITE);
		separator.setBounds(165, 0, 62, 42);
		panel_3.add(separator);
		
		BtnVolver = new JButton("Volver");
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
		BtnVolver.setBounds(10, 361, 104, 48);
		panel_2.add(BtnVolver);
		
		Btn_RegistrarVenta = new JButton("Registrar Venta");
		Btn_RegistrarVenta.setBounds(48, 264, 206, 48);
		panel_2.add(Btn_RegistrarVenta);
		Btn_RegistrarVenta.setBackground(new Color(0, 0, 160));
		Btn_RegistrarVenta.setForeground(new Color(255, 255, 255));
		Btn_RegistrarVenta.setFont(new Font("Arial Black", Font.BOLD, 16));
		Btn_RegistrarVenta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean respuesta = true;

				do {
				    int confirmacion = JOptionPane.showConfirmDialog(null, "¿Estás seguro de registrar la venta?","Venta", JOptionPane.YES_OPTION,JOptionPane.NO_OPTION);
				    
				    if (confirmacion == JOptionPane.YES_OPTION) {
				    	CabeceraVenta cabeceraVenta = new CabeceraVenta();
						DetalleVenta detalleVenta = new DetalleVenta();
						Ctrl_RegistrarVenta controlVenta = new Ctrl_RegistrarVenta();
						
						String fechaActual = "";
				        Date date = new Date();
				        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				        fechaActual = dateFormat.format(date);
						if(listalibro.size() > 0) {
						ObtenerIdLibro();
						//Registrar
						JOptionPane.showMessageDialog(null, "Venta registrada");
						cabeceraVenta.setIdCabeceraVenta(0);
						cabeceraVenta.setTotal(Double.parseDouble(Txt_Total.getText()));
						cabeceraVenta.setFechaVenta(fechaActual);
						if(controlVenta.guardar(cabeceraVenta)) {
						int r = JOptionPane.showConfirmDialog(null, "¿Deseas generar ticket?","Venta", JOptionPane.YES_OPTION,JOptionPane.NO_OPTION);
						switch(r) {
						case 0:
							VentaPDF pdf = new VentaPDF();
							pdf.generarFacturaPDF();
							break;
						default:
							break;
						}
							
							for(DetalleVenta elemento : listalibro) {
								detalleVenta.setIdDetalleVenta(0);
								detalleVenta.setIdCabeceraVenta(0);
								detalleVenta.setId_libro(elemento.getId_libro());
								detalleVenta.setCantidad(elemento.getCantidad());
								detalleVenta.setPrecio_unidad(elemento.getPrecio_unidad());
								detalleVenta.setTotal(elemento.getTotal());
								if(controlVenta.guardarDetalle(detalleVenta)) {
									
									//JOptionPane.showMessageDialog(null, "detalles guardados");
									Txt_Total.setText("0.0");
									Txt_Pago.setText("");
									Txt_Cambio.setText("0.0");
									Txt_IVA.setText("0.0");
									TotalsinIVA.setText("0.0");
									auxIdDetalle = 1;
									CargarComboLibros();
									ActualizarStock(elemento.getId_libro(), elemento.getCantidad());
									
								}
								else {
									JOptionPane.showMessageDialog(null, "Error al guardar detalles");
								}
								
								
								
							}
							//Vaciar el carrito
							
							listalibro.clear();
							listatablalibros();
							
						}
						else {
							JOptionPane.showMessageDialog(null, "Error al guardar cabecera");
						}
						}else
						{
						JOptionPane.showMessageDialog(null, "!Seleccione un libro¡");
						}
				}
				    else {
				        respuesta = false;
				    }
				    
				    if (respuesta) {
				        int respuestaNuevaVenta = JOptionPane.showConfirmDialog(null, "¿Deseas realizar otra venta?","Venta", JOptionPane.YES_OPTION,JOptionPane.NO_OPTION);
				        
				        if (respuestaNuevaVenta != JOptionPane.YES_OPTION) {
				            respuesta = false;
				            dispose();
				            BarraBusquedaAdmin Admin = new BarraBusquedaAdmin();
				            Admin.setVisible(true);
				        }
				    }
				} while (respuesta);

			}	
				
		});
		Btn_Agregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String combo = Cmb_Libros.getSelectedItem().toString();
				//Validar que seleccione un libro
				if(combo.equalsIgnoreCase("Seleccione un libro")) {
					JOptionPane.showMessageDialog(null, "Seleccione un libro");
				}
				else {
					//Validar que ingrese una cantidad
					if(!Txt_Cantidad.getText().isEmpty()) {
					//Validar que el usuario no ingrese caracteres no numericos
						boolean validacion = validar(Txt_Cantidad.getText());
						if(validacion == true) {
							//Validar que la cantidad sea mayor a 0
							if(Integer.parseInt(Txt_Cantidad.getText()) > 0) {
								cantidad = Integer.parseInt(Txt_Cantidad.getText());
								//Metodo para obtener datos del libro
								DatosDelLibro();
								
								//Validar que la cantidad selecciona no se mayor al stock
								if(cantidad <= stock) {
								 total = precio_unidad * cantidad;
								 
								 total = (double) Math.round(total * 100)/100;
								 
								 //Se crea un nv libro
								 libro = new DetalleVenta(auxIdDetalle,1,idLibro,Titulo,Integer.parseInt(Txt_Cantidad.getText()), precio_unidad,total);
					      		 listalibro.add(libro);
								 JOptionPane.showMessageDialog(null, "Libro agregado");
								 auxIdDetalle++;
								 Txt_Cantidad.setText("");
								 Lbl_Stock.setText("Existencias :");
								 Lbl_PrecioU.setText("Precio por unidad : ");
								 CargarComboLibros();
								 
								 CalcularTotalPagar();
								 Txt_Pago.setEnabled(true);
								 Btn_Calcular.setEnabled(true);
								}
								else
								{
									JOptionPane.showMessageDialog(null, "La cantidad supera el stock");
								}
							}
							else
							{
								JOptionPane.showMessageDialog(null, "La cantidad no puede ser (0), ni negativa");
							}
						}
						else
						{
						JOptionPane.showMessageDialog(null, "En la cantidad no se admiten caracteres no numericos");
						}
					}
					else {
						JOptionPane.showMessageDialog(null, "El campo cantidad no puede estar vacio");
					}
				}
				listatablalibros();
			}
		});
		this.Tabla();
		
      
	}
	private void Tabla() {
		Modelo = new DefaultTableModel();
		//Añadir columnas
		Modelo.addColumn("N.de producto");
		Modelo.addColumn("Titulo");
		Modelo.addColumn("Cantidad");
		Modelo.addColumn("Precio por unidad");
		Modelo.addColumn("Total");
		Modelo.addColumn("Acción");
		this.Tb_Carrito.setModel(Modelo);
		TableColumnModel columnModel = Tb_Carrito.getColumnModel();

		// Establecer los anchos deseados para cada columna
		int[] columnWidths = {25, 200,50,50, 20, 50}; // Anchos deseados para cada columna en píxeles

		for (int i = 0; i < columnWidths.length; i++) {
		    TableColumn column = columnModel.getColumn(i);
		    column.setPreferredWidth(columnWidths[i]);
		}
		
		
	}
	//Metodo para validar caja de texto
	private boolean validar(String valor) {
		try {
			int num = Integer.parseInt(valor);
			return true;
		}
		catch(NumberFormatException e){
			return false;
			
		}
	}
	private boolean validarcambio(String valor) {
		try {
			double num = Double.parseDouble(valor);
			return true;
		}
		catch(NumberFormatException e){
			return false;
			
		}
	}
	private void DatosDelLibro() {
		try {
			String sql = "SELECT * FROM Libreria WHERE Titulo = '"+ Cmb_Libros.getSelectedItem() + "'";
			Connection cn = Conexion.conectar();
			Statement st;
			st = cn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
				idLibro = rs.getInt("Id");
				Titulo = rs.getString("Titulo");
				precio_unidad = rs.getDouble("Precio");
                stock = rs.getInt("Stock");
					
			}
			
		}
		catch(SQLException e) {
			JOptionPane.showMessageDialog(null, "Error" + e);
		}
	}
	private void listatablalibros() {
		Modelo.setRowCount(listalibro.size());
		for(int i = 0; i<listalibro.size(); i++) {
			Modelo.setValueAt(i+1, i, 0);
			Modelo.setValueAt(listalibro.get(i).getTitulo(), i, 1);
			Modelo.setValueAt(listalibro.get(i).getCantidad(), i, 2);
			Modelo.setValueAt(listalibro.get(i).getPrecio_unidad(), i, 3);
			Modelo.setValueAt(listalibro.get(i).getTotal(), i, 4);
			Modelo.setValueAt("Eliminar", i, 5);
			
		}
		Tb_Carrito.setModel(Modelo);
	}
	private void CalcularTotalPagar() {
		IVA = 0.0;
		totalgeneral = 0.0;
		totalconIVA = 0.0;
		for(DetalleVenta elemento : listalibro) {
			totalgeneral += elemento.getTotal();
		}
		IVA = totalgeneral*.16;
		totalconIVA = totalgeneral + IVA;
		totalgeneral = (double)Math.round(totalgeneral * 1000)/1000;
		IVA = (double)Math.round(IVA * 1000)/1000;
		totalconIVA = (double)Math.round(totalconIVA * 10000)/10000;
		
		TotalsinIVA.setText(String.valueOf(totalgeneral));
		Txt_Total.setText(String.valueOf(totalconIVA));
		Txt_IVA.setText(String.valueOf(IVA));
		
		
	}
	private void ObtenerIdLibro() {
		try {
			String sql = "SELECT * FROM Libreria WHERE Titulo = '"+ Cmb_Libros.getSelectedItem() + "'";
			Connection cn = Conexion.conectar();
			Statement st;
			st = cn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
				id_libro = rs.getInt("Id_Libro");
			}
			cn.close();
			
		}
		catch(SQLException e) {
			JOptionPane.showMessageDialog(null, "Error" + e);
		}
	}
	//Metodo para actualizar stock
	private void ActualizarStock(int idlibro, int cantidad) {
		int cantidadLibrosBD = 0;
		try {
			Connection cn = Conexion.conectar();
			String sql = "SELECT Stock FROM Libreria WHERE Id = '" + idlibro +"'";
			Statement st;
			st = cn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
				cantidadLibrosBD = rs.getInt("Stock");
			}
			cn.close();
			}
		catch(SQLException e) {
			JOptionPane.showMessageDialog(null, "Error" + e);
		}
		try {
			Connection cn = Conexion.conectar();
			PreparedStatement consulta = cn.prepareStatement("UPDATE Libreria SET Stock = ? WHERE Id = '" + idlibro + "'");
			int stocknuevo = cantidadLibrosBD - cantidad;
			consulta.setInt(1, stocknuevo);
			if(consulta.executeUpdate() > 0)
			{
				
			}
			cn.close();
		}
		catch(SQLException e) {
			JOptionPane.showMessageDialog(null, "Error" + e);
		}
	}
	private void CargarComboLibros() {
		Connection cn = Conexion.conectar();
		String sql = "SELECT * FROM Libreria";
		Statement st;
		try {
			st = cn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			Cmb_Libros.removeAllItems();
			Cmb_Libros.addItem("Seleccione un libro");
			while(rs.next()) {
				Cmb_Libros.addItem(rs.getString("Titulo"));
				
			}
			cn.close();
			
		}
		catch(SQLException e) {
			JOptionPane.showMessageDialog(null,"Error" + e);
		}
	}
	private void registrosactuales() {
		try {
			String sql = "SELECT * FROM Libreria WHERE Titulo = '"+ Cmb_Libros.getSelectedItem() + "'";
			Connection cn = Conexion.conectar();
			Statement st;
			st = cn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()) {
			    stock = rs.getInt("Stock");
			    precio_unidad = rs.getDouble("Precio");
			    Lbl_PrecioU.setText("Precio por unidad : " + precio_unidad + " MXN");
				Lbl_Stock.setText("Existencias : " + stock);
			}
		}
		catch(SQLException e) {
			JOptionPane.showMessageDialog(null, "Error" + e);
		}
	}
	private void busqueda() {
		Connection cn = Conexion.conectar();
		String sql = "SELECT Titulo FROM Libreria WHERE Titulo LIKE '%" + Txt_Buscar.getText() + "%'" ;
		Statement st;
		try {
			st = cn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			Cmb_Libros.removeAllItems();
			Cmb_Libros.addItem("Seleccione un libro");
			while(rs.next()) {
				
				Cmb_Libros.addItem(rs.getString("Titulo"));
			}
			cn.close();
		}
		catch(SQLException e) {
			JOptionPane.showMessageDialog(null,"Error" + e);
		}
	}
}
