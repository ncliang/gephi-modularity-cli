package com.nigelliang;

import com.google.devtools.common.options.OptionsParser;

import java.io.File;
import java.io.IOException;
import java.util.Collections;

/**
 * Main class
 */
public class GephiModularityCli {
//    private File nodesFile;
//    private File edgesFile;
//    private File outputFile;
//
//    public GephiModularityCli(File nodesFile, File edgesFile, File outputFile) {
//        this.nodesFile = nodesFile;
//        this.edgesFile = edgesFile;
//        this.outputFile = outputFile;
//    }
//
//    private void importFile(Workspace workspace, ImportController importController, File file) throws FileNotFoundException {
//        Container container = importController.importFile(file);
//        container.getLoader().setEdgeDefault(EdgeDirectionDefault.UNDIRECTED);   //Force UNDIRECTED
//        container.getLoader().setAllowAutoNode(true);  //create missing nodes
//        container.getLoader().setEdgesMergeStrategy(EdgeMergeStrategy.SUM);
//        container.getLoader().setAutoScale(true);
//
//        importController.process(container, new AppendProcessor(), workspace);
//    }
//
//    public void executeModularity(double resolution) throws IOException {
//        ProjectController pc = Lookup.getDefault().lookup(ProjectController.class);
//        pc.newProject();
//        Workspace workspace = pc.getCurrentWorkspace();
//
//        //Get controllers and models
//        ImportController importController = Lookup.getDefault().lookup(ImportController.class);
//
//        //Get models and controllers for this new workspace - will be useful later
//        GraphModel graphModel = Lookup.getDefault().lookup(GraphController.class).getGraphModel();
//
//        // Import files
//        importFile(workspace, importController, nodesFile);
//        importFile(workspace, importController, edgesFile);
//
//        //Run modularity algorithm - community detection
//        Modularity modularity = new Modularity();
//        modularity.setResolution(.7);
//        modularity.execute(graphModel);
//
//        // Export Nodes table
//        ExportController ec = Lookup.getDefault().lookup(ExportController.class);
//        ExporterSpreadsheet.ExportTable currentTable = ExporterSpreadsheet.ExportTable.NODES;
//
//        for (GraphFileExporterBuilder builder : Lookup.getDefault().lookupAll(GraphFileExporterBuilder.class)) {
//            if (builder.getName().toLowerCase().startsWith("spreadsheet")) {
//                GraphExporter exporter = builder.buildExporter();
//                ((ExporterSpreadsheet) exporter).setTableToExport(currentTable);
//                ec.exportFile(outputFile, exporter);
//            }
//        }
//    }


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
