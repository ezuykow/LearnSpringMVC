package ru.ezuykow.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.ezuykow.app.dao.PeopleDAO;
import ru.ezuykow.app.models.Person;

@Controller
@RequestMapping("/people")
public class PeopleController {
    private final PeopleDAO people;

    @Autowired
    public PeopleController(PeopleDAO people) {
        this.people = people;
    }

    @GetMapping()
    public String indexPage(Model model) {
        model.addAttribute("people", people.index());
        return "people/index";
    }

    @GetMapping("/{id}")
    public String showPage(@PathVariable("id") int id,
                           Model model) {
        model.addAttribute("person", people.show(id));
        return "people/show";
    }

    @GetMapping("/new")
    public String newPage(@ModelAttribute("person") Person person) {
        return "people/new";
    }

    @PostMapping()
    public String addPerson(@ModelAttribute("person") Person person) {
        people.add(person);
        return "redirect:/people";
    }
}
