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

package me.marlester.mcbotlib.clientlisteners;

import lombok.RequiredArgsConstructor;
import me.marlester.mcbotlib.Bot;
import me.marlester.mcbotlib.utils.ResourcePackUtils;
import org.geysermc.mcprotocollib.network.Session;
import org.geysermc.mcprotocollib.network.event.session.SessionAdapter;
import org.geysermc.mcprotocollib.network.packet.Packet;
import org.geysermc.mcprotocollib.protocol.data.game.ResourcePackStatus;
import org.geysermc.mcprotocollib.protocol.packet.common.clientbound.ClientboundResourcePackPushPacket;
import org.geysermc.mcprotocollib.protocol.packet.common.serverbound.ServerboundResourcePackPacket;

/**
 * This is a so-called resource pack client listener. It is responsible for properly
 * accepting/declining resource pack.
 */
@RequiredArgsConstructor
public class ResourcePackListener extends SessionAdapter {

  private final Bot bot;

  @Override
  public void packetReceived(Session session, Packet packet) {
    if (!(packet instanceof ClientboundResourcePackPushPacket rpPacket)) {
      return;
    }
    if (!ResourcePackUtils.isValidResourcePackUrl(rpPacket.getUrl())) {
      session.send(new ServerboundResourcePackPacket(rpPacket.getId(),
          ResourcePackStatus.INVALID_URL));
      return;
    }
    boolean acceptRp = bot.getSettings().isAcceptResourcePack();
    if (acceptRp) {
      session.send(new ServerboundResourcePackPacket(rpPacket.getId(),
          ResourcePackStatus.ACCEPTED));
      session.send(new ServerboundResourcePackPacket(rpPacket.getId(),
          ResourcePackStatus.DOWNLOADED));
      session.send(new ServerboundResourcePackPacket(rpPacket.getId(),
          ResourcePackStatus.SUCCESSFULLY_LOADED));
    } else {
      session.send(new ServerboundResourcePackPacket(rpPacket.getId(),
          ResourcePackStatus.DECLINED));
    }
  }
}
