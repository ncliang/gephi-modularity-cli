package com.nigelliang;

import com.google.devtools.common.options.Option;
import com.google.devtools.common.options.OptionsBase;

public class Options extends OptionsBase {
    @Option(
            name = "help",
            abbrev = 'h',
            help = "Prints usage info.",
            defaultValue = "false"
    )
    public boolean help;

    @Option(
            name = "nodes",
            abbrev = 'n',
            help = "CSV file containing nodes.",
            category = "input",
            defaultValue = ""
    )
    public String nodesFile;

    @Option(
            name = "edges",
            abbrev = 'e',
            help = "CSV file containing edges.",
            category = "input",
            defaultValue = ""
    )
    public String edgesFile;

    @Option(
            name = "resolution",
            abbrev = 'r',
            help = "Resolution for modularity operation.",
            category = "input",
            defaultValue = "0.7"
    )
    public double resolution;


    @Option(
            name = "out",
            abbrev = 'o',
            help = "CSV file output containing nodes after modularity.",
            category = "output",
            defaultValue = ""
    )
    public String outputFile;
}
