package art.daimonc.controller;

import art.daimonc.bean.Post;
import art.daimonc.bean.User;
import art.daimonc.exception.UserNotFoundException;
import art.daimonc.service.PostDaoService;
import art.daimonc.service.UserDaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class PostController {

    @Autowired
    private PostDaoService postDaoService;

    @Autowired
    private UserDaoService userDaoService;

    @GetMapping(path="/users/{userId}/posts")
    public List<Post> findAllByUserId(@PathVariable long userId){
        return postDaoService.getPosts(userId);
    }

    @GetMapping(path="/users/{userId}/posts/{id}")
    public Post findById(@PathVariable long userId, @PathVariable long id){
        return postDaoService.findPostById(id, userId);
    }

    @PostMapping(path="/users/{userId}/posts")
    public ResponseEntity<Object> createPost(@PathVariable long userId, @RequestBody Post post){
        if (userId != post.getUserId() || userDaoService.findOne(userId) == null){
            throw new UserNotFoundException("id1 = " + userId + "; id2 = " +post.getUserId());
        }
        Post savedPost = postDaoService.save(post);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/"+ userId)
                .buildAndExpand(savedPost.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

}
