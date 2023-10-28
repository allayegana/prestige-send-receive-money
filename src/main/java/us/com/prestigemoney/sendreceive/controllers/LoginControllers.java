package us.com.prestigemoney.sendreceive.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import us.com.prestigemoney.sendreceive.model.Dto.LoginModelDTO;
import us.com.prestigemoney.sendreceive.model.LoginModel;
import us.com.prestigemoney.sendreceive.repository.LoginRepository;

import javax.validation.Valid;
import java.text.ParseException;

@Controller
public class LoginControllers {

    @Autowired
    private LoginRepository repository;

    @GetMapping("/login")
    public ModelAndView getLoginModelDTO(@Valid LoginModelDTO loginModelDTO) {
        ModelAndView mv = new ModelAndView("login");
        return mv;
    }

    @GetMapping("/login-up")
    public ModelAndView getLoginUpModel(@Valid LoginModel loginModel) {
        ModelAndView mv = new ModelAndView("login-up");
        return mv;
    }

    @RequestMapping(value ="/check" , method = RequestMethod.POST)
    public ModelAndView Sauvegarder(@Valid LoginModelDTO dto, BindingResult result, RedirectAttributes attributes) throws ParseException {
        if (result.hasErrors()) {
            return getLoginModelDTO(dto);
        } else {

            ModelAndView mv = new ModelAndView("redirect:/prestige-principal");
            ModelAndView mv1 = new ModelAndView("redirect:/login");


            LoginModel loRepository = repository.findByMotpass(dto.getMotpass());

            if (!dto.getUsuario().equals(loRepository.getUsuario()) && !dto.getMotpass().equals(loRepository.getMotpass())) {
                attributes.addFlashAttribute("mensagems", " password or usuario invalid");
                return mv1;
            }
            attributes.addFlashAttribute("mensagem", " singn with succes");

            return mv;

        }
    }

//    @RequestMapping("/listar")
//    public ModelAndView customer() {
//        ModelAndView mv = new ModelAndView("listes");
//        Sort sort = Sort.by("id").ascending();
//        Pageable page = PageRequest.of(0, 100, sort);
//        Page<SenderData> senderData = repository.findAll(page);
//        mv.addObject("senderData", senderData);
//        long registros = repository.count();
//        mv.addObject("registros", registros);
//        return mv;
//    }

    @RequestMapping(value = "/login-up", method = RequestMethod.POST)
    public ModelAndView login(@Valid LoginModel loginModel, BindingResult result, RedirectAttributes attributes) throws ParseException {
        if (result.hasErrors()) {
            return getLoginUpModel(loginModel);
        } else {
            ModelAndView mv = new ModelAndView("redirect:/login");

            repository.save(loginModel);
            attributes.addFlashAttribute("mensagem", " singn with succes");
            return mv;
        }
    }
}
