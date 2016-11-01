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
package controllers;

import models.ApplicationService;
import play.mvc.Controller;
import play.mvc.Result;

/**
 * The Class AdminController.
 *
 * @author pflima
 * @since 03/07/2016
 */
public class AdminController extends Controller {

	private final String MASTER_CODE = "Pa834li";

	public Result reset(String master) {

		if (!master.equals(MASTER_CODE)) {

			return unauthorized();
		}

		ApplicationService.reset();
		return ok("Room reseted with success.");
	}
}