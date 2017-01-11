package hello;

import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CacheIconLoaderTest {
  
  private CacheIconLoader systemUnderTest;
  
  @Mock
  private IconLoader<String, RuntimeException> loaderMock;
  
  @Before
  public void setUp() {
    systemUnderTest = mock(CacheIconLoader.class, Mockito.CALLS_REAL_METHODS);
    when(systemUnderTest.getIconLoader()).thenReturn(loaderMock);
  }
  
  @Test
  public void shouldLoadCachedIcon() {
    when(systemUnderTest.getIcon(0)).thenReturn(Optional.of("test"));
    String result = systemUnderTest.load(0);

    assertThat(result).isEqualTo("test");
    verify(loaderMock, never()).load(0);
    verify(systemUnderTest, never()).addIcon(0, "test");
  }
  
  @Test
  public void shouldLoadUncachedIconFromDecoratedLoader() {
    when(systemUnderTest.getIcon(0)).thenReturn(Optional.ofNullable(null));
    when(loaderMock.load(0)).thenReturn("test");
    String result = systemUnderTest.load(0);

    assertThat(result).isEqualTo("test");
    verify(loaderMock).load(0);
    verify(systemUnderTest).addIcon(0, "test");
  }
}
