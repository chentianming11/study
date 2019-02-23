package com.chen.spring.action.c2.autoconfig.soundsystem;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
//@ComponentScan("soundsystem")
//@ComponentScan({"soundsystem", "video"})
@ComponentScan(basePackageClasses = {CDPlayer.class, SgtPeppers.class})
public class CDPlayerConfig { 
}
