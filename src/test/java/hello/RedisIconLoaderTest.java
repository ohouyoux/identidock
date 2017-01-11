package hello;

import java.io.IOException;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.mockito.Mockito.mock;
import redis.embedded.RedisServer;

public class RedisIconLoaderTest {

  private static RedisServer server;
  
  @BeforeClass
  public static void setUpClass() throws IOException {
    server = new RedisServer(6379);
    server.start();
  }
  
  @AfterClass
  public static void tearDownClass() {
    server.stop();
  }
  
  private IconLoader<String, RuntimeException> loaderMock;
  
  private RedisIconLoader systemUnderTest;
  
  @Before
  public void setUp() {
    loaderMock = mock(IconLoader.class);
    systemUnderTest = new RedisIconLoader(loaderMock, "localhost");
  }
  
  @Test
  public void shouldReturnEmptyIconWhenNothingIsInCache() {
    Optional<String> result = systemUnderTest.getIcon(0);
    
    assertThat(result.isPresent()).isFalse();
  }
  
  @Test
  public void shouldStoreIconInCache() {
    systemUnderTest.addIcon(3, "test");
    Optional<String> result = systemUnderTest.getIcon(3);
    
    assertThat(result.isPresent()).isTrue();
    assertThat(result.get()).isEqualTo("test");
  }
}
