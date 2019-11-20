package util;

import java.io.*;
import pojo.*;
import java.util.*;


public class OutputParser {
    private static StringBuilder output = new StringBuilder();

    private static final String emptyNodeTuple = "(0,0,0)";

    private static final String outputFilename = "output_file.txt";

    public static void addBuildingToBePrinted(RedBlackNode node) {
        output.append(((node != null) ? node.toString() : emptyNodeTuple) + "\n");
    }

    public static void addFinishedBuildingToBePrinted(RedBlackNode node, int finishTime) {
        if(node != null) {
            output.append("(" + node.getBuildingNumber() + "," + finishTime + ")\n");
        }
    }

    public static void addMultipleBuildingsToBePrinted(List<RedBlackNode> nodes) {
        if(nodes.size() > 0) {
            for(RedBlackNode node : nodes) {
                output.append(((node != null) ? node.toString() : emptyNodeTuple) + ",");
            }
            output.setLength(output.length() - 1);
            output.append("\n");
        } else {
            output.append(emptyNodeTuple + "\n");
        }
    }

    public static void print() throws IOException {
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(outputFilename))) {
            bufferedWriter.write(output.toString().trim());
        }
    }
}