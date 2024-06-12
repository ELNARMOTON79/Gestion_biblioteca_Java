
public class CabeceraVenta {
//Atributos
	private int idCabeceraVenta;
	private double total;
	private String fechaVenta;
	
	public CabeceraVenta() {
		  this.idCabeceraVenta = 0;
		  this.total = 0.0;
		  this.fechaVenta = "";
	  }
	
	
	public CabeceraVenta(int idCabeceraVenta, double total, String fechaVenta) {
		this.idCabeceraVenta = 0;
		this.total = 0.0;
		this.fechaVenta = "";
	}

	public int getIdCabeceraVenta() {
		return idCabeceraVenta;
	}

	public void setIdCabeceraVenta(int idCabeceraVenta) {
		this.idCabeceraVenta = idCabeceraVenta;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public String getFechaVenta() {
		return fechaVenta;
	}

	public void setFechaVenta(String fechaVenta) {
		this.fechaVenta = fechaVenta;
	}

	@Override
	public String toString() {
		return "CabeceraVenta [idCabeceraVenta=" + idCabeceraVenta + ", total=" + total + ", fechaVenta=" + fechaVenta
				+ "]";
	}
	
}
