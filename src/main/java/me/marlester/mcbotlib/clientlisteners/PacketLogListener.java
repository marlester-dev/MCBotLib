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
import org.geysermc.mcprotocollib.network.Session;
import org.geysermc.mcprotocollib.network.event.session.PacketSendingEvent;
import org.geysermc.mcprotocollib.network.event.session.SessionAdapter;
import org.geysermc.mcprotocollib.network.packet.Packet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RequiredArgsConstructor
public class PacketLogListener extends SessionAdapter {
  private static final Logger LOG = LoggerFactory.getLogger(PacketLogListener.class);

  private final Bot bot;

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
