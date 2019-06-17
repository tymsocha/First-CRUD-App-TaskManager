package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class MailCreatorService {

    @Autowired
    private AdminConfig adminConfig;

    @Autowired
    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;

    public String buildTrelloCardEmail(String message) {
        Context context = new Context();
        context.setVariable("Previev", "New Trello card was created");
        context.setVariable("message", message);
        context.setVariable("tasks_url", "https://trello.com/");
        context.setVariable("button", "Visit Website");
        context.setVariable("admin_name", adminConfig.getAdminName());
        context.setVariable("goodbye", "Thank you for using CRUD App");
        context.setVariable("company",
                adminConfig.getCompanyName() + ", " +
                "E-Mail: " + adminConfig.getCompanyMail() + ", " +
                "Company phone: " + adminConfig.getCompanyPhone() + ", " +
                "Owner: " + adminConfig.getCompanyOwnerName() + " " + adminConfig.getCompanyOwnerLastName());
        return templateEngine.process("mail/created-trello-card-mail", context);
    }
}
