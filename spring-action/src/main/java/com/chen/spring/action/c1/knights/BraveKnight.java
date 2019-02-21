package com.chen.spring.action.c1.knights;
  
public class BraveKnight implements Knight {

  private Quest quest;

  public BraveKnight(Quest quest) {
    this.quest = quest;
  }

  @Override
  public void embarkOnQuest() {
    quest.embark();
  }

}
