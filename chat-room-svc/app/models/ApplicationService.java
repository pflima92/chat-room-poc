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

import java.util.List;

import exceptions.LoginException;

/**
 * The Class ApplicationService.
 *
 * @author pflima
 * @since 03/07/2016
 */
public class ApplicationService {

	/** The Constant room. */
	private static Room room;

	static {
		// Unique room
		room = new Room();
	}

	/**
	 * Gets the profiles.
	 *
	 * @return the profiles
	 */
	public static List<Profile> getProfiles() {

		return room.listProfiles();
	}

	/**
	 * Checks if is valid token.
	 *
	 * @param token
	 *            the token
	 * @return true, if is valid token
	 */
	public static boolean isValidToken(String token) {

		return room.isValidToken(token);
	}

	/**
	 * Reset room state
	 */
	public static void reset() {

		room = new Room();
	}

	/**
	 * List events.
	 *
	 * @param token
	 *            the token
	 * @return the list
	 */
	public static List<Event> listEvents(String token) {

		return room.listEvents(token);
	}

	/**
	 * Send message.
	 *
	 * @param token
	 *            the token
	 * @param message
	 *            the message
	 */
	public static void sendMessage(String token, String message) {

		room.writeMessage(token, message);
	}

	/**
	 * Sigin.
	 *
	 * @param token
	 *            the token
	 * @param profile
	 *            the profile
	 * @throws LoginException
	 *             the login exception
	 */
	public static void sigin(String token, Profile profile) throws LoginException {

		room.registry(token, profile);
	}

	/**
	 * Signout.
	 *
	 * @param token
	 *            the token
	 */
	public static void signout(String token) {

		room.kick(token);
	}
}