# 🧵 Thread Synchronization in Java (`QuokkaLabs_Intern`)

### 🔐 Synchronized "Methods" Share the Same Lock

```java
public synchronized void methodA() { ... }
public synchronized void methodB() { ... }
```

- Both `methodA()` and `methodB()` are synchronized on the same monitor — the **current object (`this`)**.
- If **Thread-1** enters `methodA()`, it **locks `this`**.
- While Thread-1 holds that lock, **Thread-2 cannot enter** `methodB()` or any other synchronized method on the **same object**.

---

### ✅ Concurrent Access Possible If...

#### 1. One method is **not synchronized**:

```java
public synchronized void methodA() { ... }

public void methodB() { // not synchronized
    ...
}
```

> Now, different threads can access both methods simultaneously.

#### 2. Methods synchronize on **different locks**:

```java
private final Object lockA = new Object();
private final Object lockB = new Object();

public void methodA() {
    synchronized (lockA) {
        // critical section A
    }
}

public void methodB() {
    synchronized (lockB) {
        // critical section B
    }
}
```

> Now, threads can execute `methodA()` and `methodB()` concurrently since they **lock on different objects**.

---

### 🔍 What Does `synchronized` Actually Lock?

#### ✅ Instance Method (`non-static`)
```java
public synchronized void methodA() { ... }
```

⟺ Equivalent to:

```java
public void methodA() {
    synchronized (this) {
        ...
    }
}
```

#### ✅ Static Method
```java
public static synchronized void staticMethod() { ... }
```

⟺ Equivalent to:

```java
public static void staticMethod() {
    synchronized (MyClass.class) {
        ...
    }
}
```

---

### ✅ Summary

| Type                        | Locks On            |
|----------------------------|---------------------|
| `synchronized` instance method | `this`               |
| `synchronized` static method   | `MyClass.class`      |
| `synchronized (obj)` block     | `obj` (any object)   |

> Use different lock objects for **fine-grained control** over concurrent access.

---

Let me know if you want this as a downloadable `.md` file.
