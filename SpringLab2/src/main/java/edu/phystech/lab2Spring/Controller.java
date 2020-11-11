package edu.phystech.lab2Spring;


import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;

@org.springframework.stereotype.Controller
public class Controller {
    private AuditDataManager auditDataManager = new AuditDataManager();

    private static final String WRONG_PASSWORD = "wrong password";
    private static final String USER_DOESNT_EXIST = "user doesn't exist";
    private static final String SUCCESSFUL_LOGIN = "successful login";
    @GetMapping("/")
    public String defaultView(Model model) {
        return "login";
    }


    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("user") User user, Model model) {
        if(user.getName() == null) return "login";
        if (!AuthorisationDataManager.userIsExist(user)) {
            model.addAllAttributes(Map.of("isErrorAcquired", true,
                    "errorMessage", "This user doesn't exist!"));
            auditDataManager.addUserAttempt(user.getName(), USER_DOESNT_EXIST);
            return "login";
        }
        if (!AuthorisationDataManager.passwordIsCorrect(user)) {
            model.addAllAttributes(Map.of("isErrorAcquired", true,
                    "errorMessage", "Wrong password!"));
            auditDataManager.addUserAttempt(user.getName(), WRONG_PASSWORD);
            return "login";
        }
        auditDataManager.addUserAttempt(user.getName(), SUCCESSFUL_LOGIN);
        return "welcome";
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        return "registration";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute("user") User user, Model model) {
        if (AuthorisationDataManager.userIsExist(user)) {
            model.addAllAttributes(Map.of("isErrorAcquired", true,
                    "errorMessage", "This user already exist!"));
            return "registration";
        }
        if ("admin".equals(user.getName())) {
            model.addAllAttributes(Map.of("isErrorAcquired", true,
                    "errorMessage", "You can't create admin user!"));
            return "registration";
        }
        AuthorisationDataManager.addUser(user);
        return "login";
    }

    @GetMapping("/audit")
    public String audit(Model model){
        model.addAttribute("auditMap", auditDataManager.getAudit().toString());
        return "audit";
    }
}
