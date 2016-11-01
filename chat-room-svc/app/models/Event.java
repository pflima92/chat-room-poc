/*
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package models;

import java.time.LocalDateTime;

/**
 * The Class Event.
 *
 * @author pflima
 * @since 03/07/2016
 */
public class Event {

	/** The date time. */
	private LocalDateTime dateTime;
	
	/** The message. */
	private String message;
	
	/** The profile. */
	private Profile profile;

	/**
	 * Instantiates a new event.
	 *
	 * @param message
	 *            the message
	 */
	public Event(String message) {

		this(message, null);
	}

	/**
	 * Instantiates a new event.
	 *
	 * @param message
	 *            the message
	 * @param profile
	 *            the profile
	 */
	public Event(String message, Profile profile) {

		this.dateTime = LocalDateTime.now();
		this.message = message;
		this.profile = profile;
	}

	/**
	 * @return the dateTime
	 */
	public LocalDateTime getDateTime() {
		return dateTime;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @return the profile
	 */
	public Profile getProfile() {
		return profile;
	}

	/**
	 * @param dateTime
	 *            the dateTime to set
	 */
	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}

	/**
	 * @param message
	 *            the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @param profile
	 *            the profile to set
	 */
	public void setProfile(Profile profile) {
		this.profile = profile;
	}
}