package iamcoda.example.springwebservicedemo.rest;

import iamcoda.example.springwebservicedemo.dao.UserDaoService;
import iamcoda.example.springwebservicedemo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class UserRestController {
    private UserDaoService serviceDao;

    @Autowired
    public UserRestController(UserDaoService serviceDao){
        this.serviceDao = serviceDao;
    }

    @GetMapping("/users")
    public List<User> retrieveAllUsers(){
        return serviceDao.findAll();
    }

    @GetMapping("/users/{id}")
    public  User retrieveUser(@PathVariable int id){
        return serviceDao.findById(id);
    }

    @PostMapping("/users")
    public ResponseEntity<User> save(@RequestBody User user){
        User savedUser = serviceDao.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(savedUser.getId())
                        .toUri();
        return ResponseEntity.created(location).build();
    }
}
