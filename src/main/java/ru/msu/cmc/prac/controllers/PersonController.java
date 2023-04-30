package ru.msu.cmc.prac.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.msu.cmc.prac.DAOs.PersonDAO;
import ru.msu.cmc.prac.DAOs.Person_ResidenceDAO;
import ru.msu.cmc.prac.DAOs.RelationDAO;
import ru.msu.cmc.prac.DAOs.ResidenceDAO;
import ru.msu.cmc.prac.DAOs.impl.PersonDAOImpl;
import ru.msu.cmc.prac.DAOs.impl.Person_ResidenceDAOImpl;
import ru.msu.cmc.prac.DAOs.impl.RelationDAOImpl;
import ru.msu.cmc.prac.DAOs.impl.ResidenceDAOImpl;
import ru.msu.cmc.prac.classes.Person;
import ru.msu.cmc.prac.classes.Residence;

import java.util.List;

@Controller
public class PersonController {
    @Autowired
    private final PersonDAO personDAO = new PersonDAOImpl();

    @Autowired
    private final ResidenceDAO residenceDAO = new ResidenceDAOImpl();

    @Autowired
    private final Person_ResidenceDAO person_residenceDAO = new Person_ResidenceDAOImpl();

    @Autowired
    private final RelationDAO relationDAO = new RelationDAOImpl();

    @GetMapping("/people")
    public String people(Model model) {
        List<Person> people = (List<Person>) personDAO.getAll();
        model.addAttribute("people", people);
        model.addAttribute("personService", personDAO);
        model.addAttribute("relationService", relationDAO);
        return "people";
    }

    @GetMapping("/person")
    public String person(@RequestParam(name = "personId") Long Id, Model model) {
        Person person = personDAO.getById(Id);
        if (person == null) {
            model.addAttribute("error_msg", "В базе нет человека, ID которого равен " + Id);
            return "errorPage";
        }
        model.addAttribute("person", person);
        model.addAttribute("personService", personDAO);
        model.addAttribute("residenceService", residenceDAO);
        model.addAttribute("person_residenceService", person_residenceDAO);
        model.addAttribute("relationService", relationDAO);
        return "person";
    }
    @GetMapping("add")
    public String addGet() {
        return "add";
    };


    @PostMapping(value="/add")
    public String add(@RequestParam(name = "Id") Long Id,
                                 @RequestParam(name = "surname") String surname,
                                 @RequestParam(name = "name") String name,
                                 @RequestParam(name = "patronymic") String patronymic,
                                 @RequestParam(name = "gender") String gender,
                                 @RequestParam(name = "birth_date", required = false) String birth_date,
                                 @RequestParam(name = "death_date", required = false) String death_date,
                                 @RequestParam(name = "characteristics", required = false) String characteristics,
                                 RedirectAttributes redirectAttributes) {
        Person newPerson = new Person(Id, surname, name, patronymic, gender, birth_date, death_date, characteristics);
        personDAO.save(newPerson);
        redirectAttributes.addFlashAttribute("message", "Новый человек успешно добавлен в базу!");
        return "redirect:/people";
    }

    @GetMapping("editPerson")
    public String editPersonGet(@RequestParam(name = "Id") Long Id, Model model) {
        if (Id == null) {
            model.addAttribute("person", new Person());
            return "editPerson";
        }
        Person person = personDAO.getById(Id);
        if (person == null) {
            model.addAttribute("error_msg", "В базе нет человека, ID которого равен " + Id);
            return "errorPage";
        }
        model.addAttribute("person", person);
        return "editPerson";
    }
    @PostMapping("/editPerson")
    public String editResidencePage(@RequestParam(name = "Id") Long Id,
                                    @RequestParam(name = "surname") String surname,
                                    @RequestParam(name = "name") String name,
                                    @RequestParam(name = "patronymic") String patronymic,
                                    @RequestParam(name = "gender") String gender,
                                    @RequestParam(name = "birth_date", required = false) String birth_date,
                                    @RequestParam(name = "death_date", required = false) String death_date,
                                    @RequestParam(name = "characteristics", required = false) String characteristics,
                                    RedirectAttributes redirectAttributes) {
        Person person = personDAO.getById(Id);
        person.setSurname(surname);
        person.setName(name);
        person.setPatronymic(patronymic);
        person.setGender(gender);
        person.setBirth_date(birth_date);
        person.setDeath_date(death_date);
        person.setCharacteristics(characteristics);
        personDAO.save(person);
        redirectAttributes.addFlashAttribute("message", "Информация о человеке успешно отредактирована в базе!");
        return "redirect:/people";
    }
    @PostMapping("/removePerson")
    public String removePersonPage(@RequestParam(name = "personId") Long personId) {
        personDAO.deleteById(personId);
        return "redirect:/people";
    }

}
