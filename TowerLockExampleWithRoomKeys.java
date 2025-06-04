package demo.threading;

// This code simulates a tower with two rooms: Room 31 and Room 52.
// Each room has its own unique special key (lock).
// Two people (threads) want to enter the tower simultaneously.
// Person A uses the key to Room 31.
// Person B uses the key to Room 52.
// Since they have different keys, both can enter their rooms concurrently without waiting.
// While Person A is inside Room 31, Person B can be inside Room 52 at the same time.
// Each person locks their room while inside (using synchronized on that room's lock).
// When finished, they leave and unlock the room.
// Meanwhile, the complex’s manager (main thread) waits until both leave before closing the tower.
//
// To time the execution from the console, run:
// javac demo\threading\TowerLockExampleWithRoomKeys.java; Measure-Command { java demo.threading.TowerLockExampleWithRoomKeys }
// The total time will be roughly the longest sleep time (4 seconds), showing concurrent access.

public class TowerLockExampleWithRoomKeys {

    private static final Object roomLock_31 = new Object();
    private static final Object roomLock_52 = new Object();

    public void enterRoom31() throws InterruptedException {
        synchronized (roomLock_31) {
            System.out.println(Thread.currentThread().getName() + " entered Room 31");
            sleep(4000);
            System.out.println(Thread.currentThread().getName() + " left Room 31");
        }
    }

    public void enterRoom52() throws InterruptedException {
        synchronized (roomLock_52) {
            System.out.println(Thread.currentThread().getName() + " entered Room 52");
            sleep(3000);
            System.out.println(Thread.currentThread().getName() + " left Room 52");
        }
    }

    private void sleep(int ms) {
        try { Thread.sleep(ms); } catch (InterruptedException _) {}
    }

    public static void main(String[] args) {
        TowerLockExampleWithRoomKeys tower = new TowerLockExampleWithRoomKeys();

        Thread t1 = new Thread(() -> {
            try {
                tower.enterRoom31();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, "Thread-1 (Person A)");

        Thread t2 = new Thread(() -> {
            try {
                tower.enterRoom52();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, "Thread-2 (Person B)");

        t1.start();
        t2.start();

        // Main thread waits for both threads to complete before exiting
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException _) {}
        System.out.println("🏁 All rooms are free. Tower is closed now.");
    }
}
