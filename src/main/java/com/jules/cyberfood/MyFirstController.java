package com.jules.cyberfood;

import com.jules.cyberfood.di.model.Client;
import com.jules.cyberfood.di.service.ClientActivationService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MyFirstController {

    private ClientActivationService clientActivationService;

    public MyFirstController(ClientActivationService clientActivationService){
        this.clientActivationService = clientActivationService;
    }

    @GetMapping("/hello")
    @ResponseBody
    public String hello(){
        Client joao = new Client("João", "joao@xyz.com", "34999998888");
        clientActivationService.activate(joao);
        return "Hello!";
    }

}
