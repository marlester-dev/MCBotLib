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
import org.geysermc.mcprotocollib.network.Session;
import org.geysermc.mcprotocollib.network.event.session.PacketSendingEvent;
import org.geysermc.mcprotocollib.network.event.session.SessionAdapter;
import org.geysermc.mcprotocollib.network.packet.Packet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RequiredArgsConstructor
public class PacketLogListener extends SessionAdapter {
  private static final Logger LOG = LoggerFactory.getLogger(PacketLogListener.class);

  private final BotSession bot;

  @Override
  public void packetReceived(Session session, Packet packet) {
    if (!bot.getSettings().isLogPlayerPackets()) {
      return;
    }
    LOG.info("[Bot " + bot.getNick() + "] Received packet: "
        + packet.getClass().getSimpleName());
  }

  @Override
  public void packetSending(PacketSendingEvent event) {
    if (!bot.getSettings().isLogPlayerPackets()) {
      return;
    }
    LOG.info("[Bot " + bot.getNick() + "] Sending packet: "
        + event.getPacket().getClass().getSimpleName());
  }

  @Override
  public void packetSent(Session session, Packet packet) {
    if (!bot.getSettings().isLogPlayerPackets()) {
      return;
    }
    LOG.info("[Bot " + bot.getNick() + "] Sent packet: "
        + packet.getClass().getSimpleName());
  }
}
