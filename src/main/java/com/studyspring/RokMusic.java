package com.studyspring;

import org.springframework.stereotype.Component;

@Component
public class RokMusic implements Music{
    @Override
    public String getSong() {
        return "rok";
    }
}
