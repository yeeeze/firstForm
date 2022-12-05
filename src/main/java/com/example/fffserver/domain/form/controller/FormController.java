package com.example.fffserver.domain.form.controller;

import com.example.fffserver.domain.form.application.FormService;
import com.example.fffserver.domain.form.dto.PostFormReq;
import com.example.fffserver.domain.form.domain.entity.Form;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FormController {

    private final FormService formService;

    public FormController(FormService formService) {
        this.formService = formService;
    }

    @PostMapping("api/v1/form")
    public ResponseEntity<String> createForm(@RequestBody PostFormReq postFormReq) {
        Form form = postFormReq.toForm();
        String formId = formService.insert(form);
        return ResponseEntity.ok().body(formId);
    }
}
