package uz.brogrammers.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import uz.brogrammers.entity.Person;
import uz.brogrammers.model.PersonDao;

@Controller
@RequestMapping("/people")
public class PeopleController {

    @Autowired
    private final PersonDao personDao;

    public PeopleController(PersonDao personDao) {
        this.personDao = personDao;
    }

    @GetMapping
    public String index(Model model) {
        model.addAttribute("people", personDao.index());
        return "people/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", personDao.show(id));
        return "people/show";
    }

    @GetMapping("/new")
    public String newPerson(Model model) {
        model.addAttribute("person", new Person());
        return "people/new";

    }

    @PostMapping
    public String add(@ModelAttribute("person") Person person) {
        personDao.add(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String updateForm(Model model, @PathVariable("id") Integer id) {
        model.addAttribute("person", personDao.show(id));
        return "people/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") Person person, @PathVariable("id") int id) {
        personDao.update(id, person);
        return "redirect:/people";
    }


}
