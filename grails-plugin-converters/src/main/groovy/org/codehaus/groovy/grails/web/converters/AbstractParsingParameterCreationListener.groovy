/* Copyright 2004-2005 Graeme Rocher
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
package org.codehaus.groovy.grails.web.converters

import org.codehaus.groovy.grails.web.servlet.mvc.ParameterCreationListener

/**
* Abstract base class for parameter creation listeners that parse incoming data such as JSON and XML.

* @author Graeme Rocher
* @since 1.0
*/
abstract class AbstractParsingParameterCreationListener implements ParameterCreationListener {

    /**
     * Populates the target map with current map using the root map to form a nested prefix
     * so that a hierarchy of maps is flattened.
     */
    protected createFlattenedKeys(Map root, Map current, Map target, prefix ='') {
        for (entry in current) {
            if (entry.value instanceof Map) {
                createFlattenedKeys(root,entry.value, target, "${entry.key}.")
            }
            else if (prefix) {
                target["${prefix}${entry.key}"] = entry.value
            }
        }
    }
}
