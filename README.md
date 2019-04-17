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
