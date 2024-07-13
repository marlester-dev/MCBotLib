/*
 * Copyright 2023 Marlester
 *
 * Licensed under the EUPL, Version 1.2 (the "License");
 *
 * You may not use this work except in compliance with the License.
 * You may obtain a copy of the License at:
 *
 * https://joinup.ec.europa.eu/software/page/eupl
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an "AS IS" basis,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions
 * and limitations under the License.
 */

package me.marlester.mcbotlib.utils;

import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class ResourceHelper {
  private ResourceHelper() {
  }

  public static String getResourceAsString(String path) {
    return new String(getResourceAsBytes(path), StandardCharsets.UTF_8);
  }

  public static byte[] getResourceAsBytes(String path) {
    try {
      return Objects.requireNonNull(ResourceHelper.class.getClassLoader().getResourceAsStream(path),
          path).readAllBytes();
    } catch (Exception e) {
      throw new RuntimeException("Failed to get file", e);
    }
  }
}
