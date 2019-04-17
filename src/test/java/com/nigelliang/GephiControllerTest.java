package com.nigelliang;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import com.google.common.io.Resources;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.File;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Unit test for GephiModularityCli.
 */
@RunWith(JUnit4.class)
public class GephiControllerTest {
    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Test
    public void testExecuteModularity() throws Exception {
        File nodesFile = new File(Resources.getResource("nodes.csv").toURI());
        File edgesFile = new File(Resources.getResource("edges.csv").toURI());

        File outFile = folder.newFile();
        new GephiController()
                .importCsv(nodesFile)
                .importCsv(edgesFile)
                .executeModularity(0.7)
                .exportCsv(outFile);

        List<String> output = Files.readLines(outFile, Charsets.UTF_8);
        assertEquals("Id,Label,timeset,modularity_class", output.get(0));
        assertEquals("23,Frontal lobe,,0", output.get(1));
    }
}
