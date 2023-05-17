package ceng.estu.edu;

public class Main {

    public static void main(String[] args) {
        new InputParser().parse(new Options(args).inputPath).forEach(Thread::start);
    }

}
