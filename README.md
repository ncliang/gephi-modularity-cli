# gephi-modularity-cli
CLI to run Gephi Modularity operation

## Usage
```
[ncliang@carbonman gephimodularitycli]$ java -jar target/gephi-modularity-cli-1.0-SNAPSHOT.jar -h
Usage: java -jar gephi-modularity-cli.jar OPTIONS
Options category 'input':
  --edges [-e] (a string; default: "")
    CSV file containing edges.
  --nodes [-n] (a string; default: "")
    CSV file containing nodes.
  --resolution [-r] (a double; default: "0.7")
    Resolution for modularity operation.

Options category 'misc':
  --[no]help [-h] (a boolean; default: "false")
    Prints usage info.

Options category 'output':
  --out [-o] (a string; default: "")
    CSV file output containing nodes after modularity.

```
Example usage:
```
[ncliang@carbonman gephimodularitycli]$ java -jar target/gephi-modularity-cli-1.0-SNAPSHOT.jar  --nodes=/home/ncliang/Downloads/gephi/gephi_for_Nigel/node.csv --edges=/home/ncliang/Downloads/gephi/gephi_for_Nigel/edges.csv  --out=/tmp/out.csv
Apr 17, 2019 1:08:27 PM org.netbeans.modules.masterfs.watcher.Watcher getNotifierForPlatform
INFO: Native file watcher is disabled
Apr 17, 2019 1:08:28 PM org.gephi.io.processor.plugin.DefaultProcessor process
INFO: # Nodes loaded: 20
Apr 17, 2019 1:08:28 PM org.gephi.io.processor.plugin.DefaultProcessor process
INFO: # Edges loaded: 0
Apr 17, 2019 1:08:28 PM org.gephi.io.processor.plugin.DefaultProcessor process
INFO: # Nodes loaded: 20 (0 added)
Apr 17, 2019 1:08:28 PM org.gephi.io.processor.plugin.DefaultProcessor process
INFO: # Edges loaded: 167 (167 added)

[ncliang@carbonman gephimodularitycli]$ cat /tmp/out.csv 
Id,Label,timeset,modularity_class
23,Frontal lobe,,0
13,PFC,,0
16,SFG,,2
19,MFG,,2
22,IFG,,2
74,Limbic system,,0
26,Hippocampus,,1
29,Amygdala,,0
37,Cingulate Cortex,,1
40,Thalamus,,1
43,Hypothalamus,,0
46,NAcc,,0
55,Corpus striatum,,2
78,insula,,0
64,Parietal lobe,,3
63,PPC,,3
56,Temporal lobe,,3
67,STG,,3
70,MTG,,3
73,ITG,,3
```
