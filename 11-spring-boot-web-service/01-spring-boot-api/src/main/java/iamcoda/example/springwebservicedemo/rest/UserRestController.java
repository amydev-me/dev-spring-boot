package iamcoda.example.springwebservicedemo.rest;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import iamcoda.example.springwebservicedemo.dao.UserDaoService;
import iamcoda.example.springwebservicedemo.entity.User;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import java.net.URI;
import java.util.List;

@RestController
public class UserRestController {
    private UserDaoService serviceDao;

    @Autowired
    public UserRestController(UserDaoService serviceDao){
        this.serviceDao = serviceDao;
    }

    @GetMapping("/filtering/{id}")
    public MappingJacksonValue filtering(@PathVariable int id){
        User user =  serviceDao.findById(id);
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(user);
        SimpleBeanPropertyFilter filter =
                SimpleBeanPropertyFilter.filterOutAllExcept("user_name", "birth_date");

        FilterProvider filters =
                new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);

        mappingJacksonValue.setFilters(filters);
        return mappingJacksonValue;
    }

    @GetMapping("/filtering-list")
    public MappingJacksonValue filteringList(){
        List<User> users = serviceDao.findAll();
        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(users);

        SimpleBeanPropertyFilter filter =
                SimpleBeanPropertyFilter.filterOutAllExcept("user_name", "birth_date");

        FilterProvider filters =
                new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);

        mappingJacksonValue.setFilters(filters);
        return mappingJacksonValue;
    }


    @GetMapping("/hide-field")
    public EntityModel<User> retrieveUserWithoutPassword(@PathVariable int id){
        User user =  serviceDao.findById(id);
        if(user == null){
            throw new UserNotFoundException("id" + id);
        }
        EntityModel<User> entityModel = EntityModel.of(user);

        WebMvcLinkBuilder builder = linkTo(methodOn(this.getClass()).retrieveAllUsers());
        entityModel.add(builder.withRel("all-users"));
        return entityModel;
    }

    @GetMapping("/users")
    public List<User> retrieveAllUsers(){
        return serviceDao.findAll();
    }

    @GetMapping("/users/{id}")
    public EntityModel<User> retrieveUser(@PathVariable int id){
        User user =  serviceDao.findById(id);
        if(user == null){
            throw new UserNotFoundException("id" + id);
        }
        EntityModel<User> entityModel = EntityModel.of(user);

        WebMvcLinkBuilder builder = linkTo(methodOn(this.getClass()).retrieveAllUsers());
        entityModel.add(builder.withRel("all-users"));
        return entityModel;
    }

    @PostMapping("/users")
    public ResponseEntity<User> save(@Valid @RequestBody User user){
        User savedUser = serviceDao.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(savedUser.getId())
                        .toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/users/{id}")
    public  String deleteUser( @PathVariable int id){
         serviceDao.deleteById(id);
        return "Successfully deleted";
    }
}
