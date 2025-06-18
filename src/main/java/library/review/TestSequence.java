package review;

public class TestSequence {
    public static void main(String[] args) {
        Sequence sequence = new Sequence(5);

        sequence.add("Object 1");
        sequence.add("Object 2");
        sequence.add("Object 3");

        Selector selector = sequence.getSelector();

        System.out.println("Testing Selector:");
        while (!selector.end()) {
            System.out.println("Current: " + selector.current());
            selector.next();
        }

        System.out.println("End of sequence reached: " + selector.end());
    }
}