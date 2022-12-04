package com.example.fffserver.domain.form.domain;

import com.example.fffserver.domain.form.domain.entity.Form;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormRepository extends MongoRepository<Form, ObjectId> {
}
