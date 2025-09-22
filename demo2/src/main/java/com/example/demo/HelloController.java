package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/list")
public class HelloController {
    private List<String> list = new ArrayList<>(List.of(
            "Linh", "Linh2", "Linh3", "Linh4", "Linh5"
    ));

    @GetMapping
    public List<String> getList() {
        return list;
    }
}
