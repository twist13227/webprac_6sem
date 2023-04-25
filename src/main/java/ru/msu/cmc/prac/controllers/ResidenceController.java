package ru.msu.cmc.prac.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public String residencesListPage(Model model) {
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

    @GetMapping("/editResidence")
    public String editResidencePage(@RequestParam(name = "residenceId", required = false) Long Id, Model model) {
        if (Id == null) {
            model.addAttribute("residence", new Residence());
            return "editResidence";
        }
        Residence residence = residenceDAO.getById(Id);
        if (residence == null) {
            model.addAttribute("error_msg", "В базе нет места, ID которого равен " + Id);
            return "errorPage";
        }

        model.addAttribute("residencce", residence);
        return "editResidence";
    }

    @PostMapping("/saveResidence")
    public String saveResidencePage(@RequestParam(name = "residenceId") Long Id,
                                    @RequestParam(name = "country") String country,
                                    @RequestParam(name = "town") String town,
                                    @RequestParam(name = "address") String address,
                                    @RequestParam(name = "residenceDescription") String description,
                                    Model model) {
        Residence residence = new Residence(Id, country, town, address, description);
        residenceDAO.save(residence);
        return String.format("redirect:/residence?residenceId=%d", residence.getId());
    }

    @PostMapping("/removeResidence")
    public String removeResidencePage(@RequestParam(name = "residenceId") Long Id) {
        residenceDAO.deleteById(Id);
        return "redirect:/residences";
    }
}
