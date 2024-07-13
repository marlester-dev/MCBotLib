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

package me.marlester.mcbotlib;

import java.util.List;
import java.util.UUID;
import lombok.Builder;
import lombok.Getter;
import org.geysermc.mcprotocollib.network.ProxyInfo;
import org.geysermc.mcprotocollib.protocol.data.game.entity.player.HandPreference;
import org.geysermc.mcprotocollib.protocol.data.game.setting.ChatVisibility;
import org.geysermc.mcprotocollib.protocol.data.game.setting.SkinPart;
import org.jetbrains.annotations.Nullable;

/**
 * Class representing the settings for a bot.
 */
@Getter
@Builder
public class BotSettings {

  /**
   * Unique identifier for the bot. Defaults to a randomly generated UUID.
   */
  @Builder.Default
  private final UUID id = UUID.randomUUID();

  /**
   * Access token for the bot. Can be null.
   */
  @Nullable
  private final String accessToken;

  /**
   * Proxy information for the bot. Can be null.
   */
  @Nullable
  private final ProxyInfo proxy;

  /**
   * Whether the bot should accept resource packs. Defaults to true.
   */
  @Builder.Default
  private final boolean acceptResourcePack = true;

  /**
   * The client brand for the bot. Defaults to "vanilla".
   */
  @Builder.Default
  private final String clientBrand = "vanilla";

  /**
   * The locale setting for the bot. Defaults to "en_gb".
   */
  @Builder.Default
  private final String locale = "en_gb";

  /**
   * The render distance setting for the bot. Defaults to 1.
   */
  @Builder.Default
  private final int renderDistance = 1;

  /**
   * The chat visibility setting for the bot. Defaults to HIDDEN.
   */
  @Builder.Default
  private final ChatVisibility chatVisibility = ChatVisibility.HIDDEN;

  /**
   * Whether the bot should use chat colors. Defaults to true.
   */
  @Builder.Default
  private final boolean useChatColors = true;

  /**
   * List of visible skin parts for the bot. Defaults to an empty list.
   */
  @Builder.Default
  private final List<SkinPart> visibleSkinParts = List.of();

  /**
   * Hand preference for the bot. Defaults to RIGHT_HAND.
   */
  @Builder.Default
  private final HandPreference handPreference = HandPreference.RIGHT_HAND;

  /**
   * Whether text filtering is enabled for the bot. Defaults to true.
   */
  @Builder.Default
  private final boolean textFiltering = true;

  /**
   * Whether the bot allows listing. Defaults to true.
   */
  @Builder.Default
  private final boolean allowsListing = true;

  /**
   * Whether disconnect errors should be logged for the bot. Defaults to true.
   */
  @Builder.Default
  private final boolean logDisconnectErrors = true;

  /**
   * Whether player packets should be logged for the bot. Defaults to false.
   */
  @Builder.Default
  private final boolean logPlayerPackets = false;
}
