package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.config.MailAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.ArrayList;
import java.util.List;

@Service
public class MailCreatorService {

    @Autowired
    private AdminConfig adminConfig;

    @Autowired
    private MailAdmin mailAdmin;

    @Autowired
    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;

    public String buildTrelloCardEmail(String message) {
        List<String> functionality = new ArrayList<>();
        functionality.add("You can manage your task");
        functionality.add("Provides connection with trello account");
        functionality.add("Application allows sending tasks to trello");

        Context context = new Context();
        context.setVariable("Previev", "New Trello card was created");
        context.setVariable("message", message);
        context.setVariable("trello_url", "https://trello.com/");
        context.setVariable("button", "Visit Website");
        context.setVariable("admin_name", adminConfig.getAdminName());
        context.setVariable("goodbye", "Thank you for using CRUD App");
        context.setVariable("company",
                adminConfig.getCompanyName() + ", " +
                "E-Mail: " + adminConfig.getCompanyMail() + ", " +
                "Company phone: " + adminConfig.getCompanyPhone() + ", " +
                "Owner: " + adminConfig.getCompanyOwnerName() + " " + adminConfig.getCompanyOwnerLastName());
        context.setVariable("show_button", false);
        context.setVariable("isFriend", true);
        context.setVariable("adminConfig", adminConfig);
        context.setVariable("appFunctionality", functionality);
        return templateEngine.process("mail/created-trello-card-mail", context);
    }

    public String buildScheduledEmail(String message) {
        List<String> image = new ArrayList<>();
        image.add("      ________________.___     .______");
        image.add("     /                | /   \\    |   _  \\");
        image.add("    |   (-----|  |----`/  ^  \\   |  |_)  |");
        image.add("     \\   \\    |  |    /  /_\\  \\  |      /");
        image.add(".-----)   |   |  |   /  _____  \\ |  |\\  \\-------.");
        image.add("|________/    |__|  /__/     \\__\\| _| `.________|");
        image.add("____    __    ____  ___     .______    ________.");
        image.add("\\   \\  /  \\  /   / /   \\    |   _  \\  /        |");
        image.add("  \\   \\/    \\/   / /  ^  \\   |  |_)  ||   (-----`");
        image.add("  \\            / /  /_\\  \\  |      /  \\   \\");
        image.add("   \\    /\\    / /  _____  \\ |  |\\  \\---)   |");
        image.add("    \\__/  \\__/ /__/     \\__\\|__| `._______/");

        Context context = new Context();
        context.setVariable("Hello", "Hello There Obi Wan Kenobi");
        context.setVariable("Information", message);
        context.setVariable("trello_info", "Check how many tasks you have currently in Trello");
        context.setVariable("tasks_url", "E:\\Dokumenty\\Kodilla\\Kodilla-Projects\\tasks\\src\\html_and_css\\localhost\\index.html");
        context.setVariable("trello_url", "https://trello.com/");
        context.setVariable("buttonTask", "Check CRUD");
        context.setVariable("buttonTrello", "Check Trello");
        context.setVariable("goodbyeCrud", "May the Force be with you");
        context.setVariable("crudCompany", mailAdmin.getCompanyName() + ", " +
                "E-Mail: " + mailAdmin.getCompanyMail() + ", " +
                "Company phone: " + mailAdmin.getCompanyPhone() + ", " +
                "Owner: " + mailAdmin.getCompanyOwnerName() + " " + mailAdmin.getCompanyOwnerLastName());
        context.setVariable("image", image);
        return templateEngine.process("mail/scheduled-email", context);
    }
}
