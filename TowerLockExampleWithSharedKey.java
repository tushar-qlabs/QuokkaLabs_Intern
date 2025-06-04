package demo.threading;

// This code simulates a tower with two rooms: Room 31 and Room 52.
// Originally, each room had its own lock, but due to privacy concerns and lost keys,
// the complex owner decided to use a single main gate lock for the entire tower.
// Now, only one person can be inside the tower at a time, regardless of which room they want to enter.
// Two people (threads) want to access the tower simultaneously—
// Person A wants to enter Room 31, and Person B wants to enter Room 52.
// However, since there's only one main gate key, if Person A is inside Room 31,
// Person B must wait outside the tower until Person A finishes and exits.
// This ensures complete privacy—no one else can be inside while someone is in any room.
// The second person enters the tower only after the first person leaves and unlocks the gate.
// Meanwhile, the complex’s manager (main thread) patiently waits for both people to finish before closing the tower.
//
// You can compile and time the execution on Windows PowerShell using:
// javac demo\threading\TowerLockExampleWithSharedKey.java; Measure-Command { java demo.threading.TowerLockExampleWithSharedKey }
// Since access is sequential due to the shared tower key, the total execution time
// will be approximately the sum of both room visit durations (about 7 seconds).

public class TowerLockExampleWithSharedKey {
    public void enterRoom31() throws InterruptedException {
        synchronized (this) {
            System.out.println(Thread.currentThread().getName() + " entered Room 31");
            sleep(4000);
            System.out.println(Thread.currentThread().getName() + " left Room 31");
        }
    }

    public void enterRoom52() throws InterruptedException {
        synchronized (this) {
            System.out.println(Thread.currentThread().getName() + " entered Room 52");
            sleep(3000);
            System.out.println(Thread.currentThread().getName() + " left Room 52");
        }
    }

    private void sleep(int ms) {
        try { Thread.sleep(ms); } catch (InterruptedException _) {}
    }

    public static void main(String[] args) {
        TowerLockExampleWithSharedKey tower = new TowerLockExampleWithSharedKey();

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
