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

import java.util.ArrayList;
import java.util.List;

import com.beust.jcommander.Parameter;

/**
 * Command-line interface handling for the metadata normaliser.
 */
public class NormaliseCLI {
    
    /**
     * Parameter specifier for "left over" parts of the input.
     */
    @Parameter(description = "<file to process>")
    private List<String> parameters = new ArrayList<String>();
    
    /**
     * Parameter specifier for the --help option.
     */
    @Parameter(names = "--help", description = "give usage information, then exit", help = true)
    private boolean help;
    
    /**
     * Getter for the "left over" parts of the input.
     * 
     * @return list of additional components of the command line
     */
    public List<String> getParameters() {
        return parameters;
    }
    
    /**
     * Indicate that help has been requested on the command line.
     * 
     * @return true if help has been requested.
     */
    public boolean isHelp() {
        return help;
    }
}
