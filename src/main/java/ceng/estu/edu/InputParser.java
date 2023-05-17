package ceng.estu.edu;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class InputParser {

    private final Set<Node> createdNodes;

    public InputParser() {
        this.createdNodes = new HashSet<>();
    }

    public Set<Node> parse(String path) {
        getRules(path).forEach(this::commitRule);
        return this.createdNodes;
    }

    private List<String> getRules(String path) {
        try {
            return Files.lines(Paths.get(path)).filter(rule -> rule.matches("((\\w*,)*(\\w+)->)?(\\w+)")).collect(Collectors.toList());
        } catch (IOException IOX) {
            System.out.println(IOX.getMessage());
            return new ArrayList<>();
        }
    }

    private void commitRule(String rule) {
        int separatorIndex = rule.indexOf("->");
        Node subjectNode = getNodeFromCreatedNodes(rule.substring(separatorIndex == -1 ? 0 :separatorIndex + 2));
        if (separatorIndex == -1) return;
        // save preconditions to subject node
        for (String preconditionId : rule.substring(0, separatorIndex).split(","))
            subjectNode.preconditions.add(getNodeFromCreatedNodes(preconditionId));
    }

    // or create a new node
    private Node getNodeFromCreatedNodes(String nodeName) {
        Node node = createdNodes.stream().filter(crNode -> Objects.equals(crNode.getName(), nodeName)).findFirst().orElse(null);
        if (node == null) createdNodes.add(node = new Node(nodeName));
        return node;
    }

}
