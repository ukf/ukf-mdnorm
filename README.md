# ukf-mdnorm

## UK federation Metadata Normaliser

This CLI application normalises certain aspects of a SAML 2.0 metadata file's white space in an attempt to make it more compact.

* DOS/Windows carriage return characters are always stripped from the file.
* Initial white space on a line is normalised into an equivalent collection of TAB characters.
* Lines consisting only of white space are reduced to empty lines.

The main CLI entry point for the application is the `uk.org.ukfederation.mdnorm.Normalise` class.

The command line consists of a single argument which is the name of the file to be processed.  The file is read, processed into an in-memory form and then written back to its original location.

The following options are also available:

* `--help` prints usage information and then exits
* `--tabSize=N` sets the number of spaces deemed equivalent to a TAB character (default: four spaces)
* `--discardBlankLines` requests that lines containing only white space are removed (by default, they are retained)
* `--keepOddSpaces` requests that spaces which don't cause alignment to a tab stop are retained (by default, they are discarded)

## Tab and Space Handling

The application attempts to normalise leading white space on a line into a series of TAB characters.  The `--tabSize` option (defaulting to `--tabSize=4`) sets the assumed size of a tab stop in spaces, for both input and output.

By default, space characters which don't cause alignment with a tab stop are discarded.  For example, an input line which starts with nine spaces will be normalised to two TAB characters by default.  With the `--keepOddSpaces` option, the result will be two TAB characters and one space character.

Lines which contain entirely of white space are normally retained as an empty line.  Empty lines are discarded if the `--discardBlankLines` option is used.  Note that the application is blind to the structure of the file, so that blank lines will be discarded in all contexts, even within XML comments.

## Copyright and License

The entire package is Copyright (C) 2013, University of Edinburgh.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
