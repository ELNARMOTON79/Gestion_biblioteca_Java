import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class Ctrl_RegistrarVenta {

	public static int idCabeceraRegistrada;
	java.math.BigDecimal idColvar;
	
	
	public boolean guardar(CabeceraVenta objeto) {
	boolean respuesta = false;
	Connection cn = Conexion.conectar();
	try {
		PreparedStatement consulta = cn.prepareStatement("INSERT INTO Cabecera_Venta VALUES(?, ?, ?)",
				Statement.RETURN_GENERATED_KEYS);
		consulta.setInt(1, 0);
		consulta.setDouble(2, objeto.getTotal());
		consulta.setString(3, objeto.getFechaVenta());
		if(consulta.executeUpdate() > 0) {
			respuesta = true;
		}
	  ResultSet rs = consulta.getGeneratedKeys();
		while(rs.next()) {
			idColvar = rs.getBigDecimal(1);
			idCabeceraRegistrada = idColvar.intValue();
		}
		
		cn.close();
	}
	catch(SQLException e){
		JOptionPane.showMessageDialog(null, "Error" + e);
	}
	return respuesta;
}
	public boolean guardarDetalle(DetalleVenta objeto) {
		boolean respuesta = false;
		Connection cn = Conexion.conectar();
		try {
			PreparedStatement consulta = cn.prepareStatement("INSERT INTO Detalle_Venta VALUES(?, ?, ?, ?, ?, ?)");
			consulta.setInt(1, 0);
			consulta.setInt(2, idCabeceraRegistrada);
			consulta.setInt(3, objeto.getId_libro());
			consulta.setInt(4, objeto.getCantidad());
			consulta.setDouble(5, objeto.getPrecio_unidad());
			consulta.setDouble(6, objeto.getTotal());
			
			if(consulta.executeUpdate() > 0) {
				respuesta = true;
			}
	cn.close();
		}
		catch(SQLException e) {
			JOptionPane.showMessageDialog(null, "Error" + e);
		}
		return respuesta;
	}
}
