package edu.phystech.lab2Spring;


import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;

@org.springframework.stereotype.Controller
public class AuthorisationController {
    private AuditDataManager auditDataManager = new AuditDataManager();

    private static final String WRONG_PASSWORD = "wrong password";
    private static final String USER_DOESNT_EXIST = "user doesn't exist";
    private static final String SUCCESSFUL_LOGIN = "successful login";

    private static final String USER_DOESNT_EXIST_ERROR = "This user doesn't exist!";
    private static final String WRONG_PASSWORD_ERROR = "Wrong password!";
    private static final String USER_ALREADY_EXIST_ERROR = "This user already exist!";
    private static final String ADMIN_USER_ERROR = "You can't create admin user!";

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
        if (user.getName() == null) return "login";
        if (!AuthorisationDataManager.userIsExist(user)) {
            AddErrorMessageToAttributes(model, USER_DOESNT_EXIST_ERROR);
            auditDataManager.addUserAttempt(user.getName(), USER_DOESNT_EXIST);
            return "login";
        }
        if (!AuthorisationDataManager.passwordIsCorrect(user)) {
            AddErrorMessageToAttributes(model, WRONG_PASSWORD_ERROR);
            auditDataManager.addUserAttempt(user.getName(), WRONG_PASSWORD);
            return "login";
        }
        auditDataManager.addUserAttempt(user.getName(), SUCCESSFUL_LOGIN);
        return "welcome";
    }

    private void AddErrorMessageToAttributes(Model model, String s) {
        model.addAllAttributes(Map.of("isErrorAcquired", true,
                "errorMessage", s));
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        return "registration";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute("user") User user, Model model) {
        if (AuthorisationDataManager.userIsExist(user)) {
            AddErrorMessageToAttributes(model, USER_ALREADY_EXIST_ERROR);
            return "registration";
        }
        if ("admin".equals(user.getName())) {
            AddErrorMessageToAttributes(model, ADMIN_USER_ERROR);
            return "registration";
        }
        AuthorisationDataManager.addUser(user);
        return "login";
    }

    @GetMapping("/audit")
    public String audit(Model model) {
        model.addAttribute("auditMap", auditDataManager.getAudit().toString());
        return "audit";
    }
}
