/*
 * Copyright (C) 2024 marlester-dev
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package me.marlester.mcbotlib.utils;

import java.net.MalformedURLException;
import java.net.URI;
import lombok.experimental.UtilityClass;

/**
 * Utils related to resource packs.
 */
@UtilityClass
public class ResourcePackUtils {

  /**
   * Checks if the given resource pack url is valid.
   *
   * @param url resource pack url.
   * @return true if valid, false if not.
   */
  public boolean isValidResourcePackUrl(String url) {
    if (url == null || url.isEmpty()) {
      return false;
    }
    try {
      var protocol = URI.create(url).toURL().getProtocol();
      return "http".equals(protocol) || "https".equals(protocol);
    } catch (MalformedURLException | IllegalArgumentException e) {
      return false;
    }
  }
}
