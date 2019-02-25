package com.chen.spring.action.c5.web.spittr.data;


import com.chen.spring.action.c5.web.spittr.Spitter;

public interface SpitterRepository {

  Spitter save(Spitter spitter);
  
  Spitter findByUsername(String username);

}
