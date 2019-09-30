package com.asm.controller;

import com.asm.entity.Student;
import com.asm.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/students")
public class StudentController {
    @Autowired
    StudentService studentService;

    @RequestMapping(method = RequestMethod.GET, value = "/login")
    public String login() {
        return "login";
    }

    @RequestMapping(method = RequestMethod.GET) // lấy danh sách list => dang dung security len bat phai login
    public String list(Model model) {
        model.addAttribute("students", studentService.getList());
        return "list";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/create") // lay trang login do ra view
    public String create(Model model) {
        model.addAttribute("student", new Student());
        return "form";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/detail/{id}") // lay chi tiet sinh vien => dang nhap thi ms xem duoc detail
    public String detail(Model model, @PathVariable long id) {
        model.addAttribute("student", studentService.getDetail(id));
        return "detail";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/create") // gui thong tin tu form len kem validate
    public String store(@Valid Student student, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "form";
        }

        studentService.create(student);
        return "redirect:/students";
    }
}
