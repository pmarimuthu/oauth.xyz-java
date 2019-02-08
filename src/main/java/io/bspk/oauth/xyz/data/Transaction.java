package io.bspk.oauth.xyz.data;

import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import lombok.Data;
import lombok.NonNull;
import lombok.experimental.Accessors;

/**
 * @author jricher
 *
 */
@Data
@Accessors(chain = true)
public class Transaction {

	public enum State {

		NEW,		// newly created transaction, nothing's been done to it yet
		ISSUED,		// an access token has been issued
		AUTHORIZED,	// the user has authorized but a token has not been issued yet
		WAITING;	// we are waiting for the user

		@JsonCreator
		public static State fromJson(String key) {
			return key == null ? null :
				valueOf(key.toUpperCase());
		}

		@JsonValue
		public String toJson() {
			return name().toLowerCase();
		}

	}

	private @Id String id;
	private Client client;
	private User user;
	private Interact interact;
	private Resource resource;
	private @NonNull HandleSet handles = new HandleSet();
	private Handle accessToken;
	private @NonNull State state = State.NEW;


}
