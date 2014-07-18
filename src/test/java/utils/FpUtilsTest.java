package test.java.utils;

import static org.junit.Assert.assertEquals;

import java.io.File;

import main.java.utils.FpUtils;

import org.junit.Test;

public class FpUtilsTest {

    @Test
    public void testEndsWithPathSep_NoSep() throws Exception {
	String inputPath = "F:" + File.separator + "myDir";
	String output = FpUtils.endsWithPathSep(inputPath);
	String expectedOutput = inputPath + File.separator;
	assertEquals(expectedOutput, output);
    }

    @Test
    public void testEndsWithPathSep_AlreadyHasSep() throws Exception {
	String inputPath = "F:" + File.separator + "myDir" + File.separator;
	String output = FpUtils.endsWithPathSep(inputPath);
	assertEquals(inputPath, output);
    }

    @Test
    public void testCreateOutputDirString() throws Exception {
	String albumName = "myAlbum";
	String inputLoc = "F:" + File.separator + "myFiles" + File.separator;
	String expected = inputLoc + "correctedFiles" + File.separator
		+ albumName + File.separator;
	String outputDir = FpUtils.createOutputDirString(albumName, inputLoc);
	assertEquals(expected, outputDir);
    }

}
