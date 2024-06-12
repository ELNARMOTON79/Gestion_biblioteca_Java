
public class DetalleVenta {
  private int idDetalleVenta;
  private int idCabeceraVenta;
  private int id_libro;
  
  private String Titulo;
  private int cantidad;
  private double precio_unidad;
  private double total;
  
  public DetalleVenta() {
	  this.idDetalleVenta = 0;
	  this.idCabeceraVenta = 0;
	  this.id_libro = 0;
	  this.Titulo = "";
	  this.cantidad = 0;
	  this.precio_unidad = 0.0;
	  this.total = 0.0;
	  
  }

public DetalleVenta(int idDetalleVenta, int idCabeceraVenta, int id_libro, String titulo, int cantidad,
		double precio_unidad, double total) {
	super();
	this.idDetalleVenta = idDetalleVenta;
	this.idCabeceraVenta = idCabeceraVenta;
	this.id_libro = id_libro;
	Titulo = titulo;
	this.cantidad = cantidad;
	this.precio_unidad = precio_unidad;
	this.total = total;
}

public int getIdDetalleVenta() {
	return idDetalleVenta;
}

public void setIdDetalleVenta(int idDetalleVenta) {
	this.idDetalleVenta = idDetalleVenta;
}

public int getIdCabeceraVenta() {
	return idCabeceraVenta;
}

public void setIdCabeceraVenta(int idCabeceraVenta) {
	this.idCabeceraVenta = idCabeceraVenta;
}

public int getId_libro() {
	return id_libro;
}

public void setId_libro(int id_libro) {
	this.id_libro = id_libro;
}

public String getTitulo() {
	return Titulo;
}

public void setTitulo(String titulo) {
	Titulo = titulo;
}

public int getCantidad() {
	return cantidad;
}

public void setCantidad(int cantidad) {
	this.cantidad = cantidad;
}

public double getPrecio_unidad() {
	return precio_unidad;
}

public void setPrecio_unidad(double precio_unidad) {
	this.precio_unidad = precio_unidad;
}

public double getTotal() {
	return total;
}

public void setTotal(double total) {
	this.total = total;
}

@Override
public String toString() {
	return "DetalleVenta [idDetalleVenta=" + idDetalleVenta + ", idCabeceraVenta=" + idCabeceraVenta + ", id_libro="
			+ id_libro + ", Titulo=" + Titulo + ", cantidad=" + cantidad + ", precio_unidad=" + precio_unidad
			+ ", total=" + total + "]";
}
  
}