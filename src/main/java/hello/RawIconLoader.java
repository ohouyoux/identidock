package hello;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;

/**
 * An {@code IconLoader} implementation which uses the "dnmonster" services to load a new icon.
 *
 * @author olivier.houyoux@gmail.com
 */
public class RawIconLoader implements IconLoader<byte[], IOException> {

  private static final String BASE = "http://dnmonster:8080/monster/";

  private static final String SIZE = "?size=80";

  @Override
  public byte[] load(final long id) throws IOException {
    String spec = BASE + id + SIZE;
    URL url = new URL(spec);
    BufferedImage img = ImageIO.read(url);
    byte[] data;

    try (final ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
      ImageIO.write(img, "png", baos);
      baos.flush();
      data = baos.toByteArray();
    }

    return data;
  }
}
