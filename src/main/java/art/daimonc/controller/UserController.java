package art.daimonc.controller;

import art.daimonc.bean.User;
import art.daimonc.exception.UserNotFoundException;
import art.daimonc.service.UserDaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Iterator;
import java.util.List;


@RestController
public class UserController {
    @Autowired
    private UserDaoService userDaoService;

    @GetMapping(path="/users")
    public List<User> getUsers(){
        return userDaoService.findAll();
    }

    @GetMapping(path="/users/{id}")
    public User getUser(@PathVariable long id){
        User gottenUser = userDaoService.findOne(id);
        if (gottenUser==null) {
            throw new UserNotFoundException("id=" + id);
        }

        //Resource<User> resource = new Resource<User>(user);
//        EntityModel<User> resource = EntityModel.of(gottenUser);
        return gottenUser;
    }

    @PostMapping(path="/users")
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user){
        User savedUser = userDaoService.save(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping(path="/users/{id}")
    public boolean deleteUser(@PathVariable long id){
        User deletedUser = userDaoService.deleteById(id);
        if (deletedUser == null) {
            throw new UserNotFoundException("id = " + id);
        }
        return true;
    }

}
