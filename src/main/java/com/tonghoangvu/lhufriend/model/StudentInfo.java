package com.tonghoangvu.lhufriend.model;

import com.tonghoangvu.lhufriend.entity.Student;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class StudentInfo {
    private String studentId;
    private String fullName;
    private String birthday;
    private String gender;
    private String placeOfBirth;
    private String ethnic;
    private String nationality;
    private String classId;
    private String image;
    private String avatar;
    private String userName;
    private String email;
    private String phone;
    private String groupName;
    private String facebook;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public StudentInfo(Student student) {
        studentId = student.getStudentId();
        fullName = student.getFullName();
        birthday = student.getBirthday();
        gender = student.getGender();
        placeOfBirth = student.getPlaceOfBirth();
        ethnic = student.getEthnic();
        nationality = student.getNationality();
        classId = student.getClassId();
        image = student.getImage();
        avatar = student.getAvatar();
        userName = student.getUserName();
        email = student.getEmail();
        phone = student.getPhone();
        groupName = student.getGroupName();
        facebook = student.getFacebook();
        createdAt = student.getCreatedAt();
        updatedAt = student.getUpdatedAt();
    }
}
