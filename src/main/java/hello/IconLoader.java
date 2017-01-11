package hello;

/**
 * Defines how to load an icon.
 *
 * @author olivier.houyoux@gmail.com
 * @param <T> the type of loaded icon
 * @param <E> the type of exception that may be thrown when loading an icon
 */
public interface IconLoader<T, E extends Exception> {

  /**
   * Loads an icon.
   *
   * @param id the identifier of the icon to be loaded
   * @return the loaded icon
   * @throws E if an error occured while loading the icon
   */
  T load(long id) throws E;
}
