package acme.features.anonymous.shout;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.framework.repositories.AbstractRepository;

@Repository
public interface AnonymousSheetRepository extends AbstractRepository{

	
	@Query("select count(*) from Shout sh")
    Integer countEntities();
}
