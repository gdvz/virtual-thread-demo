package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.CountDownLatch;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) throws InterruptedException {
        //  virtualThreadTest();
        //   normalThreadTest();

		SpringApplication.run(DemoApplication.class, args);
	}

    private static void normalThreadTest() throws InterruptedException {
        CountDownLatch doneLatch = new CountDownLatch(200000);

        for (int i = 0; i < 200000; i++) {
            Thread t = new Thread(() -> {
                try {
                    System.out.println("Thread: " + Thread.currentThread());
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    // ignore
                } finally {
                    doneLatch.countDown();
                }
            });
            t.start();
        }

        System.out.println("Finish creating 200k platform threads");

        doneLatch.await();
        System.out.println("All threads completed");
    }

    private static void virtualThreadTest() throws InterruptedException {

        CountDownLatch doneLatch = new CountDownLatch(200000);
        for (int i = 0; i < 200000; i++) {
            Thread.startVirtualThread(() -> {
                try {
                    System.out.println("Thread: " + Thread.currentThread());
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } finally {
                    doneLatch.countDown();
                }
            });
        }

        doneLatch.await();
        System.out.println("Finish creating 200k virtual threads");

    }

}
