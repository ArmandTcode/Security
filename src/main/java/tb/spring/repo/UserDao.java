package tb.spring.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tb.spring.db.DAOUser;


@Repository
public interface UserDao extends CrudRepository<DAOUser, Integer> {
    DAOUser findByUsername(String username);
}
