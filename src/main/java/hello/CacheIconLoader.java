package hello;

import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A default implementation for caching the icon contents.
 * 
 * @author olivier.houyoux@gmail.com
 */
public abstract class CacheIconLoader implements IconLoader<String, RuntimeException> {
  
  private static final Logger LOGGER = Logger.getAnonymousLogger();
  
  private final IconLoader<String, RuntimeException> loader;
  
  /**
   * Instantiates a new {@code CacheIconLoader}.
   * 
   * @param loader the decorated {@code IconLoader} which loads the real icons
   */
  public CacheIconLoader(final IconLoader<String, RuntimeException> loader) {
    this.loader = loader;
  }
  
  /**
   * Returns the {@code IconLoader} which is supposed to load the icons that were not cached yet.
   * 
   * @return the decorated {@code IconLoader}
   */
  protected IconLoader<String, RuntimeException> getIconLoader() {
    return loader;
  }
  
  @Override
  public String load(final long id) {
    String icon;
    Optional<String> tmp = getIcon(id);
    
    if (tmp.isPresent()) {
      icon = tmp.get();
      LOGGER.log(Level.INFO, "Cache hit with id {0}", id);
    } else {
      icon = getIconLoader().load(id);
      addIcon(id, icon);
      LOGGER.log(Level.INFO, "Icon loaded for id {0}", id);
    }
    
    return icon;
  }
  
  /**
   * Tries and load a cached icon. 
   * 
   * @param id the identifier of the icon to load
   * @return the loaded icon if it is cached
   */
  protected abstract Optional<String> getIcon(long id);
  
  /**
   * Adds a new icon within the cache.
   * 
   * @param id the identifier of the icon to be cached
   * @param icon the icon to be cached
   */
  protected abstract void addIcon(long id, String icon);
}
