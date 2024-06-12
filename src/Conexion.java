import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class Conexion {
public static Connection conectar() {
	try {
		Connection cn = DriverManager.getConnection("jdbc:ucanaccess://Base//Libreria.accdb");
		return cn;
	}
	catch(SQLException e) {
		JOptionPane.showMessageDialog(null, "Error Base " + e);
	}
	return null;
}
}
