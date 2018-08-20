package edm.infrastructure.user;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import edm.model.user.User;

@RepositoryRestResource(collectionResourceRel = "users", path = "users")
@CrossOrigin(origins="http://localhost:8080/edm", maxAge=36000)
public interface UserRepository extends CrudRepository<User, Long> {
	
	List<User> findByUsername(@Param("username") String username);

}
