package ru.msu.cmc.prac.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.msu.cmc.prac.DAOs.PersonDAO;
import ru.msu.cmc.prac.DAOs.ResidenceDAO;
import ru.msu.cmc.prac.DAOs.impl.PersonDAOImpl;
import ru.msu.cmc.prac.DAOs.impl.ResidenceDAOImpl;
import ru.msu.cmc.prac.classes.Residence;

import java.util.List;

@Controller
public class ResidenceController {
    @Autowired
    private final PersonDAO personDAO = new PersonDAOImpl();

    @Autowired
    private final ResidenceDAO residenceDAO = new ResidenceDAOImpl();

    @GetMapping("/residences")
    public String residences(Model model) {
        List<Residence> residences = (List<Residence>) residenceDAO.getAll();
        model.addAttribute("residences", residences);
        return "residences";
    }

    @GetMapping("/residence")
    public String residencePage(@RequestParam(name = "residenceId") Long Id, Model model) {
        Residence residence = residenceDAO.getById(Id);
        if (residence == null) {
            model.addAttribute("error_msg", "В базе нет места, ID которого равен " + Id);
            return "errorPage";
        }
        model.addAttribute("residence", residence);
        model.addAttribute("personService", personDAO);
        model.addAttribute("residenceService", residenceDAO);
        return "residence";
    }

    @GetMapping("editResidence")
    public String editResidenceGet(@RequestParam(name = "Id") Long Id, Model model) {
        if (Id == null) {
            model.addAttribute("residence", new Residence());
            return "editResidence";
        }
        Residence residence = residenceDAO.getById(Id);
        if (residence == null) {
            model.addAttribute("error_msg", "В базе нет места, ID которого равен " + Id);
            return "errorPage";
        }
        model.addAttribute("residence", residence);
        return "editResidence";
    }
    @PostMapping("/editResidence")
    public String editResidencePage(@RequestParam(name = "Id") Long Id,
                                    @RequestParam(name = "country") String country,
                                    @RequestParam(name = "town") String town,
                                    @RequestParam(name = "address") String address,
                                    @RequestParam(name = "description", required = false) String description,
                                    RedirectAttributes redirectAttributes) {
        Residence residence = residenceDAO.getById(Id);
        residence.setCountry(country);
        residence.setTown(town);
        residence.setAddress(address);
        residence.setDescription(description);
        residenceDAO.save(residence);
        redirectAttributes.addFlashAttribute("message", "Информация о месте успешно отредактировна в базе!");
        return "redirect:/residences";
    }

    @GetMapping("addResidence")
    public String addResidenceGet() {
        return "addResidence";
    };


    @PostMapping(value="/addResidence")
    public String addResidence(@RequestParam(name = "Id") Long Id,
                               @RequestParam(name = "country") String country,
                               @RequestParam(name = "town") String town,
                               @RequestParam(name = "address") String address,
                               @RequestParam(name = "description", required = false) String description,
                      RedirectAttributes redirectAttributes) {
        Residence newResidence = new Residence(Id, country, town, address, description);
        residenceDAO.save(newResidence);
        redirectAttributes.addFlashAttribute("message", "Новое место успешно добавлено в базу!");
        return "redirect:/residences";
    }


    @PostMapping("/deleteResidence")
    public String removeResidencePage(@RequestParam(name = "residenceId") Long residenceId) {
        residenceDAO.deleteById(residenceId);
        return "redirect:/residences";
    }
}
