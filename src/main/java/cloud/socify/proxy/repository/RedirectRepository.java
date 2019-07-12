package cloud.socify.proxy.repository;

import cloud.socify.proxy.model.Redirect;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RedirectRepository extends JpaRepository<Redirect, Long> {

    Redirect getByLinkFrom(String linkFrom);
}
