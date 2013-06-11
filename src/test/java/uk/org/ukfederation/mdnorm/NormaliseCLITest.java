
package uk.org.ukfederation.mdnorm;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;

public class NormaliseCLITest {

    private void expectException(String[] args) {
        NormaliseCLI cli = new NormaliseCLI();
        try {
            new JCommander(cli, args);
        } catch (ParameterException pe) {
            // this is expected
            return;
        }
        Assert.fail("expected a parameter exception");
    }

    @Test
    public void tabSize() {
        NormaliseCLI cli = new NormaliseCLI();
        new JCommander(cli, new String[]{"--tabSize=5"});
        Assert.assertEquals(cli.getTabSize(), 5);

        // test some bad values
        expectException(new String[]{"--tabSize=bad"});
        expectException(new String[]{"--tabSize=-1"});
        expectException(new String[]{"--tabSize=0"});
        expectException(new String[]{"--tabSize=99"});
    }
}
