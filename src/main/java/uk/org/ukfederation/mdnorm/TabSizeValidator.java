/*
 * Copyright (C) 2013 University of Edinburgh.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package uk.org.ukfederation.mdnorm;

import com.beust.jcommander.IParameterValidator;
import com.beust.jcommander.ParameterException;

/**
 * Value validator for the --tabSize option.
 */
public class TabSizeValidator implements IParameterValidator {

    /** {@inheritDoc} */
    public void validate(String name, String value) {
        try {
            int n = Integer.parseInt(value);
            if (n < 1 || n > 16) {
                throw new ParameterException("Parameter " + name
                        + " must be in the range 1 to 16 (found " + value + ")");
            }
        } catch (NumberFormatException e) {
            throw new ParameterException("Parameter " + name
                    + " must be an integer (found " + value + ")");
        }
    }
    
}
