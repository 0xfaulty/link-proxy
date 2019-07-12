package cloud.socify.proxy.repository;

import cloud.socify.proxy.model.RedirectHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RedirectHistoryRepository extends JpaRepository<RedirectHistory, Long> {

    RedirectHistory getById(long id);
}
