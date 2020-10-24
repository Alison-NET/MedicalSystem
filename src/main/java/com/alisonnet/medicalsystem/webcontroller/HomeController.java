package com.alisonnet.medicalsystem.webcontroller;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@NoArgsConstructor
public class HomeController {

    @GetMapping("/index")
    public String goToIndex(){
        return "index";
    }
}
