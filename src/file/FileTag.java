package file;

import java.io.File;
import java.util.ArrayList;

import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.audio.mp3.MP3File;
import org.jaudiotagger.tag.images.Artwork;
import org.jaudiotagger.tag.id3.valuepair.ImageFormats;
import org.jaudiotagger.tag.images.ArtworkFactory;
import org.jaudiotagger.tag.images.Images;
import org.jaudiotagger.tag.asf.AsfTagCoverField;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.TagException;
import org.jaudiotagger.tag.TagField;
import org.jaudiotagger.tag.id3.AbstractID3v2Frame;
import org.jaudiotagger.tag.id3.AbstractID3v2Tag;
import org.jaudiotagger.tag.id3.ID3v1Tag;
import org.jaudiotagger.tag.id3.ID3v23Tag;
import org.jaudiotagger.tag.id3.ID3v24Frames;
import org.jaudiotagger.tag.id3.ID3v24Tag;
import org.jaudiotagger.tag.id3.framebody.FrameBodyAPIC;
import org.jaudiotagger.tag.id3.framebody.FrameBodyTPE1;
import org.jaudiotagger.tag.id3.valuepair.TextEncoding;
import org.jaudiotagger.tag.reference.PictureTypes;


public class FileTag {
	
	public String readAlbulTag(File f){
		String temp = " ";
		try{
			
			MP3File mp3 = (MP3File) AudioFileIO.read(f);
			
			AbstractID3v2Tag tag =  mp3.getID3v2TagAsv24();
			
			temp = tag.getFirst(ID3v24Frames.FRAME_ID_ALBUM);
        	} catch (Exception e) {
        	}
		 return temp;
	}
	
	public String readArtistTag(File f){
		String temp = " ";
		try{
			
			MP3File mp3 = (MP3File) AudioFileIO.read(f);
			AbstractID3v2Tag tag =  mp3.getID3v2TagAsv24();
			
			temp = tag.getFirst(ID3v24Frames.FRAME_ID_ARTIST);
             
        	} catch (Exception e) {
        	}
		 return temp;
	}
	
	public String readSongTitleTag(File f){
		String temp = " ";
		try{
			
			MP3File mp3 = (MP3File) AudioFileIO.read(f);
			AbstractID3v2Tag tag =  mp3.getID3v2TagAsv24();
			
			temp = tag.getFirst(ID3v24Frames.FRAME_ID_TITLE);
   
        	} catch (Exception e) {
        	}
		 return temp;
	}
	
	public String readGenreTag(File f){
		String temp = " ";
		try{
			
			MP3File mp3 = (MP3File) AudioFileIO.read(f);
			AbstractID3v2Tag tag =  mp3.getID3v2TagAsv24();
			
			temp = tag.getFirst(ID3v24Frames.FRAME_ID_GENRE);

        	} catch (Exception e) {
        	}
		 return temp;
	}
	
	public String readYearTag(File f){
		String temp = " ";
		try{
			
			MP3File mp3 = (MP3File) AudioFileIO.read(f);
			AbstractID3v2Tag tag =  mp3.getID3v2TagAsv24();
			
			temp = tag.getFirst(ID3v24Frames.FRAME_ID_YEAR);

        	} catch (Exception e) {
        	}
		 return temp;
	}
	
	public String readLyricsTag(File f){
		String temp = " ";
		try{
			
			MP3File mp3 = (MP3File) AudioFileIO.read(f);
			AbstractID3v2Tag tag =  mp3.getID3v2TagAsv24();
			
			temp = tag.getFirst(ID3v24Frames.FRAME_ID_UNSYNC_LYRICS);
        	} catch (Exception e) {
        	}
		 return temp;
	}
	
	public static byte[] readImageTag(File f){
		Artwork artwork=null;
		byte[] firstimage=null;
		try{
			
			MP3File mp3 = (MP3File) AudioFileIO.read(f);
			AbstractID3v2Tag tag =  mp3.getID3v2TagAsv24();
			
			artwork = tag.getFirstArtwork();
			firstimage = artwork.getBinaryData();
        	} catch (Exception e) {
        	}
		 return firstimage;
	}
}
