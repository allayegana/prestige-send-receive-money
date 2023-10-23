package us.com.prestigemoney.sendreceive.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import us.com.prestigemoney.sendreceive.model.SenderData;
import us.com.prestigemoney.sendreceive.repository.SenderRepository;

import javax.validation.Valid;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

@Controller
public class SenderControllers {


    @Autowired
    private SenderRepository repository;

    @GetMapping("/index")
    public ModelAndView index() {
        ModelAndView mv = new ModelAndView("index");
        return mv;
    }


    @GetMapping("form")
    public ModelAndView getSenderData(SenderData senderData) {
        ModelAndView tv = new ModelAndView("form");
        return tv;
    }


    @RequestMapping(value = "/form", method = RequestMethod.POST)
    public ModelAndView sauve(@Valid SenderData senderData, BindingResult result, RedirectAttributes attributes) throws ParseException {
        if (result.hasErrors()) {
            return getSenderData(senderData);
        } else {
            ModelAndView mv = new ModelAndView("redirect:/listes");
            senderData.setJour(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            Random gerador = new Random();
            for (int i = 1; i < 20; i++) {
                gerador.nextInt();
            }
            senderData.setMatriculation(gerador.nextInt(30302022) + 1);
            repository.save(senderData);
            attributes.addFlashAttribute("mensagem", " formulaire rempli avec succès nous analisons et rentrons en contacter avec vous  " + senderData.getMatriculation() + " n'oubliez pas de note votre numero de matriculation");
            return mv;
        }
    }

    @RequestMapping("/listes")
    public ModelAndView customer() {
        ModelAndView mv = new ModelAndView("listes");
        Sort sort = Sort.by("id").ascending();
        Pageable page = PageRequest.of(0, 100, sort);
        Page<SenderData> senderData = repository.findAll(page);
        mv.addObject("senderData", senderData);
        long registros = repository.count();
        mv.addObject("registros", registros);
        return mv;
    }

    @RequestMapping("/{matriculation}")
    public ModelAndView consulteParMatriculation(@PathVariable("matriculation") int matriculation) {
        ModelAndView mv = new ModelAndView("filtre");
        SenderData senderData = repository.findByMatriculation(matriculation);
        mv.addObject("senderData", senderData);
        return mv;
    }
}
