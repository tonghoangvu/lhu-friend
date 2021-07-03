package com.tonghoangvu.lhufriend.service;

import com.tonghoangvu.lhufriend.common.Const;
import com.tonghoangvu.lhufriend.entity.Student;
import com.tonghoangvu.lhufriend.exception.ErrorCode;
import com.tonghoangvu.lhufriend.exception.ViewException;
import com.tonghoangvu.lhufriend.repository.CustomStudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final CustomStudentRepository customStudentRepository;

    public List<Student> getStudents(int page, int size) {
        if (size > Const.MAX_STUDENTS_PER_REQUEST.getIntValue())
            throw new ViewException(ErrorCode.BAD_REQUEST, "Request too much data");
        return customStudentRepository.getStudents(page, size);
    }

    public long getStudentCount() {
        return customStudentRepository.getStudentCount();
    }
}
