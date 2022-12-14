package com.example.fffserver.domain.form.application;

import com.example.fffserver.domain.common.EmptyResultException;
import com.example.fffserver.domain.form.domain.entity.Form;
import com.example.fffserver.domain.form.domain.FormRepository;
import com.example.fffserver.domain.form.domain.vo.Event;
import com.example.fffserver.domain.form.domain.vo.EventFactory;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.fffserver.global.exception.ExceptionCode.EMPTY_RESULT;

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

    public Event getEvent(String formId) {
        Form form = formRepository.findById(new ObjectId(formId))
                .orElseThrow(() -> new EmptyResultException(EMPTY_RESULT));

        return EventFactory.createfromForm(form);
    }

    public List<Form> findAll() {
        return formRepository.findAll();
    }

    public void deleteAll() {
        formRepository.deleteAll();
    }
}
