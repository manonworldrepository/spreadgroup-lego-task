package net.sprd.homework.resources;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexResource {

    @Value("${app.name}")
    private String name;

    @GetMapping(value = "/", produces = MediaType.TEXT_PLAIN_VALUE)
    public String getIndex() {
        return String.format("Hello from %s!", name);
    }
}
