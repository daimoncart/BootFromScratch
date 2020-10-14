package art.daimonc.bean;

import art.daimonc.exception.UserNotFoundException;
import art.daimonc.service.UserDaoService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

@Getter @Setter @ToString @AllArgsConstructor
public class Post {

    private long id;
    private long userId;
    private String name;
    private String content;
    private Date timestamp;
}
