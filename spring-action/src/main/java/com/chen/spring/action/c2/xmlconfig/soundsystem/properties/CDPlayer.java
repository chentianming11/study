package com.chen.spring.action.c2.xmlconfig.soundsystem.properties;

import com.chen.spring.action.c2.xmlconfig.soundsystem.CompactDisc;
import com.chen.spring.action.c2.xmlconfig.soundsystem.MediaPlayer;
import org.springframework.beans.factory.annotation.Autowired;


public class CDPlayer implements MediaPlayer {
  private CompactDisc compactDisc;

  @Autowired
  public void setCompactDisc(CompactDisc compactDisc) {
    this.compactDisc = compactDisc;
  }

  @Override
  public void play() {
    compactDisc.play();
  }

}
