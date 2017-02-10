package file;

import java.io.File;
import java.util.ArrayList;

public class MusicFile{
	
	private FileTag filetag = new FileTag();
	
	public static File directoryName=null;
	public static File[] files;
	public static ArrayList<String> filenamelist = new ArrayList<String>();
	public static ArrayList<String> albumlist = new ArrayList<String>();
	public static ArrayList<String> artistlist = new ArrayList<String>();
	public static ArrayList<String> songtitlelist = new ArrayList<String>();
	public static ArrayList<String> genrelist = new ArrayList<String>();
	public static ArrayList<String> yearlist = new ArrayList<String>();
	public static ArrayList<String> lyricslist = new ArrayList<String>();
	
	public void setFileList(){
		
		if(!directoryName.isDirectory()){
			System.out.println("해당디렉토리는 존재하지 않습니다");
      	    System.exit(1);
        }
		
		MP3FileFilter mp3FileFilter = new MP3FileFilter();
		files = directoryName.listFiles(mp3FileFilter);
		
		for(File f:files){
			
			filenamelist.add(f.getName());
			setTagList(f);
        }
	}
	
	public void setFolderName(File foldername){
		directoryName=foldername;
	}
	
	public void clearFileList(){
		files=null;
		filenamelist.clear();
		albumlist.clear();
		artistlist.clear();
		songtitlelist.clear();
		genrelist.clear();
		yearlist.clear();
		lyricslist.clear();
	}
	
	public void setTagList(File f){
		albumlist.add(filetag.readAlbulTag(f));
		artistlist.add(filetag.readArtistTag(f));
		songtitlelist.add(filetag.readSongTitleTag(f));
		genrelist.add(filetag.readGenreTag(f));
		yearlist.add(filetag.readYearTag(f));
		lyricslist.add(filetag.readLyricsTag(f));
	}
}
