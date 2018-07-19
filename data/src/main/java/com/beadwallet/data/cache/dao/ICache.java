package com.beadwallet.data.cache.dao;


public interface ICache<T> {
  void put(T put);
  T get();
  String cacheName();
  void clearCache();
}
