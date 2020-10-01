package com.viva.ppmtool.config;

import org.ehcache.event.CacheEvent;
import org.ehcache.event.CacheEventListener;

public class CacheLogger implements CacheEventListener<Object, Object> {
	  @Override
	  public void onEvent(CacheEvent<?, ?> cacheEvent) {
	    System.out.println("Key: "+ cacheEvent.getKey() + "| EventType: "+ cacheEvent.getType() +" | Old value: "+ cacheEvent.getOldValue()+" | New value: "+ cacheEvent.getNewValue());
	             
	  }
	}