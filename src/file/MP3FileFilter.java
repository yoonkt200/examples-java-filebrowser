package file;

import java.io.File;
import java.io.FilenameFilter;

public class MP3FileFilter implements FilenameFilter{
	
	public boolean accept(File dir, String name){
		return name.toLowerCase().endsWith(".mp3");
	}

}