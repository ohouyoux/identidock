package hello;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class GreetingITCase {

  @LocalServerPort
  private int port;

  // TODO Find why @Autowired does not work
  //@Autowired
  private TestRestTemplate template;

  @Before
  public void setUp() {
    template = new TestRestTemplate();
  }

  @Test
  public void greetingShouldFirstReturnRegistrationForm() {
    String result = template.getForObject("http://localhost:" + port + "/greeting", String.class);

    assertThat(result)
      .contains("<h1>Form</h1>")
      .contains("<form action=\"/greeting\" method=\"post\">")
      .contains("Name: <input type=\"text\" id=\"name\" name=\"name\" value=\"\" />")
      .contains("<input type=\"submit\" value=\"Submit\" />")
      .contains("<input type=\"reset\" value=\"Reset\" />");
  }

  @Test
  public void greetingShouldAnswerWithName() {
    MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
    map.add("name", "Olivier");
    map.add("id", "0");
    String result =
        template.postForObject("http://localhost:" + port + "/greeting", map, String.class);
    System.out.println(result);

    assertThat(result).contains("Olivier");
  }

//  @Test
//  public void greetingShouldAnswerWithNewIcon() {
//
//  }

//  @Test
//  public void greetingShouldAnswerWithSameIconForSameIdentifier() {
//
//  }
}
