package cloud.socify.proxy.model;

import java.time.ZonedDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "redirect_history")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RedirectHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long redirectId;
    private String linkFrom;
    private String linkTo;
    private String ip;
    private String headers;
    private String cookies;
    private String body;
    private ZonedDateTime time;
}
