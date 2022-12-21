package com.example.fffserver.domain.form.controller;

import com.example.fffserver.domain.form.application.FormService;
import com.example.fffserver.domain.form.domain.entity.Form;
import com.example.fffserver.domain.question.domain.entity.Question;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class FormViewController {

    private final FormService formService;

    public FormViewController(FormService formService) {
        this.formService = formService;
    }

    /**
     * 입장 대기 페이지
     */
    @GetMapping("/api/v1/form/{formId}")
    public String viewEnterPage(@PathVariable String formId, Model model) {
        Form form = formService.findById(formId);
        model.addAttribute("formId", form.getId().toString());
        model.addAttribute("startTime", form.getStartTime());
        model.addAttribute("winnersNum", form.getWinnersNumber());
        return "entrance";
    }

    /**
     * 응답 작성 페이지
     */
    @GetMapping("/api/v1/form/{formId}/enter")
    public String viewSubmitPage(@PathVariable String formId, Model model) {
        Form form = formService.findById(formId);
        List<Question> questions = form.getQuestions();
        model.addAttribute("form", form);
        model.addAttribute("formId", form.getId().toString());
        model.addAttribute("questions", questions);
        return "submitForm";
    }
}
