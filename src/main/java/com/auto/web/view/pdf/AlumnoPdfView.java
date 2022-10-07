package com.auto.web.view.pdf;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.auto.web.models.Alumno;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;




public class AlumnoPdfView{
	
	
	private List<Alumno> listaAlumnos;
	
	public AlumnoPdfView(List<Alumno> listaAlumnos) {
		super();
		this.listaAlumnos=listaAlumnos;
	}
	
	private void escribirCabeceraDeLaTabla(PdfPTable tabla) {
		PdfPCell celda = new PdfPCell();
		celda.setPadding(5);
		
		Font fuente = FontFactory.getFont(FontFactory.HELVETICA);
		
		celda.setPhrase(new Phrase("ID",fuente));
		tabla.addCell(celda);
		
		celda.setPhrase(new Phrase("Dni",fuente));
		tabla.addCell(celda);
		
		celda.setPhrase(new Phrase("Nombre",fuente));
		tabla.addCell(celda);
		
		celda.setPhrase(new Phrase("Apellido",fuente));
		tabla.addCell(celda);
		
		celda.setPhrase(new Phrase("Telefono",fuente));
		tabla.addCell(celda);
		
		celda.setPhrase(new Phrase("Genero",fuente));
		tabla.addCell(celda);
		
		celda.setPhrase(new Phrase("Fec Matricula",fuente));
		tabla.addCell(celda);
		
		celda.setPhrase(new Phrase("Plan",fuente));
		tabla.addCell(celda);
	}

	
	private void escribirDatosDeLaTabla(PdfPTable tabla) {
		
		for(Alumno alumno : listaAlumnos) {
			tabla.addCell(String.valueOf(alumno.getId()));
			tabla.addCell(String.valueOf(alumno.getDni()));
			tabla.addCell(alumno.getNombre());
			tabla.addCell(alumno.getApellido());
			tabla.addCell(alumno.getTelefono());
			tabla.addCell(alumno.getGenero());
			tabla.addCell(alumno.getFecMatricula().toString());
			tabla.addCell(alumno.getPlanes().toString());
		}
	}

	
	public void exportar(HttpServletResponse response) throws DocumentException, IOException {
		Document documento = new Document(PageSize.A4);
		PdfWriter.getInstance(documento, response.getOutputStream());
		
		
		documento.open();
		
		Font fuente = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		fuente.setSize(18);
		
		Paragraph titulo = new Paragraph("Lista de empleados", fuente);
		titulo.setAlignment(Paragraph.ALIGN_CENTER);
		documento.add(titulo);
		
		PdfPTable tabla = new PdfPTable(8);
		tabla.setWidthPercentage(100);
		tabla.setSpacingBefore(15);
		tabla.setWidths(new float[] {1f,2.3f,2.3f,4f, 2.9f, 3.5f, 6f, 4f});
		tabla.setWidthPercentage(110);
		
		escribirCabeceraDeLaTabla(tabla);
		escribirDatosDeLaTabla(tabla);
		
		documento.add(tabla);
		documento.close();
	}
}
