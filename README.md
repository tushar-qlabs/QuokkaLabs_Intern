# QuokkaLabs_Intern

Two threads cannot access different synchronized methods of the same object at the same time if the methods are synchronized on the same monitor, i.e., this.

public synchronized void methodA() { ... }
public synchronized void methodB() { ... }

Both methodA() and methodB() are synchronized on the same object lock (this). So:

If Thread-1 enters methodA(), it acquires the lock on this object.

While Thread-1 holds that lock, Thread-2 cannot enter methodB() or any other synchronized method of the same object — because it’s trying to acquire the same lock.

✅ But Yes, if:
One method is not synchronized, it can be accessed by other threads.

Or, you synchronize on different locks, like:

private final Object lockA = new Object();
private final Object lockB = new Object();

public void methodA() {
    synchronized(lockA) {
        // critical section A
    }
}

public void methodB() {
    synchronized(lockB) {
        // critical section B
    }
}
Now, different threads can enter methodA() and methodB() at the same time... because they lock on different objects.

Point to be noted that, when you use synchronized method Lock is acquired itself on the current object (this).

Means ...
Instance method (non-static):
public synchronized void methodA() { ... }

Is similar to ...
public void methodA() {
    synchronized (this) {
        ...
    }
}


Same way...
A static synchronized method is 100% equivalent to synchronizing on the class object:
public static synchronized void staticMethod() {
    // synchronized on MyClass.class
}

⟺ Equivalent to ...
public static void staticMethod() {
    synchronized (MyClass.class) {
        // same effect
    }
}




