package spittr.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import spittr.Spitter;
import spittr.data.SpitterRepository;

import javax.servlet.http.Part;
import javax.validation.Valid;
import java.io.IOException;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping("/spitter")
public class SpitterController {

  private SpitterRepository spitterRepository;

  @Autowired
  public SpitterController(SpitterRepository spitterRepository) {
    this.spitterRepository = spitterRepository;
  }
  
  @RequestMapping(value="/register", method=GET)
  public String showRegistrationForm() {
    return "registerForm";
  }


//  @RequestMapping(value="/register", method=POST)
//  public String processRegistration(
//          @RequestPart(value="profilePictures", required=false) Part fileBytes,
//          RedirectAttributes redirectAttributes,
//          @Valid Spitter spitter,
//          Errors errors) throws IOException {
//    if (errors.hasErrors()) {
//      return "registerForm";
//    }
//
//    spitterRepository.save(spitter);
//    redirectAttributes.addAttribute("username", spitter.getUsername());
//    redirectAttributes.addFlashAttribute(spitter);
//    return "redirect:/spitter/" + spitter.getUsername();
//  }

  @RequestMapping(value="/register", method=POST)
  public String processRegistration(
          @RequestPart(value="profilePictures", required=false) Part fileBytes,
          RedirectAttributes redirectAttributes,
          @Valid Spitter spitter,
          Errors errors) throws IOException {
    if (errors.hasErrors()) {
      return "registerForm";
    }

    spitterRepository.save(spitter);
    redirectAttributes.addAttribute("username", spitter.getUsername());
    redirectAttributes.addFlashAttribute(spitter);
    return "redirect:/spitter/" + spitter.getUsername();
  }

  @RequestMapping(value="/register", method=POST)
  public String processRegistration(
      @Valid Spitter spitter,
      Errors errors) {
    if (errors.hasErrors()) {
      return "registerForm";
    }

    spitterRepository.save(spitter);
    return "redirect:/spitter/" + spitter.getUsername();
  }
  
  @RequestMapping(value="/{username}", method=GET)
  public String showSpitterProfile(@PathVariable String username, Model model) {
    Spitter spitter = spitterRepository.findByUsername(username);
    model.addAttribute(spitter);
    return "profile";
  }
  
}
