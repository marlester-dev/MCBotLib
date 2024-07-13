package me.marlester.mcbotlib.utils;

import java.net.MalformedURLException;
import java.net.URI;
import lombok.experimental.UtilityClass;

/**
 * Utils related to resource packs.
 */
@UtilityClass
public class ResourcePackUtils {

  /**
   * Checks if the given resource pack url is valid.
   *
   * @param url resource pack url.
   * @return true if valid, false if not.
   */
  public boolean isValidResourcePackUrl(String url) {
    if (url == null || url.isEmpty()) {
      return false;
    }
    try {
      var protocol = URI.create(url).toURL().getProtocol();
      return "http".equals(protocol) || "https".equals(protocol);
    } catch (MalformedURLException | IllegalArgumentException e) {
      return false;
    }
  }
}
