package art.daimonc.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;

@Getter @Setter @ToString @AllArgsConstructor @NoArgsConstructor
@ApiModel(description = "All details concerning user.")
@Entity
public class User {
    @Id
    @GeneratedValue
    private long id;
    @Size(min=2, message = "Name should have at least 2 chars")
    @ApiModelProperty(notes = "Name can't be a single char or less")
    private String name;

    @Past
    @ApiModelProperty(notes="Birth date cannot be in the future")
    private Date birthdate;

}
