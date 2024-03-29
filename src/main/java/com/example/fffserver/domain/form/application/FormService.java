package com.example.fffserver.domain.form.application;

import static com.example.fffserver.global.exception.ExceptionCode.EMPTY_RESULT;

import com.example.fffserver.domain.common.EmptyResultException;
import com.example.fffserver.domain.form.domain.FormRepository;
import com.example.fffserver.domain.form.domain.entity.Form;
import com.example.fffserver.domain.form.domain.vo.Event;
import com.example.fffserver.domain.form.domain.vo.EventFactory;
import com.example.fffserver.global.exception.BusinessException;
import com.example.fffserver.global.exception.ExceptionCode;
import java.time.LocalDateTime;
import java.util.List;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

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

    LocalDateTime now = LocalDateTime.now();
    if (now.isAfter(form.getEndTime())) {
      throw new BusinessException(ExceptionCode.AFTER_END);
    }

    return EventFactory.createfromForm(form);
  }

  public List<Form> findAll() {
    return formRepository.findAll();
  }

  public void deleteAll() {
    formRepository.deleteAll();
  }

  public Form findById(String formId) {
    ObjectId formIdToObjectId = new ObjectId(formId);
    return formRepository.findById(formIdToObjectId)
        .orElseThrow(() -> new EmptyResultException(EMPTY_RESULT));
  }

  public void changeEndTime(LocalDateTime endTime, String formId) {
    Form form = formRepository.findById(new ObjectId(formId))
        .orElseThrow(() -> new EmptyResultException(EMPTY_RESULT));

    form.changeEndTime(endTime);
    formRepository.save(form);
  }

}
