package com.tonghoangvu.lhufriend.repository;

import com.tonghoangvu.lhufriend.entity.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CustomStudentRepository {
    private final MongoOperations mongoOperations;

    public List<Student> getStudents(int page, int size) {
        Query query = new Query()
                .with(Sort.by("studentId").descending())
                .with(PageRequest.of(page, size));
        return mongoOperations.find(query, Student.class);
    }

    public long getStudentCount() {
        return mongoOperations.count(new Query(), Student.class);
    }
}
