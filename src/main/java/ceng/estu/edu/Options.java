package ceng.estu.edu;

import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

public class Options {

    public Options(String[] args) {
        try {
            new CmdLineParser(this).parseArgument(args);
        } catch (CmdLineException e) {
            e.printStackTrace();
        }
    }

    @Option(name = "-i", aliases = {"input"}, usage = "input path", required = true)
    public String inputPath;

}
