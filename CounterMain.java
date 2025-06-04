package demo.threading;

// Volatile keyword is used to reflect the changes made by one thread to other threads immediately.
// It is used to prevent thread caching and ensure that the value of the variable is always read from the main memory.
// You should use it only when you don't care about the previous value of the variable, and you are not dependent on the previous value.

// We can achieve atomicity by using the synchronized method or block or by using the Atomic classes provided by Java.
// It ensures that only one thread can access the resource at a time, making the operation atomic.

// Atomicity is crucial when your operation depends on the previous value of a variable. For example, in operations like incrementing
// a counter (x++), where you need to read the current value, modify it, and then write it back, atomicity ensures that no other
// thread can interfere during this process.
// If your operation does not depend on the previous value and is simply setting a new value (like x = 5),
// then atomicity isn’t as critical. In such cases, using the volatile keyword might be enough to ensure visibility across threads.

// This class provides atomic operations on integers and are more efficient than using synchronized methods or blocks but are limited for simple operations.
//import java.util.concurrent.atomic.AtomicInteger;

class Counter {
//    private final AtomicInteger count = new AtomicInteger(0);

    private int count = 0;
    public synchronized void increment() {  // This synchronization keyword ensures that only one thread can access this method at a time
//        count.incrementAndGet();
        ++count;
    }

    public int getCount() {
//        return count.get();
        return count;
    }
}


public class CounterMain {
    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter();

        // Create two threads that both increment the counter
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                counter.increment();
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                counter.increment();
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("Final count: " + counter.getCount());  // Not guaranteed to be 2000
    }
}
