package demo.threading;

// This code simulates a building with two rooms: Room 31 and Room 52.
// Each room has its own unique key (lock), ensuring privacy and exclusive access.
// Two people (threads) want to use the rooms at the same time:
//   - Person A uses the key to Room 31.
//   - Person B uses the key to Room 52.
// Because they use different keys, they can access their rooms concurrently without waiting for each other.
// While Person A is inside Room 31, Person B can independently be inside Room 52.
// Each person locks their room while inside (synchronized on their room's lock) and unlocks it after leaving.
// Meanwhile, the building’s manager (main thread) waits until both people finish and exit their rooms before closing the building.
//
// To compile and measure execution time on Windows PowerShell:
// javac demo\threading\BuildingLockExampleWithDifferentRoomKeys.java; Measure-Command { java demo.threading.BuildingLockExampleWithDifferentRoomKeys }
// Since both threads run in parallel, the total time will be approximately equal to the longest room usage (4 seconds), not the sum.

public class BuildingLockExampleWithDifferentRoomKeys {

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
        BuildingLockExampleWithDifferentRoomKeys buildingLockExampleWithDifferentRoomKeys = new BuildingLockExampleWithDifferentRoomKeys();

        Thread t1 = new Thread(() -> {
            try {
                buildingLockExampleWithDifferentRoomKeys.enterRoom31();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, "Thread-1 (Person A)");

        Thread t2 = new Thread(() -> {
            try {
                buildingLockExampleWithDifferentRoomKeys.enterRoom52();
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
        System.out.println("🏁 All rooms are free. Building is closed now.");
    }
}
