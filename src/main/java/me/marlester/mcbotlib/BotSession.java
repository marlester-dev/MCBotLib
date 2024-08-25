package me.marlester.mcbotlib;

import lombok.Getter;
import me.marlester.mcbotlib.clientlisteners.BaseListener;
import me.marlester.mcbotlib.clientlisteners.BrandListener;
import me.marlester.mcbotlib.clientlisteners.ClientInfoListener;
import me.marlester.mcbotlib.clientlisteners.DisconnectListener;
import me.marlester.mcbotlib.clientlisteners.PacketLogListener;
import me.marlester.mcbotlib.clientlisteners.ResourcePackListener;
import org.geysermc.mcprotocollib.auth.GameProfile;
import org.geysermc.mcprotocollib.network.tcp.TcpClientSession;
import org.geysermc.mcprotocollib.protocol.MinecraftConstants;
import org.geysermc.mcprotocollib.protocol.MinecraftProtocol;
import org.geysermc.mcprotocollib.protocol.data.ProtocolState;
import org.jetbrains.annotations.NotNull;

public class BotSession extends TcpClientSession {

  @NotNull
  @Getter
  private final String nick;
  @NotNull
  @Getter
  private final BotSettings settings;

  /**
   * Creating a new {@link BotSession} instance with standard settings and
   * doing all of the important stuff.
   * But NOT connecting it to the server. To do it see {@link #connect()}.
   *
   * @param nick the nick (name/username) of the bot. Self-explanatory.
   * @param serverIp the ip (host/hostname) of the server you will then want to
   *                 connect the bot with. Like 12.435.323.57.
   * @param serverPort the port of the server you will then want to
   *                   connect the bot with. Like 25565 (5 numbers).
   */
  public BotSession(@NotNull String nick, @NotNull String serverIp, int serverPort) {
    this(nick, serverIp, serverPort, BotSettings.builder().build());
  }

  /**
   * Creating a new {@link BotSession} instance and doing all of the important stuff.
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
  public BotSession(@NotNull String nick, @NotNull String serverIp,
      int serverPort, @NotNull BotSettings settings) {
    super(serverIp, serverPort, createProtocol(nick, settings), settings.getProxy());

    this.nick = nick;
    this.settings = settings;

    // Adding listeners, the correct order is important
    addListener(new DisconnectListener(this));
    addListener(new BaseListener(ProtocolState.LOGIN, false));
    addListener(new BrandListener(this));
    addListener(new ClientInfoListener(this));
    addListener(new ResourcePackListener(this));
    addListener(new PacketLogListener(this));

    // Setting essential flags
    setFlag(MinecraftConstants.AUTOMATIC_KEEP_ALIVE_MANAGEMENT, true);
  }

  private static MinecraftProtocol createProtocol(String nick, BotSettings settings) {
    var gameProfile = new GameProfile(settings.getId(), nick);
    var protocol = new MinecraftProtocol(gameProfile, settings.getAccessToken());
    protocol.setUseDefaultListeners(false);
    return protocol;
  }
}
