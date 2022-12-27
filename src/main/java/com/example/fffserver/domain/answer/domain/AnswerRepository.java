package com.example.fffserver.domain.answer.domain;

import com.example.fffserver.domain.answer.domain.entity.Answer;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerRepository extends MongoRepository<Answer, ObjectId> {

     List<Answer> findAllByFormId(ObjectId formId);
}
