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

package me.marlester.mcbotlib;

import com.github.steveice10.mc.auth.data.GameProfile;
import lombok.Getter;
import me.marlester.mcbotlib.clientlisteners.BaseListener;
import me.marlester.mcbotlib.clientlisteners.BrandListener;
import me.marlester.mcbotlib.clientlisteners.ClientInfoListener;
import me.marlester.mcbotlib.clientlisteners.DisconnectListener;
import me.marlester.mcbotlib.clientlisteners.PacketLogListener;
import me.marlester.mcbotlib.clientlisteners.ResourcePackListener;
import net.kyori.adventure.text.Component;
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
