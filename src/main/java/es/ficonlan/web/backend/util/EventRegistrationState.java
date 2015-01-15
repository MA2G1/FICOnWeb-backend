package es.ficonlan.web.backend.util;

import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import es.ficonlan.web.backend.jersey.util.JsonRegistrationStateDeserializer;
import es.ficonlan.web.backend.jersey.util.JsonRegistrationStateSerializer;
import es.ficonlan.web.backend.model.registration.Registration.RegistrationState;

public class EventRegistrationState {

	private boolean open;
	
	private RegistrationState state;
	
	private int place;

	public EventRegistrationState(boolean open, RegistrationState state, int place) {
		this.open = open;
		this.state = state;
		this.place = place;
	}

	public boolean isOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}

	@JsonDeserialize(using = JsonRegistrationStateDeserializer.class)
	@JsonSerialize(using = JsonRegistrationStateSerializer.class)
	public RegistrationState getState() {
		return state;
	}

	public void setState(RegistrationState state) {
		this.state = state;
	}

	public int getPlace() {
		return place;
	}

	public void setPlace(int place) {
		this.place = place;
	}
	
	@Override
	public String toString() {
		String o = "Closed";
		if(this.open) o = "Open";
		return o + " - " + Integer.toString(this.place) + " - " + this.state;
	}
}