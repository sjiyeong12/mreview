package org.zerock.mreview.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UploadTestController {
    @RequestMapping({ "", "/" })
    public String index() {
        return "index";
    }

    @GetMapping("/uploadEx")
    public void uploadEx() {

    }
}
