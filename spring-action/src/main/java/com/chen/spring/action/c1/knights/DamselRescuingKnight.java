package com.chen.spring.action.c1.knights;

public class DamselRescuingKnight implements Knight {

  private RescueDamselQuest quest;

  public DamselRescuingKnight() {
    this.quest = new RescueDamselQuest();
  }

  @Override
  public void embarkOnQuest() {
    quest.embark();
  }

}
