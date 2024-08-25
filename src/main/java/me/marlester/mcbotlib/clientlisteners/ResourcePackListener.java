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

package me.marlester.mcbotlib.clientlisteners;

import lombok.RequiredArgsConstructor;
import me.marlester.mcbotlib.BotSession;
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

  private final BotSession bot;

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
