package hello;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * The Spring {@code Controller} which orchestrates the 'Greeting' application.
 *
 * @author olivier.houyoux@gmail.com
 */
@Controller
public class GreetingController {

  /**
   * Displays a greeting form.
   *
   * @param model the {@code Model} used to fill the greeting form page
   * @return the greeting form page name
   */
  @GetMapping("/greeting")
  public String greetingForm(final Model model) {
    model.addAttribute("greeting", new Greeting());

    return "greeting";
  }

  /**
   * Submits a greeting, displaying the user's greeting.
   *
   * @param greeting the {@code Greeting} to be displayed
   * @return the result page name
   */
  @PostMapping("/greeting")
  public String greetingSubmit(@ModelAttribute final Greeting greeting) {
      return "result";
  }
}