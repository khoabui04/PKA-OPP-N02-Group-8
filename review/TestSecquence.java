public class TestSequence {
    public static void main(String[] args) {
        // Create a Sequence object
        Sequence sequence = new Sequence(5);
        
        // Add some objects to the sequence
        sequence.add("Object 1");
        sequence.add("Object 2");
        sequence.add("Object 3");
        
        // Get the selector from the sequence
        Selector selector = sequence.getSelector();
        
        // Test the selector functionality
        System.out.println("Testing Selector:");
        while (!selector.end()) {
            System.out.println("Current: " + selector.current());
            selector.next();
        }
        
        // Check if the end method works correctly
        System.out.println("End of sequence reached: " + selector.end());
    }
}