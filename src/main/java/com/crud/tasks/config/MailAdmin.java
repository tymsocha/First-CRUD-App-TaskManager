package com.crud.tasks.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class MailAdmin {
    @Value("${admin.mail}")
    private String adminMail;

    @Value("${admin.name}")
    private String adminName;

    private String companyName = "CRUD";

    private String companyMail = "crud@crud.com";

    private String companyPhone = "123456789";

    private String companyOwnerName = "Tymek";

    private String companyOwnerLastName = "Socha";
}


