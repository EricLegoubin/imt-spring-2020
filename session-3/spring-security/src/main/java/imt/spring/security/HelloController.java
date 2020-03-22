package imt.spring.security;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping
    public String greeting() {
        return "Welcome !";
    }

    @GetMapping("/user")
    public String user() {
        return "Welcome, user !";
    }

    @GetMapping("/admin")
    public String admin() {
        return "Welcome, admin !";
    }
}
