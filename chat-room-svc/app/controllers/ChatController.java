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
package controllers;

import java.util.List;

import models.ApplicationService;
import models.Event;
import models.Profile;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

/**
 * The Class ChatController.
 *
 * @author pflima
 * @since 03/07/2016
 */
public class ChatController extends Controller {

	/**
	 * Profiles.
	 *
	 * @return the result
	 */
	public Result profiles() {

		List<Profile> profiles = ApplicationService.getProfiles();
		return ok(Json.toJson(profiles));
	}

	/**
	 * Receive.
	 *
	 * @param token
	 *            the token
	 * @return the result
	 */
	public Result receive(String token) {

		if (!ApplicationService.isValidToken(token)) {

			return unauthorized();
		}

		List<Event> events = ApplicationService.listEvents(token);
		return ok(Json.toJson(events));
	}

	/**
	 * Send.
	 *
	 * @param token
	 *            the token
	 * @return the result
	 */
	public Result send(String token) {

		if (!ApplicationService.isValidToken(token)) {

			return unauthorized();
		}

		ApplicationService.sendMessage(token, request().body().asJson().get("message").asText());
		return ok();
	}
}