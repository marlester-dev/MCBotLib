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
import org.geysermc.mcprotocollib.network.Session;
import org.geysermc.mcprotocollib.network.event.session.SessionAdapter;
import org.geysermc.mcprotocollib.network.packet.Packet;
import org.geysermc.mcprotocollib.protocol.packet.common.serverbound.ServerboundClientInformationPacket;
import org.geysermc.mcprotocollib.protocol.packet.login.clientbound.ClientboundGameProfilePacket;

@RequiredArgsConstructor
public class ClientInfoListener extends SessionAdapter {

  private final Bot bot;

  @Override
  public void packetReceived(Session session, Packet packet) {
    if (!(packet instanceof ClientboundGameProfilePacket)) {
      return;
    }
    var settings = bot.getSettings();
    session.send(new ServerboundClientInformationPacket(
        settings.getLocale(),
        settings.getRenderDistance(),
        settings.getChatVisibility(),
        settings.isUseChatColors(),
        settings.getVisibleSkinParts(),
        settings.getHandPreference(),
        settings.isTextFiltering(),
        settings.isAllowsListing()
    ));
  }
}
