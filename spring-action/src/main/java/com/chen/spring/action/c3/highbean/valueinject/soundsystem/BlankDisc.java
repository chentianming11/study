package com.chen.spring.action.c3.highbean.valueinject.soundsystem;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class BlankDisc {

  private final String title;
  private final String artist;

  public BlankDisc(
          @Value("#{systemProperties['disc.title']}") String title,
          @Value("#{systemProperties['disc.artist']}") String artist) {
    this.title = title;
    this.artist = artist;
  }
  
  public String getTitle() {
    return title;
  }
  
  public String getArtist() {
    return artist;
  }
  
}
