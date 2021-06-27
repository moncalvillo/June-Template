package acme.features.administrator.shout;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.Query;

import acme.entities.shouts.Shout;
import acme.framework.repositories.AbstractRepository;

public interface AdministratorShoutDashboardRepository extends AbstractRepository {

	@Query("SELECT AVG(sh.domemi.budget.amount) FROM Shout sh GROUP BY sh.domemi.budget.currency")
	List<Double> avgCurrency();
	
	@Query("SELECT COUNT(sh) FROM Shout sh")
	Integer countShouts();
	
	@Query("SELECT COUNT(sh) FROM Shout sh WHERE sh.domemi.important = true")
	Integer trueShouts();
	
	@Query("SELECT COUNT(sh) FROM Shout sh WHERE sh.domemi.budget.amount = 0.00")
	Integer countEURShouts();
	
	@Query("SELECT sh.domemi.budget.amount FROM Shout sh WHERE sh.domemi.budget.currency = 'EUR'")
	List<Double> amountsEUR();
	
	@Query("SELECT sh.domemi.budget.amount FROM Shout sh WHERE sh.domemi.budget.currency = 'USD'")
	List<Double> amountsUSD();
	
	@Query("select sh from Shout sh")
	Collection<Shout> findAllShouts();
}
