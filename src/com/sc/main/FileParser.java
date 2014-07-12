package com.sc.main;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.apache.log4j.Logger;
import org.apache.tika.parser.Parser;
import org.apache.tika.parser.mp3.Mp3Parser;

public class FileParser {
    private File inputDirectory;
    private static final Logger log = Logger.getLogger(FileParser.class);

    public static void main(String[] args) {
	log.debug("Input location::" + args[0]);
	FileParser fileParser = new FileParser(args[0]);
	fileParser.parse();
    }

    public FileParser(final String inputDirectoryLoc) {
	inputDirectory = new File(inputDirectoryLoc);

    }

    public final void parse() {
	File[] listOfFiles = inputDirectory.listFiles();
	for (File inputFile : listOfFiles) {
	    try (InputStream input = new FileInputStream(inputFile)) {
		log.debug("Looking at file::" + inputFile.getAbsolutePath());

	    } catch (Exception ex) {
		log.error("Something went wrong::" + ex.getMessage());
	    }
	}
    }
}
