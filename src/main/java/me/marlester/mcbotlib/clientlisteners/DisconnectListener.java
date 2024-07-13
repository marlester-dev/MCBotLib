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

package me.marlester.mcbotlib.clientlisteners;

import lombok.RequiredArgsConstructor;
import me.marlester.mcbotlib.Bot;
import org.geysermc.mcprotocollib.network.event.session.DisconnectedEvent;
import org.geysermc.mcprotocollib.network.event.session.SessionAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RequiredArgsConstructor
public class DisconnectListener extends SessionAdapter {
  private static final Logger LOG = LoggerFactory.getLogger(DisconnectListener.class);

  private final Bot bot;

  @Override
  public void disconnected(DisconnectedEvent event) {
    var cause = event.getCause();
    if (cause != null && bot.getSettings().isLogDisconnectErrors()) {
      LOG.error("Bot " + bot.getNick() + " disconnected with an error!", cause);
    }
  }
}
