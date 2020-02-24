package com.uhsnarp.controller;

import com.uhsnarp.model.UserBO;
import com.uhsnarp.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@RequestMapping({"/users"})
@Controller
public class UserController {

    private static final String VIEWS_USER_CREATE_OR_UPDATE_FORM = "users/createOrUpdateUserForm";

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @InitBinder
    public void setAllowedFields(WebDataBinder dataBinder) {
        dataBinder.setDisallowedFields("id");
    }

    /*@GetMapping({"", "/", "/index", "/index.html"})
    public String listOwners(Model model) {
        model.addAttribute("users", userService.findAll());
        return "users/index";
    }*/

    @GetMapping({"/find"})
    public String findUsers(Model model) {
        model.addAttribute("user", UserBO.builder().build());
        return "users/findUsers";
    }

    @GetMapping
    public String processFindForm(UserBO userBO, BindingResult result, Model model) {
        //allow parameterless GET request for /users to return all records
        if (userBO.getName() == null) {
            userBO.setName(""); // empty string signifies broadest possible search
        }

        //find users by last name
        List<UserBO> results = userService.findAllByNameLike("%" + userBO.getName() + "%");
        if (results.isEmpty()) {
            //no users found
            result.rejectValue("name", "notFound", "not found");
            return "users/findUsers";
        } else if (results.size() == 1) {
            //1 userBO found
            userBO = results.get(0);
            return "redirect:/users/" + userBO.getId();
        } else {
            //multiple users found
            model.addAttribute("selections", results);
            return "users/usersList";
        }
    }

    @GetMapping("/{userId}")
    public ModelAndView showOwner(@PathVariable("userId") Integer userId) {
        ModelAndView mav = new ModelAndView("/users/userDetails");
        mav.addObject(userService.findById(userId));
        return mav;
    }

    @GetMapping("/new")
    public String initCreationForm(Model model) {
        model.addAttribute("user", UserBO.builder().build());
        return VIEWS_USER_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/new")
    public String processCreationForm(@Valid UserBO userBO, BindingResult result) {
        if (result.hasErrors()) {
            return VIEWS_USER_CREATE_OR_UPDATE_FORM;
        } else {
            UserBO savedUserBO = userService.save(userBO);
            return "redirect:/users/" + savedUserBO.getId();
        }
    }

    @GetMapping("/{userId}/edit")
    public String initUpdateOwnerForm(@PathVariable Integer userId, Model model) {
        model.addAttribute(userService.findById(userId));
        return VIEWS_USER_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/{userId}/edit")
    public String processUpdateOwnerForm(@Valid UserBO userBO, BindingResult result, @PathVariable Integer userId) {
        if (result.hasErrors()) {
            return VIEWS_USER_CREATE_OR_UPDATE_FORM;
        } else {
            userBO.setId(userId);
            UserBO savedUserBO = userService.save(userBO);
            return "redirect:/users/" + savedUserBO.getId();
        }
    }
}
