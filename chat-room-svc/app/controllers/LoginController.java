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

import org.apache.commons.lang3.StringUtils;

import exceptions.LoginException;
import models.ApplicationService;
import models.Profile;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

/**
 * The Class LoginController.
 *
 * @author pflima
 * @since 03/07/2016
 */
public class LoginController extends Controller {

	/**
	 * Signin.
	 *
	 * @param token
	 *            the token
	 * @return the result
	 */
	public Result signin(String token) {

		Profile profile = Json.fromJson(request().body().asJson(), Profile.class);

		if (StringUtils.isEmpty(token) || profile == null) {

			return badRequest();
		}

		try {

			ApplicationService.sigin(token, profile);
			return ok();
		} catch (LoginException e) {

			return unauthorized(e.getMessage());
		}
	}

	/**
	 * Signout.
	 *
	 * @param token
	 *            the token
	 * @return the result
	 */
	public Result signout(String token) {

		ApplicationService.signout(token);
		return ok();
	}
}