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
