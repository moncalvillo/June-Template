package acme.entities.shouts;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import acme.framework.datatypes.Money;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Domemi extends DomainEntity{

	protected static final long serialVersionUID = 1L;
	
	@Column(unique=true)
	@NotBlank
	@Pattern(regexp = "^\\d{2}:\\w{2}:\\d{2}:\\d{5}:\\d{2}$")
	protected String stamp;
	
	
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	protected Date deadline;
	
	@NotNull
	protected Money budget;
	
	@NotNull
	protected Boolean important;

	@Override
	public String toString() {
		return "Stamp:" + this.stamp + "; Deadline:" + this.deadline + "; Budget:" + this.budget + "; Important:" + this.important + "";
	}
}

