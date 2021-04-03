package com.jules.cyberfood.jpa;

import com.jules.cyberfood.CyberfoodApplication;
import com.jules.cyberfood.domain.model.State;
import com.jules.cyberfood.domain.repository.StateRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import java.util.List;

public class ConsultStateMain {

    public static void main(String[] args) {

        ApplicationContext applicationContext = new SpringApplicationBuilder(CyberfoodApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        StateRepository consultState = applicationContext.getBean(StateRepository.class);
        List<State> states = consultState.allStates();

        for (State state : states){
            System.out.println(state.getName());
        }
    }
}
