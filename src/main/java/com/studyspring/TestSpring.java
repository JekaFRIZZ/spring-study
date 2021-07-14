package com.studyspring;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestSpring {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("applicationContext.xml");
        MusicPlayer firstMusicPlayer = context.getBean("musicPlayer", MusicPlayer.class);
        MusicPlayer secondMusicPlayer = context.getBean("musicPlayer", MusicPlayer.class);

        firstMusicPlayer.playingMusic();
        System.out.println(firstMusicPlayer == secondMusicPlayer);

        firstMusicPlayer.setName("classic player");

        System.out.println(firstMusicPlayer.getName());
        System.out.println(secondMusicPlayer.getName());

        context.close();
    }
}
