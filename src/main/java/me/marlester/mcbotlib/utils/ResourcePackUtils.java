/*
 *
 * Copyright (C) 2024 Marlester
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
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
