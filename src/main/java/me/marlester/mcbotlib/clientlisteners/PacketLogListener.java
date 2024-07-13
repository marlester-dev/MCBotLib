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
