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

import java.io.BufferedReader;
import java.io.CharArrayWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.List;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;

/**
 * Metadata file normalisation.
 * 
 * @author iay
 */
public final class Normalise implements Runnable {

    /**
     * Indicates whether to retain odd spaces (ones which do not
     * align exactly with a tab stop).
     */
    private static final boolean RETAIN_ODD_SPACES = false;

    /**
     * Indicates whether to retain blank lines.
     */
    private static final boolean RETAIN_BLANK_LINES = true;

    /**
     * Number of spaces corresponding to a tab character.
     */
    private static final int TAB_SIZE = 4;

    /**
     * The file to process.
     */
    private final File file;

    /**
     * Constructor.
     * 
     * @param file file to process
     */
    private Normalise(final File file) {
        this.file = file;
    }

    /**
     * Terminate execution as the result of an exception.
     * 
     * @param s termination message
     * @param e exception which caused termination
     */
    private static void croak(final String s, final Exception e) {
        e.printStackTrace();
        System.err.println("Internal error: " + s + ": " + e.getMessage());
        System.exit(1);
    }

    private CharArrayWriter process(final Reader in) throws IOException {
        final CharArrayWriter w = new CharArrayWriter();
        final WhitespaceNormaliser norm =
                new WhitespaceNormaliser(RETAIN_ODD_SPACES, RETAIN_BLANK_LINES, TAB_SIZE);
        norm.process(in,  w);
        return w;
    }

    public void run() {

        /*
         * Open the input file and construct a Reader to access it.
         */
        InputStream is;
        try {
            is = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            croak("input file not found", e);
            return;
        }
        Reader in;
        try {
            in = new BufferedReader(new InputStreamReader(is, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            croak("UTF-8 not supported", e);
            return;
        }

        /*
         * Run through the input file processing each character in turn until we get to the end. The results are
         * collected in a CharArrayWriter.
         */
        CharArrayWriter w;
        try {
            w = process(in);
        } catch (IOException e) {
            croak("I/O exception while processing file", e);
            return;
        }

        /*
         * We're finished with the input file.
         */
        try {
            in.close();
        } catch (IOException e) {
            croak("can't close input file", e);
            return;
        }

        /*
         * Write the processed data back into the same file.
         */
        OutputStream os;
        try {
            os = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            croak("output file not found", e);
            return;
        }
        Writer out;
        try {
            out = new OutputStreamWriter(os, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            croak("UTF-8 not supported", e);
            return;
        }
        try {
            w.writeTo(out);
            out.close();
        } catch (IOException e) {
            croak("I/O exception while writing output file", e);
            return;
        }
    }

    /**
     * Command-line entry point.
     * 
     * @param args command-line arguments
     */
    public static void main(final String[] args) {
        final String progName = "Normalise";
        final NormaliseCLI cli = new NormaliseCLI();
        final JCommander jc = new JCommander(cli);
        jc.setProgramName(progName);
        
        try {
            jc.parse(args);
        } catch (ParameterException e) {
            System.out.println(progName + ": " + e.getMessage());
            System.out.println();
            jc.usage();
            System.exit(1);
        }
        
        /*
         * Handle the "help" command.
         */
        if (cli.isHelp()) {
            jc.usage();
            return;
        }
        
        /*
         * Acquire name of file to process.
         */
        List<String> files = cli.getParameters();
        if (files.size() != 1) {
            jc.usage();
            System.exit(1);
        }

        Normalise norm = new Normalise(new File(files.get(0)));
        norm.run();
    }

}
