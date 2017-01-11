package hello;

/**
 * Creates a {@code IconLoader} implementation which encodes icon with Base64 encoding and caches
 * the icon contents to speed up the loading process.
 *
 * @author olivier.houyoux@gmail.com
 */
public class DefaultIconLoaderFactory implements IconLoaderFactory<String, RuntimeException> {

  private static final IconLoader<String, RuntimeException> LOADER =
    new RedisIconLoader(new EncodedIconLoader(new RawIconLoader()), "redis");

  @Override
  public IconLoader<String, RuntimeException> newInstance() {
      return LOADER;
  }
}
