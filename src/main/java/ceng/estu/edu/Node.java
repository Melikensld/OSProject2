package ceng.estu.edu;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class Node extends Thread {

    public final Set<Node> preconditions;

    public Node(String name) {
        super(name);
        this.preconditions = new HashSet<>();
    }

    @Override
    public void run() {
        if (!isAvailable()) waiting();
        try {
            for (Node precondition : preconditions)
                precondition.join();
            perform();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void waiting() {
        System.out.printf("Node%s is waiting for %s\n", getName(), preconditions.stream().map(Thread::getName).collect(Collectors.joining(",")));
    }

    private void perform() throws InterruptedException {
        System.out.printf("Node%s is being started\n", getName());
        Thread.sleep((long) (Math.random() * 2000));
        System.out.printf("Node%s is completed\n", getName());
    }

    private boolean isAvailable() {
        return preconditions.stream().filter(Thread::isAlive).findFirst().orElse(null) == null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return getName().equals(node.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }

}
