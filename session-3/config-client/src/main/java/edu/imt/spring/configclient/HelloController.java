package edu.imt.spring.configclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class HelloController {

    @Value("${my.greeting}")
    private String greetingMessage;

    @Value("${params.list}")
    private List<String> paramsList;

    @Value("${params.missing}")
    private String paramsMissing;

    @Value("#{${params.dict}}")
    private Map<String, String> paramsDict;

    @Autowired
    private ConnectionSettings connectionSettings;

    @GetMapping
    public String greeting() {
        return "<ul>"
                + "<li> message: " + greetingMessage +"</li>"
                + "<li> list:" + paramsList + "</li>"
                + "<li> missing:" + paramsMissing + "</li>"
                + "<li> dict:" + paramsDict + "</li>"
                + "<li> object:" + connectionSettings + "</li>"
                + "</ul>";
    }
}
