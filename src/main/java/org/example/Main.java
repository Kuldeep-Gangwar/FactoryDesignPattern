class SharedResource {
    private boolean available = false;

    public synchronized void produce() {
        while (available) {
            try {
                wait(); // wait until resource is consumed
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println(Thread.currentThread().getName() + " produced data.");
        available = true;
        notify(); // notify consumer
    }

    public synchronized void consume() {
        while (!available) {
            try {
                wait(); // wait until resource is produced
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println(Thread.currentThread().getName() + " consumed data.");
        available = false;
        notify(); // notify producer
    }
}

class Producer implements Runnable {
    private final SharedResource resource;

    public Producer(SharedResource resource) {
        this.resource = resource;
    }

    public void run() {
        for (int i = 0; i < 5; i++) {
            resource.produce();
        }
    }
}

class Consumer implements Runnable {
    private final SharedResource resource;

    public Consumer(SharedResource resource) {
        this.resource = resource;
    }

    public void run() {
        for (int i = 0; i < 5; i++) {
            resource.consume();
        }
    }
}

public class Main {
    public static void main(String[] args) {
        SharedResource resource = new SharedResource();

        Thread producerThread = new Thread(new Producer(resource), "Producer");
        Thread consumerThread = new Thread(new Consumer(resource), "Consumer");

        producerThread.start();
        consumerThread.start();
    }
}
