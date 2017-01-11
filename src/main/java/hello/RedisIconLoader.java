package hello;

import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Decorates an {@code IconLoader} implementation with a caching system based on Redis.
 *
 * @author olivier.houyoux@gmail.com
 */
public class RedisIconLoader extends CacheIconLoader {

  private final JedisPool pool;

  /**
   * Instantiates a new {@code RedisIconLoader}.
   *
   * @param loader the decorated {@code IconLoader}
   * @param host the host name of the Redis server
   */
  public RedisIconLoader(final IconLoader<String, RuntimeException> loader, final String host) {
    super(loader);
    pool = new JedisPool(new JedisPoolConfig(), host, 6379);
  }

  @Override
  protected Optional<String> getIcon(final long id) {
    Optional<String> icon = Optional.empty();
    String key = Long.toString(id);

    try (final Jedis client = pool.getResource()) {
      String data = client.get(key);
      icon = Optional.ofNullable(data);
    } catch (final Exception e) {
      Logger.getAnonymousLogger().log(Level.WARNING, "Could not load icon from cache", e);
    }

    return icon;
  }

  @Override
  protected void addIcon(final long id, final String icon) {
    try (final Jedis client = pool.getResource()) {
      String key = Long.toString(id);
      client.set(key, icon);
    }
  }
}
