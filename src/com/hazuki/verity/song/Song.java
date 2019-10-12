package com.hazuki.verity.song;

import org.newdawn.slick.Image;
import org.newdawn.slick.Music;

import java.io.File;

public class Song {

    private String songName;

    private Music songFile;

    private Image songImage;

    private File songFolder;

    public Song(String songName, Music songFile, Image songImage, File songFolder) {
        this.songName = songName;
        this.songFile = songFile;
        this.songImage = songImage;
        this.songFolder = songFolder;
    }

    public String getSongName() {
        return this.songName;
    }

    public Music getSongFile() {
        return this.songFile;
    }

    public Image getSongImage() {
        return this.songImage;
    }

    public File getSongFolder() {
        return this.songFolder;
    }

}