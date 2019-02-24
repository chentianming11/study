package com.chen.spring.action.c2.basebean.xmlconfig.soundsystem.collections;

import com.chen.spring.action.c2.basebean.xmlconfig.soundsystem.CompactDisc;

import java.util.List;


public class BlankDisc implements CompactDisc {

  private String title;
  private String artist;
  private List<String> tracks;

  public BlankDisc(String title, String artist, List<String> tracks) {
    this.title = title;
    this.artist = artist;
    this.tracks = tracks;
  }

  public void play() {
    System.out.println("Playing " + title + " by " + artist);
    for (String track : tracks) {
      System.out.println("-Track: " + track);
    }
  }

}
