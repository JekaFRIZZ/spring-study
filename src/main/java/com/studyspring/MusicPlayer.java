package com.studyspring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MusicPlayer {
    @Autowired
    private Music music;

    public void playingMusic() {
        System.out.println(music.getSong());
    }
}
