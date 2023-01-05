package com.example.fffserver.domain.answer.controller;

import com.example.fffserver.domain.answer.application.AnswerService;
import com.example.fffserver.domain.answer.domain.entity.Answer;
import com.example.fffserver.domain.form.application.FormService;
import com.example.fffserver.domain.form.domain.entity.Form;
import com.example.fffserver.domain.question.domain.entity.Question;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class AnswerViewController {

    private final AnswerService answerService;
    private final FormService formService;

    public AnswerViewController(AnswerService answerService, FormService formService) {
        this.answerService = answerService;
        this.formService = formService;
    }

    /**
     * 응답 조회 화면
     */
    @GetMapping("/api/v1/answer/{formId}")
    public String viewAnswerPage(@PathVariable String formId, Model model) {
        List<Answer> answerList = answerService.findAllByForm(formId);
        Form form = formService.findById(formId);
        List<Question> questions = form.getQuestions();

        model.addAttribute("form", form);
        model.addAttribute("questions", questions);
        model.addAttribute("answers", answerList);
        return "answer";
    }

    @GetMapping("/test")
    public String v() {
        return "test";
    }
}
