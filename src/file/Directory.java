package file;

import java.io.File;
import javax.swing.JFileChooser;

public class Directory extends MusicFile{
	
	public void guiCall(){
		JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        chooser.showOpenDialog(null);
        File file;
        if(chooser.getSelectedFile()!=null){
        	
        	file=chooser.getSelectedFile();        	
        	setFolderName(file);
        	clearFileList();
        	setFileList();
        }
	}
}
