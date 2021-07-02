package com.tonghoangvu.lhufriend.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
@Getter
@Setter
public class User {
    private String id;
    private String email;
    private String fullName;
    private String pictureUrl;
    private String locale;
}
