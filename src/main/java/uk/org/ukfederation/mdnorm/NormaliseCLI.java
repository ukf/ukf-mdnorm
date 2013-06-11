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
import com.beust.jcommander.Parameters;

/**
 * Command-line interface handling for the metadata normaliser.
 */
@Parameters(separators = "=")
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
     * Parameter specifier for the --tabSize option.
     */
    @Parameter(names = "--tabSize",
            description = "spaces per TAB character (default: --tabSize=4)",
            validateWith = TabSizeValidator.class)
    private int tabSize = 4;
    
    /**
     * Parameter specifier for the --discardBlankLines option.
     */
    @Parameter(names = "--discardBlankLines",
            description = "discard lines which only contain white space")
    private boolean discardingBlankLines;
    
    /**
     * Parameter specifier for the --keepOddSpaces option.
     */
    @Parameter(names = "--keepOddSpaces",
            description = "keep spaces which don't align with TABs")
    private boolean keepingOddSpaces;
    
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
    
    /**
     * Return the number of spaces corresponding to a TAB character.
     * 
     * @return number of spaces
     */
    public int getTabSize() {
        return tabSize;
    }
    
    /**
     * Indicate whether blank lines are to be discarded.
     * 
     * @return true if blank lines are to be discarded.
     */
    public boolean isDiscardingBlankLines() {
        return discardingBlankLines;
    }
    
    /**
     * Indicate whether odd spaces are to be retained.
     * 
     * @return true if odd spaces are to be retained.
     */
    public boolean isKeepingOddSpaces() {
        return keepingOddSpaces;
    }
}
