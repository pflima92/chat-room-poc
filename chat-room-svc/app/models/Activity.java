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
import java.util.ArrayList;
import java.util.List;

/**
 * The Class Activity.
 *
 * @author pflima
 * @since 03/07/2016
 */
public class Activity {

	/** The last interaction. */
	private LocalDateTime lastInteraction;

	/** The events. */
	private List<Event> events = new ArrayList<>();

	/**
	 * Instantiates a new activity.
	 */
	public Activity() {

		interact();
	}

	/**
	 * Adds the event.
	 *
	 * @param event
	 *            the event
	 */
	public void addEvent(Event event) {

		events.add(event);
	}

	/**
	 * Consume.
	 *
	 * @return the list
	 */
	public List<Event> consume() {

		List<Event> result = new ArrayList<>(this.events);
		this.events.clear();
		interact();
		return result;
	}

	/**
	 * @return the lastInteraction
	 */
	public LocalDateTime getLastInteraction() {
		return lastInteraction;
	}

	/**
	 * Interact.
	 */
	private void interact() {

		this.lastInteraction = LocalDateTime.now();
	}
}