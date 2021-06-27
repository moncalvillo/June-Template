package acme.features.administrator.shout;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.dashboard.Dashboard;
import acme.framework.components.Model;

@Service
public class AdministratorShoutDashboardService {

	@Autowired
	AdministratorShoutDashboardRepository shoutRepo;
	
	public void dashboardShout(final Model model) {
		
		if(!this.shoutRepo.findAllShouts().isEmpty()) {
			final List<Double> avgCurrencies = this.shoutRepo.avgCurrency();
			final Integer total = this.shoutRepo.countShouts();
			final Double ratioTrue = this.shoutRepo.trueShouts() / (double) total;
			final Double ratioEUR = this.shoutRepo.countEURShouts() / (double) total;
			model.setAttribute("totalShout", this.shoutRepo.countShouts());
			model.setAttribute("trueShouts", ratioTrue);
			model.setAttribute("ratioEUR", ratioEUR);
			model.setAttribute("avgCurrencyEUR", avgCurrencies.get(0));
			model.setAttribute("avgCurrencyUSD", avgCurrencies.get(1));
			final Double eurDeviation = Dashboard.deviation(this.shoutRepo.amountsEUR());
			final Double usdDeviation = Dashboard.deviation(this.shoutRepo.amountsUSD());
			
			model.setAttribute("eurDev", eurDeviation);
			model.setAttribute("usdDev", usdDeviation);
		}else {
			model.setAttribute("totalShout", 0);
			model.setAttribute("trueShouts", 0.00);
			model.setAttribute("ratioEUR", 0.00);
			model.setAttribute("avgCurrencyEUR", 0.00);
			model.setAttribute("avgCurrencyUSD", 0.00);
			model.setAttribute("eurDev", 0.00);
			model.setAttribute("usdDev", 0.00);
		}
		
	}
}
