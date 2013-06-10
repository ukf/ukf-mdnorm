
package uk.org.ukfederation.mdnorm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;

import org.testng.Assert;
import org.testng.annotations.Test;

public class WhitespaceNormaliserTest {

    private Reader acquireResource(String fileName) throws IOException {
        final String resourceName = "/" + fileName;
        final InputStream is = WhitespaceNormaliserTest.class.getResourceAsStream(resourceName);
        if (is == null) {
            throw new IOException("resource " + resourceName + " not found");
        }
        return new BufferedReader(new InputStreamReader(is, "UTF-8"));
    }
    
    private String acquireString(String fileName) throws IOException {
        final Reader in = acquireResource(fileName);
        final StringBuilder sb = new StringBuilder();
        for (;;) {
            final int c = in.read();
            if (c == -1) {
                break;
            }
            sb.append((char)c);
        }
        in.close();
        return sb.toString();
    }
    
    @Test
    public void standardOptions() throws IOException {
        final Reader in = acquireResource("standardOptionsIn.txt");
        final String expected = acquireString("standardOptionsOut.txt");
        final Writer out = new StringWriter();
        final WhitespaceNormaliser norm = new WhitespaceNormaliser(false, true, 4);
        norm.process(in,  out);
        final String result = out.toString();
        Assert.assertEquals(result, expected);
    }

    @Test
    public void noBlankLines() throws IOException {
        final Reader in = acquireResource("noBlankLinesIn.txt");
        final String expected = acquireString("noBlankLinesOut.txt");
        final Writer out = new StringWriter();
        final WhitespaceNormaliser norm = new WhitespaceNormaliser(false, false, 4);
        norm.process(in,  out);
        final String result = out.toString();
        Assert.assertEquals(result, expected);
    }

    @Test
    public void tabSize() throws IOException {
        final Reader in = acquireResource("tabSizeIn.txt");
        final String expected = acquireString("tabSizeOut.txt");
        final Writer out = new StringWriter();
        final WhitespaceNormaliser norm = new WhitespaceNormaliser(false, true, 3);
        norm.process(in,  out);
        final String result = out.toString();
        Assert.assertEquals(result, expected);
    }

    @Test
    public void oddSpaces() throws IOException {
        final Reader in = acquireResource("oddSpacesIn.txt");
        final String expected = acquireString("oddSpacesOut.txt");
        final Writer out = new StringWriter();
        final WhitespaceNormaliser norm = new WhitespaceNormaliser(true, true, 4);
        norm.process(in,  out);
        final String result = out.toString();
        Assert.assertEquals(result, expected);
    }

}
