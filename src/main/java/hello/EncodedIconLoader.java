package hello;

import java.io.IOException;
import org.apache.commons.codec.binary.Base64;

/**
 * Encodes an icon content with the Base64 encoder.
 *
 * @author olivier.houyoux@gmail.com
 */
public class EncodedIconLoader implements IconLoader<String, RuntimeException> {

  private final IconLoader<byte[], IOException> loader;

  /**
   * Instantiates a new {@code EncodedIconLoader}.
   *
   * @param loader the {@code IconLoader} which provides the current icon to encode
   */
  public EncodedIconLoader(final IconLoader<byte[], IOException> loader) {
    this.loader = loader;
  }

  @Override
  public String load(final long id) {
    try {
      byte[] data = loader.load(id);

      return Base64.encodeBase64String(data);
    } catch (final IOException e) {
      throw new RuntimeException("Could not get image for id " + id, e);
    }
  }
}
