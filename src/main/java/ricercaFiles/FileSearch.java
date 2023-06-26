package ricercaFiles;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileSearch {
	
    public static List<String> ricerca(String basePath, String nomeFile) {
        File baseDir = new File(basePath);

        List<String> fileTrovati = inserimentoFiles(baseDir, nomeFile);
        
        return fileTrovati;
    }
    
    private static List<String> inserimentoFiles(File dir, String nomeFile) {
        List<String> foundFiles = new ArrayList<>();
        
        File[] files = dir.listFiles();
        
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    foundFiles.addAll(inserimentoFiles(file, nomeFile));
                } else if (file.isFile() && file.getName().equals(nomeFile)) {
                    foundFiles.add(file.getAbsolutePath());
                }
            }
        }
        
        return foundFiles;
    }
}