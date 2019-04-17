package com.nigelliang;

import org.gephi.graph.api.GraphController;
import org.gephi.graph.api.GraphModel;
import org.gephi.io.exporter.api.ExportController;
import org.gephi.io.exporter.plugin.ExporterSpreadsheet;
import org.gephi.io.exporter.spi.GraphExporter;
import org.gephi.io.exporter.spi.GraphFileExporterBuilder;
import org.gephi.io.importer.api.Container;
import org.gephi.io.importer.api.EdgeDirectionDefault;
import org.gephi.io.importer.api.EdgeMergeStrategy;
import org.gephi.io.importer.api.ImportController;
import org.gephi.io.processor.plugin.AppendProcessor;
import org.gephi.project.api.ProjectController;
import org.gephi.project.api.Workspace;
import org.gephi.statistics.plugin.Modularity;
import org.openide.util.Lookup;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class GephiController {
    Workspace workspace;

    public GephiController() {
        ProjectController pc = Lookup.getDefault().lookup(ProjectController.class);
        pc.newProject();
        this.workspace = pc.getCurrentWorkspace();
    }

    public GephiController importCsv(File file) throws FileNotFoundException {
        ImportController importController = Lookup.getDefault().lookup(ImportController.class);
        Container container = importController.importFile(file);
        container.getLoader().setEdgeDefault(EdgeDirectionDefault.UNDIRECTED);   //Force UNDIRECTED
        container.getLoader().setAllowAutoNode(true);  //create missing nodes
        container.getLoader().setEdgesMergeStrategy(EdgeMergeStrategy.SUM);
        container.getLoader().setAutoScale(true);

        importController.process(container, new AppendProcessor(), workspace);
        return this;
    }

    public GephiController executeModularity(double resolution) {
        GraphModel graphModel = Lookup.getDefault().lookup(GraphController.class).getGraphModel();
        Modularity modularity = new Modularity();
        modularity.setResolution(resolution);
        modularity.execute(graphModel);
        return this;
    }

    public void exportCsv(File file) throws IOException {
        // Export Nodes table
        ExportController ec = Lookup.getDefault().lookup(ExportController.class);
        ExporterSpreadsheet.ExportTable currentTable = ExporterSpreadsheet.ExportTable.NODES;

        for (GraphFileExporterBuilder builder : Lookup.getDefault().lookupAll(GraphFileExporterBuilder.class)) {
            if (builder.getName().toLowerCase().startsWith("spreadsheet")) {
                GraphExporter exporter = builder.buildExporter();
                ((ExporterSpreadsheet) exporter).setTableToExport(currentTable);
                ec.exportFile(file, exporter);
            }
        }
    }
}
