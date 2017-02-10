package editfilename;

import java.io.File;
import file.MusicFile;

public class SortFileName {
	public void sorting(File file, String string){
		
		File tofile = new File(MusicFile.directoryName + "\\" + string + ".mp3");
		file.renameTo(tofile);
	}
	
	public File changeArrayInfo(File file, String string){
		File changeTo = new File(MusicFile.directoryName + "\\" + string + ".mp3");
		return changeTo;
	}
}
