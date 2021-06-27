package acme.features.anonymous.shout;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.shouts.Domemi;
import acme.entities.shouts.Shout;
import acme.features.administrator.spamfilter.spamword.AdministratorSpamwordListService;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractCreateService;

@Service
public class AnonymousShoutCreateService implements AbstractCreateService <Anonymous,Shout> {
	
	@Autowired
	protected AnonymousShoutRepository repository;

	@Autowired
	protected AdministratorSpamwordListService spamService;
	@Autowired
	protected AnonymousSheetRepository sheetRepo;
	
	@Override
	public boolean authorise(final Request<Shout> request) {
		assert request != null;
		return true;
	}
	
	@Override
	public void bind (final Request<Shout> request, final Shout entity, final Errors errors) {
		assert request!=null;
		assert entity!=null;
		assert errors!=null;
		request.bind(entity, errors);
	}
	
	@Override
	public void unbind (final Request<Shout> request, final Shout entity, final Model model) {
		assert request!=null;
		assert entity!=null;
		assert model!=null;
		model.setAttribute("domemi.stamp", entity.getDomemi().getStamp());
		request.unbind(entity, model, "author", "text", "info", "domemi");
	}
	
	@Override
	public Shout instantiate(final Request<Shout> request) {
		assert request != null;

		Shout result;
		Date moment;
		final Domemi domemi;
		
		moment = new Date(System.currentTimeMillis()-1);
		
		domemi = new Domemi();
		final LocalDate hoy=LocalDate.now();
        final String  anyo= hoy.getYear() +"";
        final String yy=anyo.substring(2, 4);
        String mm = hoy.getMonthValue() + "";
        if(mm.length()==1) mm = "0"+mm;
        String dd = hoy.getDayOfMonth() + "";
        if(dd.length()==1) dd = "0"+dd;
        final Integer  i = this.sheetRepo.countEntities() + 1;
        final String formatI = String.format("%%0%dd", 5);
        final String formattedI = String.format(formatI, i);
        
        
        
        final String pattern = yy + ":" + "DM:" + mm + ":"+ formattedI + ":" + dd;
		domemi.setStamp(pattern);
		
		result = new Shout();
		result.setMoment(moment);
		result.setDomemi(domemi);


		return result;
	}

	@Override
	public void validate(final Request<Shout> request, final Shout entity, final Errors errors) {
		assert request!=null;
		assert entity!=null;
		assert errors!=null;
		
		
		final Boolean b = this.spamService.filtro(entity);
		errors.state(request, b, "text", "anonymous.shout.error.text");		
		
		
		if(entity.getDomemi().getDeadline() != null) {
			Calendar calendar;
	        calendar = Calendar.getInstance();
	        calendar.setTime(new Date(System.currentTimeMillis() - 1));
	        calendar.add(Calendar.DAY_OF_MONTH, 7);
	        Date hoy;
	        hoy = calendar.getTime();
			final Boolean b2 = entity.getDomemi().getDeadline().after(hoy);
			errors.state(request, b2, "domemi.deadline", "anonymous.shout.error.deadline");
		}
		
		if(entity.getDomemi().getBudget() != null) {
			final String cur = entity.getDomemi().getBudget().getCurrency();
			final Boolean b3 = cur.equals("EUR") || cur.equals("USD");
			errors.state(request, b3, "domemi.budget", "anonymous.shout.error.budget");
		}
		
	}

	
	@Override
	public void create(final Request<Shout> request, final Shout entity) {
		assert request!=null;
		assert entity!=null;

		Date moment;
	
		this.sheetRepo.save(entity.getDomemi());
		
		moment = new Date(System.currentTimeMillis()-1);
		entity.setMoment(moment);
		this.repository.save(entity);
	}
	
	
	
}
