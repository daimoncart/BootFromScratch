package art.daimonc.service;

import art.daimonc.bean.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Component
public class UserDaoService {
    private static int userCount = 4;
    private static List<User> users = new ArrayList<>();

    static {
        users.add(new User(1, "Adam", new Date()));
        users.add(new User(2, "Eve", new Date()));
        users.add(new User(3, "Chef", new Date()));
        users.add(new User(4, "Mr.Hamster", new Date()));
    }

    public List<User> findAll(){
        return users;
    }

    public User save(User user){
        user.setId(++userCount);
        users.add(user);
        return user;
    }

    public User findOne(long id){
        User foundUser = users.stream()
                .filter(user -> id == user.getId())
                .findAny()
                .orElse(null);
        return foundUser;
    }

    public User deleteById(long id) {
        Iterator<User> iterator = users.iterator();
        while (iterator.hasNext()){
            User user = iterator.next();
            if (user.getId() == id){
                iterator.remove();
                return user;
            }
        }
        return null;
    }
}
