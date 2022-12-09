package com.example.fffserver.domain.question.domain;

import com.example.fffserver.domain.question.domain.entity.Question;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionReposioty extends MongoRepository<Question, ObjectId> {
}
