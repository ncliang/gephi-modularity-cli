package com.nigelliang;

import com.google.devtools.common.options.OptionsParser;

import java.io.File;
import java.io.IOException;
import java.util.Collections;

/**
 * Main class
 */
public class GephiModularityCli {
    
    public static void main(String[] args) {
        OptionsParser parser = OptionsParser.newOptionsParser(Options.class);
        parser.parseAndExitUponError(args);
        Options options = parser.getOptions(Options.class);
        if (options.help) {
            printUsage(parser);
            return;
        }
        if (options.nodesFile.isEmpty() || !(new File(options.nodesFile)).exists()) {
            System.out.println("--nodes option is required.");
            return;
        }
        if (options.edgesFile.isEmpty() || !(new File(options.edgesFile)).exists()) {
            System.out.println("--edges option is required.");
            return;
        }

        File nodesFile = new File(options.nodesFile);
        File edgesFile = new File(options.edgesFile);
        File outFile = new File(options.outputFile);
        try {
            new GephiController()
                    .importCsv(nodesFile)
                    .importCsv(edgesFile)
                    .executeModularity(options.resolution)
                    .exportCsv(outFile);

        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
    }

    private static void printUsage(OptionsParser parser) {
        System.out.println("Usage: java -jar gephi-modularity-cli.jar OPTIONS");
        System.out.println(parser.describeOptions(Collections.<String, String>emptyMap(),
                OptionsParser.HelpVerbosity.LONG));
    }

}
