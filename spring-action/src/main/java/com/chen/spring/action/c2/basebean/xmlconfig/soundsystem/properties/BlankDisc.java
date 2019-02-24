package com.chen.spring.action.c2.basebean.xmlconfig.soundsystem.properties;

import com.chen.spring.action.c2.basebean.xmlconfig.soundsystem.CompactDisc;

import java.util.List;


public class BlankDisc implements CompactDisc {

  private String title;
  private String artist;
  private List<String> tracks;

  public void setTitle(String title) {
    this.title = title;
  }

  public void setArtist(String artist) {
    this.artist = artist;
  }

  public void setTracks(List<String> tracks) {
    this.tracks = tracks;
  }

  public void play() {
    System.out.println("Playing " + title + " by " + artist);
    for (String track : tracks) {
      System.out.println("-Track: " + track);
    }
  }

}
