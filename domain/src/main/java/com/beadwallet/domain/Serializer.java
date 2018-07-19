/**
 * Copyright (C) 2015 Fernando Cejas Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.beadwallet.domain;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Json Serializer/Deserializer.
 */
@Singleton
public class Serializer {

  private final Gson gson = new Gson();

  @Inject
  Serializer() {
  }

  /**
   * Serialize an object to Json.
   *
   * @param object to serialize.
   */
  public String serialize(Object object) {
    return gson.toJson(object);
  }

  /**
   * Deserialize a json representation of an object.
   *
   * @param string A json string to deserialize.
   */
  public <T> T deserialize(String string, Class<T> clazz) {
    return gson.fromJson(string, clazz);
  }


  public <T> List<T> deserializeList(String string, Class<? extends T[]> clazz) {
    ArrayList<T> list = new ArrayList<>();
    T[] ts = gson.fromJson(string, clazz);
    list.addAll(Arrays.asList(ts));
    return list;
  }
}
