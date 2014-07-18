package main.java.utils;

import java.io.File;

import org.apache.log4j.Logger;

public class FpUtils {

    private static final Logger log = Logger.getLogger(FpUtils.class);
    private static final String CORRECTED_FILES = "correctedFiles";

    private FpUtils() {
	// Empty private constructor
    }

    /**
     * Method ensures the user specified directory ends with a file separator if
     * it does not already.
     * 
     * @param inputDirectoryLoc
     * @return
     */
    public static String endsWithPathSep(String inputDirectoryLoc) {

	String fixedInputDir = inputDirectoryLoc;
	if (!inputDirectoryLoc.endsWith(File.separator)) {
	    fixedInputDir = inputDirectoryLoc + File.separator;
	}
	return fixedInputDir;
    }

    /**
     * Will create a path for the files to be output to within the original
     * input directory provided on the command line. Nominally will be something
     * like 'inputDir/correctedFiles/album/'.
     * 
     * @param albumName
     * @param inputDirLoc
     * @return
     */
    public static String createOutputDirString(String albumName,
	    String inputDirLoc) {
	StringBuilder output = new StringBuilder(inputDirLoc);
	output.append(CORRECTED_FILES);
	output.append(File.separator);
	output.append(albumName);
	output.append(File.separator);
	log.debug("Output location for new files::" + output.toString());
	return output.toString();
    }

}
