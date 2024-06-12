import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
public class VentaPDF {
private String fechaActual = "";
private String nombrepdf = "";

//Metodo para obtener la factura
public void generarFacturaPDF() {
	try {
		//Cargar Fecha Actual
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        fechaActual = dateFormat.format(date);
        String fechaNueva = "";
        for(int i = 0;i < fechaActual.length(); i++) {
        	if(fechaActual.charAt(i) == '/') {
        		fechaNueva = fechaActual.replace("/", "_");
        	}
        }
        nombrepdf = "Venta_ "+ fechaNueva + ".pdf";
        FileOutputStream archivo;
        File file = new File("src/pdf/"+ nombrepdf);
        archivo = new FileOutputStream(file);
        Document doc = new Document();
        PdfWriter.getInstance(doc, archivo);
        doc.open();
        Image img = Image.getInstance("bin/img/logo.png");
        Paragraph fecha = new Paragraph();
        Font negrita =new Font(Font.FontFamily.TIMES_ROMAN,12,Font.BOLD, BaseColor.BLUE);
        fecha.add(Chunk.NEWLINE);//Agregar una nueva linea
        fecha.add("Factura 001\n" + "Fecha de venta :" + fechaActual);
        PdfPTable Encabezado = new PdfPTable(4);
        Encabezado.setWidthPercentage(100);
        Encabezado.getDefaultCell().setBorder(0);
        //Tamaño de las celdas
        float[] columnaencabezado = new float[] {20f,40f,70f,70f};
        Encabezado.setWidths(columnaencabezado);
        Encabezado.setHorizontalAlignment(Element.ALIGN_LEFT);
        //Agregar celdas
        Encabezado.addCell(img);
        Encabezado.addCell("");
        Encabezado.addCell("LIBRERIA TEC");
        Encabezado.addCell(fecha);
        doc.add(Encabezado);
        //Cuerpo
        PdfPTable carrito = new PdfPTable(4);
        carrito.setWidthPercentage(100);
        carrito.getDefaultCell().setBorder(0);
        float[] columncarrito = new float[] {15f,50f,15f,20f};
        carrito.setWidths(columncarrito);
        carrito.setHorizontalAlignment(Element.ALIGN_LEFT);
        PdfPCell libro1 = new PdfPCell(new Phrase("Cantidad",negrita));
        PdfPCell libro2 = new PdfPCell(new Phrase("Libro",negrita));
        PdfPCell libro3 = new PdfPCell(new Phrase("Precio Unitario",negrita));
        PdfPCell libro4 = new PdfPCell(new Phrase("Total",negrita));
        
        //Quitar border
        libro1.setBorder(0);
        libro2.setBorder(0);
        libro3.setBorder(0);
        libro4.setBorder(0);
        //Agregar color
        libro1.setBackgroundColor(BaseColor.LIGHT_GRAY);
        libro2.setBackgroundColor(BaseColor.LIGHT_GRAY);
        libro3.setBackgroundColor(BaseColor.LIGHT_GRAY);
        libro4.setBackgroundColor(BaseColor.LIGHT_GRAY);
        //Agg celda
        carrito.addCell(libro1);
        carrito.addCell(libro2);
        carrito.addCell(libro3);
        carrito.addCell(libro4);
        
        for(int i = 0; i < Menu_VentasJ.Tb_Carrito.getRowCount(); i++) {
        	String libro = Menu_VentasJ.Tb_Carrito.getValueAt(i, 2).toString();
        	String cantidad = Menu_VentasJ.Tb_Carrito.getValueAt(i, 1).toString();
        	String precio = Menu_VentasJ.Tb_Carrito.getValueAt(i, 3).toString();
        	String total = Menu_VentasJ.Tb_Carrito.getValueAt(i, 4).toString();
        	
        	carrito.addCell(libro);
        	carrito.addCell(cantidad);
        	carrito.addCell(precio);
        	carrito.addCell(total);
        	
        	
        }
        //Agregar al documento
        doc.add(carrito);
        //Total general
        Paragraph info = new Paragraph();
        info.add(Chunk.NEWLINE);
        info.add("Total sin IVA : " + Menu_VentasJ.TotalsinIVA.getText());
        info.add(Chunk.NEWLINE);
        info.add("IVA : " + Menu_VentasJ.Txt_IVA.getText());
        info.add(Chunk.NEWLINE);
        info.add("Total general: " + Menu_VentasJ.Txt_Total.getText());
        info.setAlignment(Element.ALIGN_RIGHT);
        doc.add(info);
        //Firma
        Paragraph firma = new Paragraph();
        firma.add(Chunk.NEWLINE);
        firma.add("Firma\n\n");
        firma.add("_____________________________");
        firma.setAlignment(Element.ALIGN_CENTER);
        doc.add(firma);
        //Mensaje
        Paragraph mensaje = new Paragraph();
        mensaje.add(Chunk.NEWLINE);
        mensaje.add("Gracias por su compra");
        mensaje.setAlignment(Element.ALIGN_CENTER);
        doc.add(mensaje);
        //Cerrar el documento y archivo
        doc.close();
        archivo.close();
        
        //Abrir el ticket al ser generado
        Desktop.getDesktop().open(file);
        
        
	}
	catch(DocumentException | IOException e) {
		JOptionPane.showMessageDialog(null, "Error al generar el ticket" + e);
	}
}


}
