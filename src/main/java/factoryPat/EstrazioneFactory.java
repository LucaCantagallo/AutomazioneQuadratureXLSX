package factoryPat;

import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;
import java.util.List;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

//prende l'intero file
//legge
//seleziona solo quello che gli interessa e lo mette nelle variabili con un metodo unico
//restituisce i getter

//excel #	data	da registrare	registrate	mancanti	% mancanti

public class EstrazioneFactory {

	private String percorsoFile;
	
	private String dat;
	private int daRegistrare;
	private int registrate;
	private int mancanti;
	private String percMancanti;
	
	public EstrazioneFactory(String percorsoFile) {
		this.percorsoFile = percorsoFile;
	}
	
	public void compute() {
		
		try {
			File file = new File(percorsoFile);
		    List<String> righe = FileUtils.readLines(file, "UTF-8");

		    // Leggi la prima, la terza e la sesta riga
		    this.dat = this.insertDat(righe.get(0));
		    this.daRegistrare = this.insertNum(righe.get(16));
		    this.registrate = this.insertNum(righe.get(17));
		    this.mancanti = this.insertNum(righe.get(19));
		    this.percMancanti = this.insertStr(righe.get(20));
		    

		    // Puoi fare qualcos'altro con le righe lette
		} catch (IOException e) {
		    e.printStackTrace();
		}
		
	}
	

	

	@Override
	public String toString() {
		return "EstrazioneFactory [percorsoFile=" + percorsoFile + ", dat=" + dat + ", daRegistrare=" + daRegistrare
				+ ", registrate=" + registrate + ", mancanti=" + mancanti + ", percMancanti=" + percMancanti + "]";
	}

	public String getPercorsoFile() {
		return percorsoFile;
	}
	public void setPercorsoFile(String percorsoFile) {
		this.percorsoFile = percorsoFile;
	}
	public String getDat() {
		return dat;
	}
	public void setDat(String dat) {
		this.dat = dat;
	}
	public int getDaRegistrare() {
		return daRegistrare;
	}
	public void setDaRegistrare(int daRegistrare) {
		this.daRegistrare = daRegistrare;
	}
	public int getRegistrate() {
		return registrate;
	}
	public void setRegistrate(int registrate) {
		this.registrate = registrate;
	}
	public int getMancanti() {
		return mancanti;
	}
	public void setMancanti(int mancanti) {
		this.mancanti = mancanti;
	}
	public String getPercMancanti() {
		return percMancanti;
	}
	public void setPercMancanti(String percMancanti) {
		this.percMancanti = percMancanti;
	}
	
	
	private String insertDat(String rigDat) {
		String dat = rigDat.split(": ")[1];
		SimpleDateFormat formatoIngresso = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat formatoUscita = new SimpleDateFormat("dd/MM/yyyy");
        String dataFormattata;

        try {
            Date data = formatoIngresso.parse(dat);
            dataFormattata = formatoUscita.format(data);

        } catch (ParseException e) {
        	dataFormattata=dat;
            e.printStackTrace();
        }
		return dataFormattata;
	}
	
	private int insertNum(String string) {
		String num = string.split(": ")[1];
		return Integer.parseInt(num);
	}
	
	private String insertStr(String string) {
		return string.split(": ")[1];
	}
	
	
	
}
