package demo.threading;

// This code simulates an ABC Complex that has multiple towers, each with its own rooms (Room 31, Room 52, etc).
// Due to strict privacy rules, the entire complex now uses a single key shared across all towers.
// This is like having one static lock (class-level lock) shared across all instances.
// Now, if Person A enters Room 31 of Tower-A,
// Person B must wait—even if they want to enter a different room in Tower-B.
// Only one person across the **entire complex** can be inside any room of any tower at a time.
// Once that person leaves, the next person can enter.
// Meanwhile, the complex manager (main thread) waits until all visitors are done to close the complex.
//
// You can compile and run this on PowerShell as:
// javac demo\threading\TowerLockExampleWithComplexLock.java; Measure-Command { java demo.threading.TowerLockExampleWithComplexLock }
// Since access is global across towers, execution time is roughly sum of both durations (~7 seconds).

public class TowerLockExampleWithComplexLock {

    public void enterRoom31() throws InterruptedException {
        synchronized (TowerLockExampleWithComplexLock.class) {
            System.out.println(Thread.currentThread().getName() + " entered Room 31 in Tower-A");
            sleep(4000);
            System.out.println(Thread.currentThread().getName() + " left Room 31 in Tower-A");
        }
    }

    public void enterRoom52() throws InterruptedException {
        synchronized (TowerLockExampleWithComplexLock.class) {
            System.out.println(Thread.currentThread().getName() + " entered Room 52 in Tower-B");
            sleep(3000);
            System.out.println(Thread.currentThread().getName() + " left Room 52 in Tower-B");
        }
    }

    private void sleep(int ms) {
        try { Thread.sleep(ms); } catch (InterruptedException _) {}
    }

    public static void main(String[] args) {
        // Two separate towers (different instances)
        TowerLockExampleWithComplexLock towerA = new TowerLockExampleWithComplexLock();
        TowerLockExampleWithComplexLock towerB = new TowerLockExampleWithComplexLock();

        Thread t1 = new Thread(() -> {
            try {
                towerA.enterRoom31();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, "Thread-1 (Person A)");

        Thread t2 = new Thread(() -> {
            try {
                towerB.enterRoom52();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }, "Thread-2 (Person B)");

        t1.start();
        t2.start();

        // Main thread waits for both threads to finish before closing the complex
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException _) {}
        System.out.println("🏁 All rooms are free. Complex is closed now.");
    }
}
