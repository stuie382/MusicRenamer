package com.sc.main;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.metadata.TikaCoreProperties;
import org.apache.tika.metadata.XMPDM;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.parser.mp3.Mp3Parser;
import org.xml.sax.ContentHandler;
import org.xml.sax.helpers.DefaultHandler;

import com.sc.view.Track;

public class FileParser {

    private File inputDirectory;
    private static final Logger log = Logger.getLogger(FileParser.class);

    public static void main(String[] args) {
	log.debug("Input location::" + args[0]);
	FileParser fileParser = new FileParser(args[0]);
	fileParser.startParse();
    }

    public FileParser(final String inputDirectoryLoc) {
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
    }
}
