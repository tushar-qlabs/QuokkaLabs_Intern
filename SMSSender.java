package demo.threading;

import java.util.ArrayList;
import java.util.List;
import java.util.AbstractMap.SimpleEntry;
import java.util.concurrent.*;

public class SMSSender {

    // Function to simulate sending an SMS
    public static void sendSMS(String serverName, String phoneNumber, String message) {
        System.out.println("[" + serverName + "] Sending SMS to " + phoneNumber + ": " + message);
        try {
            // Simulate time taken to send an SMS (e.g., 1 second)
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void main(String[] args) {
        // Create a list of employees with phone numbers and messages
        List<SimpleEntry<String, String>> employeeMessages = new ArrayList<>();

        // Populate the list with 100 employees
        for (int i = 1; i <= 100; i++) {
            String phoneNumber = "123-456-789" + i;
            String message = "Hello, this is a message for employee " + i + ".";
            employeeMessages.add(new SimpleEntry<>(phoneNumber, message));
        }

        // Create a blocking queue to hold employee messages
        BlockingQueue<SimpleEntry<String, String>> queue = new LinkedBlockingQueue<>(employeeMessages);

        // Define the server names
        String[] servers = {
                "Server 1", "Server 2",
                "Server 3", "Server 4",
                "Server 5", "Server 6",
                "Server 7", "Server 8",
                "Server 9", "Server 10",
                "Server 11", "Server 12",
                "Server 13", "Server 14",
                "Server 15"
        };

        // Get the number of available CPU cores
        int availableCores = Runtime.getRuntime().availableProcessors();
        System.out.println("Total available cores: " + availableCores);

        // Adjust the number of threads based on type of workload.
        // For I/O-bound workloads (tasks spend most of their time waiting for external operations), more threads are often beneficial, even exceeding the number of CPU cores.
        // For CPU-bound workloads (tasks spend most of their time doing calculations), the optimal number of threads is often close to the number of CPU cores. Having significantly more threads than cores can lead to excessive context switching, which can degrade performance
        int threadCount = Math.min(availableCores, servers.length); // Limit to number of servers


        // Create a fixed thread pool with the calculated thread count
        ExecutorService executor = Executors.newFixedThreadPool(threadCount);

        // Assign tasks to each server
        for (String server : servers) {
            executor.submit(() -> {
                while (true) {
                    try {
                        // Take an employee message from the queue
                        SimpleEntry<String, String> employee = queue.take();

                        // Check if the employee is the sentinel value to stop the thread
                        if (employee.getKey().equals("STOP")) {
                            break;
                        }

                        // Send the SMS to the employee
                        try {
                            sendSMS(server, employee.getKey(), employee.getValue());
                        } catch (Exception e) {
                            System.err.println("Error sending SMS to " + employee.getKey() + ": " + e.getMessage());
                        }
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt(); // Restore interrupted status
                        break; // Exit the loop if interrupted
                    }
                }
            });
        }

        // Send the sentinel value to stop the threads.
        // Since we are using Queue (FIFO) order, the STOP signal will be processed after all the employee messages.
        // Every server will consume the single STOP signal, because after consuming a STOP signal, the thread will break out of the loop. (refer to line 59)
        for (String server : servers) {
            System.out.println("Sending STOP signal to " + server);
            try {
                queue.put(new SimpleEntry<>("STOP", "")); // Use put to avoid the warning
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        // Shutdown the executor when done
        executor.shutdown();
        try {
            if (!executor.awaitTermination(10, TimeUnit.MINUTES)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
        }

        System.out.println("All messages sent!");
    }
}
