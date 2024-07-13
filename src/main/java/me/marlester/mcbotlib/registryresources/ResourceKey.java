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

package me.marlester.mcbotlib.registryresources;

import net.kyori.adventure.key.Key;
import net.kyori.adventure.key.KeyPattern;

public record ResourceKey<T extends RegistryValue<T>>(Key registryKey, Key key) {
  public static final Key ROOT_REGISTRY_KEY = Key.key("minecraft:root");

  public static <T extends RegistryValue<T>> ResourceKey<T> key(@KeyPattern String key) {
    return key(Key.key(key));
  }

  public static <T extends RegistryValue<T>> ResourceKey<T> key(Key key) {
    return new ResourceKey<>(ROOT_REGISTRY_KEY, key);
  }
}
