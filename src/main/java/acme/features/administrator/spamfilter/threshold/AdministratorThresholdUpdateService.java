package acme.features.administrator.spamfilter.threshold;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.spamfilter.Threshold;
import acme.features.administrator.spamfilter.AdministratorSpamFilterRepository;
import acme.framework.components.Errors;
import acme.framework.components.HttpMethod;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.components.Response;
import acme.framework.entities.Administrator;
import acme.framework.helpers.PrincipalHelper;
import acme.framework.services.AbstractUpdateService;

@Service
public class AdministratorThresholdUpdateService implements AbstractUpdateService<Administrator, Threshold>{

	@Autowired
	protected AdministratorSpamFilterRepository repository;

	// AbstractUpdateService<Authenticated, Consumer> interface -----------------


	@Override
	public boolean authorise(final Request<Threshold> request) {
		assert request != null;

		return true;
	}

	@Override
	public void validate(final Request<Threshold> request, final Threshold entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;
	}

	@Override
	public void bind(final Request<Threshold> request, final Threshold entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Threshold> request, final Threshold entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "value");
	}

	@Override
	public Threshold findOne(final Request<Threshold> request) {
		assert request != null;
		

		return this.repository.getThreshold().stream().collect(Collectors.toList()).get(0);
	}

	@Override
	public void update(final Request<Threshold> request, final Threshold entity) {
		assert request != null;
		assert entity != null;

		this.repository.save(entity);
	}

	@Override
	public void onSuccess(final Request<Threshold> request, final Response<Threshold> response) {
		assert request != null;
		assert response != null;

		if (request.isMethod(HttpMethod.POST)) {
			PrincipalHelper.handleUpdate();
		}
	}

}
