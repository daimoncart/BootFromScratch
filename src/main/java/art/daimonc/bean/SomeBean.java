package art.daimonc.bean;

import com.fasterxml.jackson.annotation.JsonFilter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import net.minidev.json.annotate.JsonIgnore;

@AllArgsConstructor @Getter @Setter
@JsonFilter("NewBeanFilter")
public class SomeBean {
    private String field1;
    @JsonIgnore
    private String field2;
    private String field3;
}
