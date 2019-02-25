package com.chen.spring.action.c5.web.spittr.data;

import com.chen.spring.action.c5.web.spittr.Spittle;

import java.util.List;


public interface SpittleRepository {

  List<Spittle> findRecentSpittles();

  List<Spittle> findSpittles(long max, int count);
  
  Spittle findOne(long id);

  void save(Spittle spittle);

}
