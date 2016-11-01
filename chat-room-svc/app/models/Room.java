/*
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package models;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import exceptions.LoginException;

/**
 * The Class Room.
 *
 * @author pflima
 * @since 03/07/2016
 */
public class Room {

	/** The token2activities. */
	protected final ConcurrentMap<String, Activity> token2activities = new ConcurrentHashMap<>();

	/** The token2users. */
	protected final ConcurrentMap<String, Profile> token2users = new ConcurrentHashMap<String, Profile>();

	/** The max time without interaction. */
	protected final long MAX_TIME_WITHOUT_INTERACTION = 30000;

	private Usher usher;

	public Room() {

		usher = new Usher();
		usher.start();
	}

	/**
	 * Account is available.
	 *
	 * @param profile
	 *            the profile
	 * @return true, if successful
	 */
	public boolean accountIsAvailable(Profile profile) {

		return token2users.values().stream().filter(p -> p.getEmail().equals(profile.getEmail())).collect(Collectors.toList()).isEmpty();
	}

	/**
	 * Checks if is valid token.
	 *
	 * @param token
	 *            the token
	 * @return true, if is valid token
	 */
	public boolean isValidToken(String token) {

		return token2users.containsKey(token);
	}

	/**
	 * Kick.
	 *
	 * @param token
	 *            the token
	 */
	public void kick(String token) {

		Profile profile = token2users.remove(token);
		token2activities.remove(token);
		notify(new Event(String.format("%s saiu do chat.", profile.getNickname())));
	}

	/**
	 * List events.
	 *
	 * @param token
	 *            the token
	 * @return the list
	 */
	public List<Event> listEvents(String token) {

		return token2activities.get(token).consume();
	}

	/**
	 * List profiles.
	 *
	 * @return the list
	 */
	public List<Profile> listProfiles() {

		return token2users.values().stream().collect(Collectors.toList());
	}

	/**
	 * Registry.
	 *
	 * @param token
	 *            the token
	 * @param profile
	 *            the profile
	 * @throws LoginException
	 *             the login exception
	 */
	public void registry(String token, Profile profile) throws LoginException {

		if (!accountIsAvailable(profile)) {

			throw new LoginException("Usuário já está registrado.");
		}

		token2users.put(token, profile);
		token2activities.put(token, new Activity());
		notify(new Event(String.format("%s entrou no chat.", profile.getNickname())));
	}

	/**
	 * Write message.
	 *
	 * @param token
	 *            the token
	 * @param message
	 *            the message
	 */
	public void writeMessage(String token, String message) {

		Profile profile = token2users.get(token);
		notify(new Event(message, profile));
	}

	/**
	 * Notify.
	 *
	 * @param event
	 *            the event
	 */
	private void notify(Event event) {

		token2activities.forEach((k, v) -> v.addEvent(event));
	}

	/**
	 * The Class Usher.
	 *
	 * @author pflima
	 * @since 03/07/2016
	 */
	class Usher extends Thread {

		/*
		 * (non-Javadoc)
		 *
		 * @see java.lang.Thread#run()
		 */
		@Override
		public void run() {

			while(true){

				try {
					List<String> blackTokens = new ArrayList<>();
					token2activities.forEach((k, v) -> {

						Duration diff = Duration.between(v.getLastInteraction(), LocalDateTime.now());
						if (diff.getSeconds() > MAX_TIME_WITHOUT_INTERACTION) {

							blackTokens.add(k);
						}
					});

					blackTokens.forEach(bt -> kick(bt));

					Thread.sleep(TimeUnit.SECONDS.toMillis(1));
				} catch (InterruptedException e) {

					e.printStackTrace();
				}
			}
		}
	}

}