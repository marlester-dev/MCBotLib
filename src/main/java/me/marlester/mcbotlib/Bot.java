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

package me.marlester.mcbotlib;

import lombok.Getter;
import me.marlester.mcbotlib.clientlisteners.BaseListener;
import me.marlester.mcbotlib.clientlisteners.BrandListener;
import me.marlester.mcbotlib.clientlisteners.ClientInfoListener;
import me.marlester.mcbotlib.clientlisteners.DisconnectListener;
import me.marlester.mcbotlib.clientlisteners.PacketLogListener;
import me.marlester.mcbotlib.clientlisteners.ResourcePackListener;
import net.kyori.adventure.text.Component;
import org.geysermc.mcprotocollib.auth.GameProfile;
import org.geysermc.mcprotocollib.network.Session;
import org.geysermc.mcprotocollib.network.tcp.TcpClientSession;
import org.geysermc.mcprotocollib.protocol.MinecraftConstants;
import org.geysermc.mcprotocollib.protocol.MinecraftProtocol;
import org.geysermc.mcprotocollib.protocol.data.ProtocolState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * This class is responsible for creating bots.
 */
public class Bot {

  @NotNull
  @Getter
  private final String nick;
  @NotNull
  @Getter
  private final String serverIp;
  @Getter
  private final int serverPort;
  @NotNull
  @Getter
  private final BotSettings settings;

  /**
   * This is the session of the bot.
   * Responsible for almost everything.
   * <p>
   * For more read {@link Session}, {@link TcpClientSession}.
   */
  @Getter
  private final Session client;
  /**
   * The protocol used to create bot's client.
   * Responsible for many things such as auth, logging in and many more.
   */
  @Getter
  private final MinecraftProtocol protocol;

  /**
   * Creating the bot's instance and doing all of the important stuff.
   * But NOT connecting it to the server. To do it see {@link #connect()}.
   *
   * @param nick the nick (name/username) of the bot. Self-explanatory.
   * @param serverIp the ip (host/hostname) of the server you will then want to
   *                 connect the bot with. Like 12.435.323.57.
   * @param serverPort the port of the server you will then want to
   *                   connect the bot with. Like 25565 (5 numbers).
   * @param settings the BotSettings define lots of its properties.
   *                 They have access token, client brand, locale, etc.
   */
  public Bot(@NotNull String nick, @NotNull String serverIp,
             int serverPort, BotSettings settings) {
    this.nick = nick;
    this.serverIp = serverIp;
    this.serverPort = serverPort;
    this.settings = settings;

    // Initializing protocol and client
    var gameProfile = new GameProfile(settings.getId(), nick);
    protocol = new MinecraftProtocol(gameProfile, settings.getAccessToken());
    protocol.setUseDefaultListeners(false);
    client = new TcpClientSession(serverIp, serverPort, protocol, settings.getProxy());

    // Adding listeners, the correct order is important
    client.addListener(new DisconnectListener(this));
    client.addListener(new BaseListener(ProtocolState.LOGIN, false));
    client.addListener(new BrandListener(this));
    client.addListener(new ClientInfoListener(this));
    client.addListener(new ResourcePackListener(this));
    client.addListener(new PacketLogListener(this));

    // Setting essential flags
    client.setFlag(MinecraftConstants.AUTOMATIC_KEEP_ALIVE_MANAGEMENT, true);
    client.setFlag(MinecraftConstants.FOLLOW_TRANSFERS, true);
  }

  /**
   * Connects the bot with the server. See {@link Session#connect()}.
   */
  public void connect() {
    client.connect();
  }

  /**
   * Disconnects the bot from the server with given reason.
   *
   * @param reason the reason (Component).
   */
  public void disconnect(@Nullable Component reason) {
    if (reason == null) {
      reason = Component.empty();
    }
    client.disconnect(reason);
  }

  /**
   * Disconnects the bot from the server with blank reason.
   */
  public void disconnect() {
    disconnect((Component) null);
  }

  /**
   * Disconnects the bot from the server with given reason.
   *
   * @param reason the reason (String).
   */
  public void disconnect(@Nullable String reason) {
    if (reason == null) {
      reason = "";
    }
    disconnect(Component.text(reason));
  }
}
