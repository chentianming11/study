package com.chen.spring.action.c2.basebean.autoconfig.soundsystem;
import org.springframework.stereotype.Component;


@Component
public class SgtPeppers implements CompactDisc {

  private String title = "Sgt. Pepper's Lonely Hearts Club Band";  
  private String artist = "The Beatles";
  
  @Override
  public void play() {
    System.out.println("Playing " + title + " by " + artist);
  }
  
}
