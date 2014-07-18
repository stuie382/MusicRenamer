package main.java.view;

import java.io.File;

public class Track {
    private String trackName;
    private int trackNumber;
    private String albumName;
    private String artist;
    private File content;

    /**
     * Selection of meta information about the track, and the track content
     * itself.
     * 
     * @param trackName
     * @param albumName
     * @param artist
     * @param content
     */
    public Track(final String trackName, final String trackNumber,
	    final String albumName, final String artist, final File content) {
	super();
	this.trackName = trackName;
	this.trackNumber = Integer.parseInt(trackNumber);
	this.albumName = albumName;
	this.artist = artist;
	this.content = content;
    }

    public final String getTrackName() {
	return trackName;
    }
    
    public final int getTrackNumber() {
	return trackNumber;
    }

    public final String getAlbumName() {
	return albumName;
    }

    public final String getArtist() {
	return artist;
    }

    public final File getContent() {
	return content;
    }

    @Override
    public String toString() {
	StringBuilder builder = new StringBuilder();
	builder.append("Track [trackName=").append(trackName)
		.append(", trackNumber=").append(trackNumber)
		.append(", albumName=").append(albumName).append(", artist=")
		.append(artist).append(", content=").append(content)
		.append("]");
	return builder.toString();
    }

}
