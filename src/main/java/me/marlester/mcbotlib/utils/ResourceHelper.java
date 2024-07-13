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
