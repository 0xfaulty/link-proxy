package cloud.socify.proxy.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "redirect")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Redirect {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String linkFrom;
    private String linkTo;
}
