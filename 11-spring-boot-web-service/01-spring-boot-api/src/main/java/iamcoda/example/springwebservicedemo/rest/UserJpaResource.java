package iamcoda.example.springwebservicedemo.rest;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import iamcoda.example.springwebservicedemo.dao.UserDaoService;
import iamcoda.example.springwebservicedemo.entity.Post;
import iamcoda.example.springwebservicedemo.entity.User;
import iamcoda.example.springwebservicedemo.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class UserJpaResource {
    private UserDaoService serviceDao;

    private UserRepository repository;

    @Autowired
    public UserJpaResource(UserDaoService serviceDao, UserRepository repository){
        this.repository = repository;
        this.serviceDao = serviceDao;
    }

    @GetMapping("/jpa/filtering/{id}")
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

    @GetMapping("/jpa/filtering-list")
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


    @GetMapping("/jpa/hide-field")
    public EntityModel<User> retrieveUserWithoutPassword(@PathVariable int id){
        Optional<User> user=  repository.findById(id);
        if(user.isEmpty()){
            throw new UserNotFoundException("id" + id);
        }
        EntityModel<User> entityModel = EntityModel.of(user.get());

        WebMvcLinkBuilder builder = linkTo(methodOn(this.getClass()).retrieveAllUsers());
        entityModel.add(builder.withRel("all-users"));
        return entityModel;
    }

    @GetMapping("/jpa/users")
    public List<User> retrieveAllUsers(){
        return repository.findAll();
//        return serviceDao.findAll();
    }

    @GetMapping("/jpa/users/{id}")
    public EntityModel<User> retrieveUser(@PathVariable int id){
        Optional<User> user=  repository.findById(id);
        if(user.isEmpty()){
            throw new UserNotFoundException("id" + id);
        }
        EntityModel<User> entityModel = EntityModel.of(user.get());

        WebMvcLinkBuilder builder = linkTo(methodOn(this.getClass()).retrieveAllUsers());
        entityModel.add(builder.withRel("all-users"));
        return entityModel;
    }

    @PostMapping("/jpa/users")
    public ResponseEntity<User> save(@Valid @RequestBody User user){
        User savedUser = repository.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(savedUser.getId())
                        .toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/jpa/users/{id}")
    public  String deleteUser( @PathVariable int id){
        repository.deleteById(id);
//         serviceDao.deleteById(id);
        return "Successfully deleted";
    }

    @GetMapping("/jpa/users/{id}/posts")
    public List<Post> retrievePostsForUser(@PathVariable int id){
        Optional<User> user=  repository.findById(id);
        if(user.isEmpty()){
            throw new UserNotFoundException("id" + id);
        }
        EntityModel<User> entityModel = EntityModel.of(user.get());

        return user.get().getPosts();
    }
}
