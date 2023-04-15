package com.iamcoda.springcoredemo.rest;

import com.iamcoda.springcoredemo.common.Coach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
    private Coach myCoach;

    /**
     * If we mixed @Primary and @Qualifier
     * Spring will do the high priority @Qualifier
     * Note : Can't set the multiple @Primary
     * @param theCoach
     */
    @Autowired
    public DemoController(@Qualifier("trackCoach") Coach theCoach){
        myCoach = theCoach;
    }

    @GetMapping("/dailyworkout")
    public String getDailyWorkout(){
        return myCoach.getDailyWorkout();
    }
}
