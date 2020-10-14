package art.daimonc.service;

import art.daimonc.bean.Post;
import art.daimonc.bean.User;
import art.daimonc.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class PostDaoService {

    private static int count = 5;
    private static List<Post> posts = new ArrayList();

    static {
        posts.add(new Post(1, 1, "Nice teeth", "They were really nice teeth you know", new Date()));
        posts.add(new Post(2, 1, "Bonez", "I wonder, do you?", new Date()));
        posts.add(new Post(3, 2, "Mouthpiece", "If you put your pick in your mouth, is it a mouthpiece", new Date()));
        posts.add(new Post(4, 3, "Relativity", "This is the subject I'like to talk about some day", new Date()));
        posts.add(new Post(5, 4, "Morning", "Great feeling waking up, hope the day will be even better", new Date()));
    }

    public List<Post> getPosts(long userId){
        List<Post> returnedList = posts.stream()
                .filter(post -> post.getUserId()==userId)
                .collect(Collectors.toList());
        return returnedList;
    }

    public Post findPostById(long id, long userId){
        Post foundPost = posts.stream()
                .filter(post -> (
                        id == post.getId() &&
                        userId == post.getUserId()))
                .findAny()
                .orElse(null);
        return foundPost;
    }

    public Post save(Post post){
        post.setId(++count);
        posts.add(post);
        return post;
    }

}
