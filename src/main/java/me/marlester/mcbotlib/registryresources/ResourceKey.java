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
