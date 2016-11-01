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
package filters;

import java.util.Arrays;

import play.http.HttpFilters;
import play.mvc.EssentialFilter;

/**
 * Helper class which has a varargs constructor taking the filters. Reduces
 * boilerplate for defining HttpFilters.
 */
public class MyDefaultHttpFilters implements HttpFilters {

	/** The filters. */
	private final EssentialFilter[] filters;

	/**
	 * Instantiates a new my default http filters.
	 *
	 * @param filters
	 *            the filters
	 */
	public MyDefaultHttpFilters(play.api.mvc.EssentialFilter... filters) {
		this.filters = Arrays.stream(filters).map(f -> f.asJava()).toArray(EssentialFilter[]::new);
	}

	/* (non-Javadoc)
	 * @see play.http.HttpFilters#filters()
	 */
	@Override
	public EssentialFilter[] filters() {
		return filters;
	}
}