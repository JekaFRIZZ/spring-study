package com.studyspring;

public class MusicPlayer {
    private Music music;

    public MusicPlayer(Music music) {
        this.music = music;
    }

    public void playingMusic() {
        System.out.println(music.getSong());
    }
}
