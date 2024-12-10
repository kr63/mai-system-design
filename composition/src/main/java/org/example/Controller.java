package org.example;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class Controller {

    private final CompositionService service;

    @GetMapping
    public CompositionResponse getUser(CompositionRequest request) {
        return service.getUser(request);
    }

}
