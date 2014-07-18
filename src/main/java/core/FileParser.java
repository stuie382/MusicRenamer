package main.java.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import main.java.utils.FpUtils;
import main.java.view.Track;

import org.apache.log4j.Logger;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.metadata.TikaCoreProperties;
import org.apache.tika.metadata.XMPDM;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.parser.mp3.Mp3Parser;
import org.xml.sax.ContentHandler;
import org.xml.sax.helpers.DefaultHandler;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;
import com.google.common.io.Files;

public class FileParser {

    private final File inputDirectory;
    private final String inputDirectoryLoc;
    private static final Logger log = Logger.getLogger(FileParser.class);

    public static void main(String[] args) {
	log.debug("Input location::" + args[0]);
	FileParser fileParser = new FileParser(args[0]);
	fileParser.startParse();
    }

    public FileParser(final String inputDirectoryLoc) {
	this.inputDirectoryLoc = FpUtils.endsWithPathSep(inputDirectoryLoc);
	inputDirectory = new File(inputDirectoryLoc);
    }

    public final void startParse() {
	File[] listOfFiles = inputDirectory.listFiles();
	List<Track> inputWithMeta = new ArrayList<>();

	for (File inputFile : listOfFiles) {
	    try (InputStream input = new FileInputStream(inputFile)) {
		log.debug("Looking at file::" + inputFile.getAbsolutePath());
		ContentHandler handler = new DefaultHandler();
		Metadata metadata = new Metadata();
		ParseContext parseContext = new ParseContext();
		Parser parser = new Mp3Parser();
		parser.parse(input, handler, metadata, parseContext);

		Track newTrack = new Track(
			metadata.get(TikaCoreProperties.TITLE),
			metadata.get(XMPDM.TRACK_NUMBER),
			metadata.get(XMPDM.ALBUM), metadata.get(XMPDM.ARTIST),
			inputFile);
		inputWithMeta.add(newTrack);
		log.debug(newTrack.toString());
	    } catch (Exception ex) {
		log.error("Something went wrong::" + ex.getMessage());
	    }
	}
	ListMultimap<String, Track> albums = groupByAlbum(inputWithMeta);
	reorganiseFiles(albums);

    }

    private void reorganiseFiles(ListMultimap<String, Track> albums) {
	for (String albumName : albums.keySet()) {
	    List<Track> tracks = albums.get(albumName);
	    String outputDir = FpUtils.createOutputDirString(albumName,
		    inputDirectoryLoc);
	    for (Track track : tracks) {
		File updatedTrack = new File(outputDir + track.getTrackName()
			+ ".mp3");
		try {
		    Files.createParentDirs(updatedTrack);
		    Files.move(track.getContent(), updatedTrack);
		    log.debug("Moved " + track.getTrackName() + " to "
			    + updatedTrack.getAbsolutePath());
		} catch (IOException e) {
		    log.error("Error occured::", e);
		}
	    }
	}
    }

    private ListMultimap<String, Track> groupByAlbum(List<Track> inputWithMeta) {
	ListMultimap<String, Track> albums = ArrayListMultimap.create();
	for (Track track : inputWithMeta) {
	    albums.put(track.getAlbumName(), track);
	}
	return albums;
    }
}
