package art.daimonc.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class HelloWorldBean {
    private String message;
    public HelloWorldBean(String message) {
        this.message = message;
    }
}
