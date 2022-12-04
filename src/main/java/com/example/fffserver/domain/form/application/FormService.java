package com.example.fffserver.domain.form.application;

import com.example.fffserver.domain.form.domain.entity.Form;
import com.example.fffserver.domain.form.domain.FormRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FormService {

    private final FormRepository formRepository;

    public FormService(FormRepository formRepository) {
        this.formRepository = formRepository;
    }

    public String insert(Form form) {
        Form saveForm = formRepository.save(form);

        return saveForm.getId().toString();
    }

    public List<Form> findAll() {
        return formRepository.findAll();
    }

    public void deleteAll() {
        formRepository.deleteAll();
    }
}
