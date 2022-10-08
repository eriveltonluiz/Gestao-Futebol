package br.com.erivelton.canchafut.beans.arquivos;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.primefaces.model.file.UploadedFile;

import br.com.erivelton.canchafut.model.Jogador;

@SuppressWarnings("resource")
public class LoadDocuments {

	public static List<XWPFParagraph> getDocumentDocx(InputStream arquivo, UploadedFile fileInserir) {

		try {
			XWPFDocument document = new XWPFDocument(OPCPackage.open(arquivo));
			List<XWPFParagraph> paragrafos = document.getParagraphs();
			return paragrafos;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static List<Jogador> getDocumentXlsx(InputStream arquivo, UploadedFile fileInserir) {

		List<Jogador> listaJogadores = new ArrayList<>();

		try {
			// Encontra a instância da pasta de trabalho para o arquivo XLSX
			XSSFWorkbook workbook = new XSSFWorkbook(arquivo);

			// Retorna a primeira planilha da pasta de trabalho XLSX
			XSSFSheet sheettime = workbook.getSheetAt(0);

			// Obtém o iterador para todas as linhas da planilha atual
			Iterator<Row> rowIterator = sheettime.iterator();

			// Percorrendo cada linha do arquivo XLSX
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();
				Iterator<Cell> cellIterator = row.cellIterator();

				Jogador jog = new Jogador();

				// Para cada linha, itere por cada coluna
				while (cellIterator.hasNext()) {
					Cell cell = cellIterator.next();
					
					switch (cell.getColumnIndex()) {
						case 0:
							jog.setNome(cell.getStringCellValue());
							break;
	
						case 1:
							jog.setRg(Integer.valueOf((int) cell.getNumericCellValue()));
							break;
	
						case 2:
							jog.setNumCamisa(Integer.valueOf((int) cell.getNumericCellValue()));
							break;
	
						case 3:
							jog.setDataNascimento(new SimpleDateFormat("dd/MM/yyyy").parse(cell.getStringCellValue()));
							break;
					}
				}
				listaJogadores.add(jog);
			}
			return listaJogadores;
		
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
