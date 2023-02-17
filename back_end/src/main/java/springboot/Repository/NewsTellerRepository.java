package springboot.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springboot.Entity.NewsTeller;

public interface NewsTellerRepository extends JpaRepository< NewsTeller, Long> {

}
