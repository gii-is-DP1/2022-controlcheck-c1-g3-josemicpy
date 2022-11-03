package org.springframework.samples.petclinic.recoveryroom;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.samples.petclinic.model.NamedEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class RecoveryRoom extends NamedEntity {

	@NotNull
	@Min(value = 0)
	double size;
	@NotNull
	boolean secure;
	@Transient
	@ManyToOne(optional = false)
	RecoveryRoomType roomType;
}
