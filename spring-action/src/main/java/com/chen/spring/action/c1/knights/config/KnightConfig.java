package com.chen.spring.action.c1.knights.config;

import com.chen.spring.action.c1.knights.BraveKnight;
import com.chen.spring.action.c1.knights.Knight;
import com.chen.spring.action.c1.knights.Quest;
import com.chen.spring.action.c1.knights.SlayDragonQuest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KnightConfig {

  @Bean
  public Knight knight() {
    return new BraveKnight(quest());
  }
  
  @Bean
  public Quest quest() {
    return new SlayDragonQuest(System.out);
  }

}
