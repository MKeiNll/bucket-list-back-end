package com.bucketlist.controller;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class EntryController {

    public String getHello() {
        return "hello";
    }
}
