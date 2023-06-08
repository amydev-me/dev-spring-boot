package iamcoda.example.springwebservicedemo.rest;


import iamcoda.example.springwebservicedemo.entity.Name;
import iamcoda.example.springwebservicedemo.entity.PersonV1;
import iamcoda.example.springwebservicedemo.entity.PersonV2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersioningPersonController {

    @GetMapping("/v1/person")
    public PersonV1 getFirstVersionOfPerson(){
        return new PersonV1("Bob Charlie");
    }

    @GetMapping("/v2/person")
    public PersonV2 getSecondVersionOfPerson(){
        return new PersonV2(new Name("BOB", "Charlie"));
    }
}
