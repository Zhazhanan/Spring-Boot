package com.example.distributedlock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class MicroController {
    @Autowired
    private MicroService microService;

    @GetMapping("/lockByZK/{path}")
    public Boolean getLock1(@PathVariable String path) {
        return microService.getLockByZK(path);
    }

    @GetMapping("/lockByCurator/{path}")
    public Boolean getLock2(@PathVariable String path) {
        return microService.getLockByCurator(path);
    }
}
