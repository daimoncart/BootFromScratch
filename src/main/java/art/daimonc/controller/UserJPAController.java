package art.daimonc.controller;

import art.daimonc.bean.User;
import art.daimonc.exception.UserNotFoundException;
import art.daimonc.repository.UserRepository;
import art.daimonc.service.UserDaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;


@RestController
public class UserJPAController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping(path="/jpa/users")
    public List<User> getUsers(){
        return userRepository.findAll();
    }

    @GetMapping(path="/jpa/users/{id}")
    public User getUser(@PathVariable long id){
        Optional<User> gottenUser = userRepository.findById(id);
        if (!gottenUser.isPresent()) {
            throw new UserNotFoundException("id=" + id);
        }

        //Resource<User> resource = new Resource<User>(user);
//        EntityModel<User> resource = EntityModel.of(gottenUser);
        return gottenUser.get();
    }

    @PostMapping(path="/jpa/users")
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user){
        User savedUser = userRepository.save(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping(path="/jpa/users/{id}")
    public void deleteUser(@PathVariable long id){
        userRepository.deleteById(id);
    }

}
