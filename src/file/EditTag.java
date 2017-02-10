package file;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
import org.jaudiotagger.tag.images.StandardArtwork;
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

public class EditTag {
	
	public static void editAlbumTag(File file, String albumname){
		try{
			
			MP3File mp3 = (MP3File) AudioFileIO.read(file);
			AbstractID3v2Tag tag = mp3.getID3v2TagAsv24();
			tag.setField(FieldKey.ALBUM, albumname);
			mp3.setID3v2Tag(tag);
			mp3.save();
			
        	} catch (Exception e) {
        	}
	}
	
	public static void editArtistTag(File file, String artistname){
		try{
			
			MP3File mp3 = (MP3File) AudioFileIO.read(file);
			AbstractID3v2Tag tag = mp3.getID3v2TagAsv24();
			
			tag.setField(FieldKey.ARTIST,artistname);
			mp3.setID3v2Tag(tag);
			mp3.save();
			
        	} catch (Exception e) {
        	}
	}
	
	public static void editSongTitleTag(File file, String songtitlename){
		try{
			
			MP3File mp3 = (MP3File) AudioFileIO.read(file);
			AbstractID3v2Tag tag = mp3.getID3v2TagAsv24();
			
			tag.setField(FieldKey.TITLE,songtitlename);
			mp3.setID3v2Tag(tag);
			mp3.save();
			
        	} catch (Exception e) {
        	}
	}
	
	public static void editGenreTag(File file, String genrename){
		try{
			
			MP3File mp3 = (MP3File) AudioFileIO.read(file);
			AbstractID3v2Tag tag = mp3.getID3v2TagAsv24();
			
			tag.setField(FieldKey.GENRE,genrename);
			mp3.setID3v2Tag(tag);
			mp3.save();
			
        	} catch (Exception e) {
        	}
	}
	
	public static void editYearTag(File file, String yearname){
		try{
			
			MP3File mp3 = (MP3File) AudioFileIO.read(file);
			AbstractID3v2Tag tag = mp3.getID3v2TagAsv24();
			
			tag.setField(FieldKey.YEAR,yearname);
			mp3.setID3v2Tag(tag);
			mp3.save();
			
        	} catch (Exception e) {
        	}
	}
	
	public static void editLyricsTag(File file, String lyrics){
		try{
			
			MP3File mp3 = (MP3File) AudioFileIO.read(file);
			AbstractID3v2Tag tag = mp3.getID3v2TagAsv24();
			
			tag.setField(FieldKey.LYRICS,lyrics);
			mp3.setID3v2Tag(tag);
			mp3.save();
			
        	} catch (Exception e) {
        	}
	}
	
	public static void editImageTag(File file, File loadedimage) throws IOException{
		
		Artwork artwork = new StandardArtwork();
		
		try{
			
			MP3File mp3 = (MP3File) AudioFileIO.read(file);
			AbstractID3v2Tag tag = mp3.getID3v2TagAsv24();
			
			artwork = tag.getFirstArtwork();
			
			final List multiFrames = new ArrayList();
            multiFrames.add(mp3.getID3v2Tag().createField(ArtworkFactory.createArtworkFromFile(loadedimage)));
            mp3.getID3v2Tag().setFrame("APIC", multiFrames);
            mp3.commit();
        	} catch (Exception e) {
        		System.out.println();
        	}
	}
}
