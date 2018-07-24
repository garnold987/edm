package edm.infrastructure;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import edm.model.Account;

@RepositoryRestResource(collectionResourceRel = "account", path = "account")
public interface AccountRepository extends CrudRepository<Account, Long>{

	List<Account> findByName(@Param("name") String name);
}
