package com.modelcontroller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PingPongController {
    record PingPong(String message){}

    @GetMapping("/ping")
    public PingPong ping(){
        return new PingPong("pong");



    }


}
