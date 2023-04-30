package ru.msu.cmc.prac.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.msu.cmc.prac.DAOs.PersonDAO;
import ru.msu.cmc.prac.DAOs.RelationDAO;
import ru.msu.cmc.prac.DAOs.impl.PersonDAOImpl;
import ru.msu.cmc.prac.DAOs.impl.RelationDAOImpl;
import ru.msu.cmc.prac.classes.Person;
import ru.msu.cmc.prac.classes.Relation;

import java.util.List;

@Controller
public class RelationController {
    @Autowired
    private final PersonDAO personDAO = new PersonDAOImpl();

    @Autowired
    private final RelationDAO relationDAO = new RelationDAOImpl();

    @GetMapping("/relations")
    public String relations(Model model) {
        List<Relation> relations = (List<Relation>) relationDAO.getAll();
        model.addAttribute("relations", relations);
        return "relations";
    }

    @GetMapping("/relation")
    public String relationPage(@RequestParam(name = "relationId") Long Id, Model model) {
        Relation relation = relationDAO.getById(Id);
        if (relation == null) {
            model.addAttribute("error_msg", "В базе нет взаимоотношения, ID которого равен " + Id);
            return "errorPage";
        }
        model.addAttribute("relation", relation);
        model.addAttribute("personService", personDAO);
        model.addAttribute("relationService", relationDAO);
        return "relation";
    }

    @GetMapping("editRelation")
    public String editRelationGet(@RequestParam(name = "Id") Long Id, Model model) {
        if (Id == null) {
            model.addAttribute("relation", new Relation());
            return "editRelation";
        }
        Relation relation = relationDAO.getById(Id);
        if (relation == null) {
            model.addAttribute("error_msg", "В базе нет отношения, ID которого равен " + Id);
            return "errorPage";
        }
        model.addAttribute("relation", relation);
        model.addAttribute("parent", Relation.RelType.PARENT);
        model.addAttribute("child", Relation.RelType.CHILD);
        model.addAttribute("parent_in_law", Relation.RelType.PARENT_IN_LAW);
        model.addAttribute("child_in_law", Relation.RelType.CHILD_IN_LAW);
        model.addAttribute("spouse", Relation.RelType.SPOUSE);
        model.addAttribute("bastard", Relation.RelType.BASTARD);
        model.addAttribute("adopted_child", Relation.RelType.ADOPTED_CHILD);
        model.addAttribute("adoptive_parent", Relation.RelType.ADOPTIVE_PARENT);
        return "editRelation";
    }
    @PostMapping("/editRelation")
    public String editRelationPage(@RequestParam(name = "Id") Long Id,
                                    @RequestParam(name = "first_person") Person first_person,
                                    @RequestParam(name = "second_person") Person second_person,
                                    @RequestParam(name = "relation_type") Relation.RelType relation_type,
                                    @RequestParam(name = "information", required = false) String information,
                                    RedirectAttributes redirectAttributes) {
        Relation relation = relationDAO.getById(Id);
        relation.setFirst_person_id(first_person);
        relation.setSecond_person_id(second_person);
        relation.setRelation_type(relation_type);
        relation.setInformation(information);
        relationDAO.save(relation);
        redirectAttributes.addFlashAttribute("message", "Информация об отношении успешно отредактировна в базе!");
        return "redirect:/relations";
    }

    @GetMapping("addRelation")
    public String addRelationGet(Model model) {
        model.addAttribute("parent", Relation.RelType.PARENT);
        model.addAttribute("child", Relation.RelType.CHILD);
        model.addAttribute("parent_in_law", Relation.RelType.PARENT_IN_LAW);
        model.addAttribute("child_in_law", Relation.RelType.CHILD_IN_LAW);
        model.addAttribute("spouse", Relation.RelType.SPOUSE);
        model.addAttribute("bastard", Relation.RelType.BASTARD);
        model.addAttribute("adopted_child", Relation.RelType.ADOPTED_CHILD);
        model.addAttribute("adoptive_parent", Relation.RelType.ADOPTIVE_PARENT);
        return "addRelation";
    }


    @PostMapping(value="/addRelation")
    public String addRelation(@RequestParam(name = "Id") Long Id,
                               @RequestParam(name = "first_person_id") Long first_person_id,
                               @RequestParam(name = "second_person_id") Long second_person_id,
                               @RequestParam(name = "relation_type") Relation.RelType relation_type,
                               @RequestParam(name = "information", required = false) String information,
                               RedirectAttributes redirectAttributes) {
        Relation newRelation = new Relation(Id, personDAO.getById(first_person_id), personDAO.getById(second_person_id), relation_type, information);
        relationDAO.save(newRelation);
        redirectAttributes.addFlashAttribute("message", "Новое взаимотношение успешно добавлено в базу!");
        return "redirect:/relations";
    }


    @PostMapping("/removeRelation")
    public String removeRelationPage(@RequestParam(name = "relationId") Long relationId) {
        relationDAO.deleteById(relationId);
        return "redirect:/relations";
    }
}
