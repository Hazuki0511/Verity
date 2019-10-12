package com.hazuki.verity.song;

import org.newdawn.slick.Image;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class SongManager {

    private static ArrayList<Song> songs = new ArrayList<>();

    public static void loadSongs() throws SlickException {
        var songFoldersDir = new File("C:/Users/Hazuki/Documents/Verity/Songs");
        var songFolders = songFoldersDir.listFiles();

        if (songFolders == null || songFolders.length == 0) {
            return;
        }
        for (File songFolder : songFolders) {
            if (songFolder.isDirectory()) {
                var songFilesDir = new File(songFolder.getPath());
                var songFiles = songFilesDir.listFiles();

                if (songFiles == null || songFiles.length == 0) {
                    return;
                }
                for (File songFile : songFiles) {
                    var songFileExtension = songFile.getName().split("\\.", 0)[1];

                    if (songFileExtension.equals("vss")) {
                        String songName = null;
                        Music songMusic = null;
                        Image songImage = null;

                        try {
                            var fileReader = new FileReader(songFile); // songFile == Verity SongSheet
                            var bufferedReader = new BufferedReader(fileReader);
                            String line;

                            while ((line = bufferedReader.readLine()) != null) {
                                var text = line.split(" ", 2); // 一回分割

                                if (text[0].equalsIgnoreCase("[SongName]")) {
                                    songName = text[1];
                                }
                                if (text[0].equalsIgnoreCase("[SongFile]")) {
                                    var songMusicPath = songFilesDir.getPath() + "/" + text[1];

                                    songMusic = new Music(songMusicPath);
                                }
                                if (text[0].equalsIgnoreCase("[SongImage]")) {
                                    var songImagePath = songFilesDir.getPath() + "/" + text[1];

                                    songImage = new Image(songImagePath);
                                }
                            }
                            SongManager.songs.add(new Song(songName, songMusic, songImage, songFilesDir));
                            Log.info("Verity - Added song" + " : " + songName);
                            bufferedReader.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        var songCount = SongManager.songs.size();

        Log.info("Verity - Loaded" + " " + songCount + " " + "songs");
    }

    public static ArrayList<Song> getSongs() {
        return SongManager.songs;
    }

}