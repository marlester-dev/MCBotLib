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

import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import lombok.RequiredArgsConstructor;
import me.marlester.mcbotlib.Bot;
import me.marlester.mcbotlib.protocol.ProtocolConstants;
import org.geysermc.mcprotocollib.network.Session;
import org.geysermc.mcprotocollib.network.event.session.SessionAdapter;
import org.geysermc.mcprotocollib.network.packet.Packet;
import org.geysermc.mcprotocollib.protocol.packet.common.serverbound.ServerboundCustomPayloadPacket;
import org.geysermc.mcprotocollib.protocol.packet.login.clientbound.ClientboundGameProfilePacket;

@RequiredArgsConstructor
public class BrandListener extends SessionAdapter {

  private final Bot bot;

  @Override
  public void packetReceived(Session session, Packet packet) {
    if (!(packet instanceof ClientboundGameProfilePacket)) {
      return;
    }
    var buf = Unpooled.buffer();
    session.getCodecHelper().writeString(buf, bot.getSettings().getClientBrand());

    session.send(new ServerboundCustomPayloadPacket(ProtocolConstants.BRAND_PAYLOAD_KEY,
        ByteBufUtil.getBytes(buf)));
  }
}
