MusicRenamer
============
Program will take a directory as input. This directory is expected to contain a flat structure of mp3 files 
that have incorrect file names (obfuscated, prepended with track numbers etc).
Program will look to create a nested structure of files that:
  # - have been renamed to the 'proper' track name
  # - will be grouped into a directory based on the album name
  
TODO
====
Handle compilation albums - for albums that have multiple artists across many tracks, these should be sorted
                            by the compilation rather than by the original artist.
Group albums by artist    - where an album contains a single artist, we will then create a parent directory
                            named after the artist. This will keep the albums together.
Handle duplicate tracks   - where we have multiple copies of the same track name, track number, album name,
                            and artist, move duplicates out to a separate top level 'duplicates' directory.
                            Don't delete them as we will want to manually double check them just incase the
                            metadata was not correct on the file.
