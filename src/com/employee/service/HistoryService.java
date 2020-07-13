package com.employee.service;

import java.util.List;

import com.employee.po.History;

public interface HistoryService {
   public List<History> findByKey(String key ,int size,int page);
   
   public int pageCount( int size);
   
   public List<History> list(String key);
   
}
