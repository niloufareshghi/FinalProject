import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;



public class SongInfo {
	File song;
	FileInputStream file;
	private String[] info;
	private BufferedImage img;
	static String filename;

	public SongInfo(String filename) throws IOException, UnsupportedTagException, InvalidDataException {
		info = new String[4];
		filename= this.filename;
		try {
			song = new File(filename);
			file= new FileInputStream(song);
			int size = (int) song.length();
			file.skip(size -128);
			byte[] last128 = new byte[128];
			file.read(last128);
			String id3 = new String(last128);
			String tag = id3.substring(0, 3);
			Mp3File mp3Song = new Mp3File(filename);
			
			if (mp3Song.hasId3v2Tag()){
			     ID3v2 id3v2tag = mp3Song.getId3v2Tag();
			     byte[] imageData = id3v2tag.getAlbumImage();
			     //converting the bytes to an image
			     img = ImageIO.read(new ByteArrayInputStream(imageData));
			}
			
			info[0]=  id3.substring(3, 32);
			info[1] = id3.substring(33, 62);
			info[2]= id3.substring(63, 91);
			info[3]=  id3.substring(93, 97);
			
			/*
			if (tag.equals("TAG")) {
				System.out.println("Title: " + id3.substring(3, 32));
				System.out.println("Artist: " + id3.substring(33, 62));
				System.out.println("Album: " + id3.substring(63, 91));
				System.out.println("Year: " + id3.substring(93, 97));
			} else
				System.out.println(filename + " does not contain"
						+ " ID3 info."); 
						*/
			file.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}



	}
	public String[] getInfo() {
		return info;
	}
	public BufferedImage getArtwork() {
		return img;
	}
}
