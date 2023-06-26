package test;

import java.io.File;
import java.text.ParseException;
import java.util.List;

import excel.ExcelUtils;
import factoryPat.EstrazioneFactory;
import ricercaFiles.FileSearch;

public class TestProva {

	public static void main(String[] args) throws ParseException {
		String relativePath = "src/main/resources/#QUADRATURE";
	    String absolutePath = new File(relativePath).getAbsolutePath();
	    List<String> datiStringhe = FileSearch.ricerca(absolutePath, "Statistiche.txt");

	    String fileExcel = absolutePath+"/statsQuadratureCodice.xlsx";
	    ExcelUtils.createExcelFile(fileExcel);
	    ExcelUtils.createHeaderRow();

	    int i = 0;
	    for (String stats : datiStringhe) {
	    	i++;
	        EstrazioneFactory ef = new EstrazioneFactory(stats);
	        ef.compute();
	        System.out.println(ef.toString());
	        ExcelUtils.insertData(i, ef.getDat(), ef.getDaRegistrare(), ef.getRegistrate(), ef.getMancanti(), ef.getPercMancanti());
	    }

	    ExcelUtils.saveExcelFile(fileExcel);
	}

}
