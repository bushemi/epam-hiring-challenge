package password_breaker;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

class BrutForce {
    private static HashCalculator calculator = new HashCalculator();
    private static final String TARGET = "69c459dd76c6198f72f0c20ddd3c9447"; //zebra
    private static final String TARGET_ADVANCED = "4fd0101ea3d0f5abbe296ef97f47afec"; // titkos
    private ThreadPoolExecutor executor;

    private String unHashedTarget = new String();
    private String unHashedTargetAdvanced = new String();

    BrutForce(int numberOfThreads) {
        executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(numberOfThreads);
    }

    void findPasswordRegular() throws InterruptedException {
        Password password = new Password();
        while (true) {
            executor.submit(() -> {
                String text = password.getPassword();
                if (checkForMatch(text)) unHashedTarget = text;
                return null;
            });
            if (executor.getQueue().size() > 10000) {
                Thread.sleep(10);
            }
            if (!unHashedTarget.isEmpty()) break;
        }
        System.out.println("unHashedTarget = " + unHashedTarget);
        executor.shutdown();
    }

    void findPasswordAdvancedLevel() throws InterruptedException {
        Password password = new Password();
        while (true) {
            executor.submit(() -> {
                String text = password.getPassword();
                if (checkForMatchAdvanced(text)) unHashedTargetAdvanced = text;
                return null;
            });

            if (!unHashedTargetAdvanced.isEmpty()) break;
            if (executor.getQueue().size() > 10000) {
                Thread.sleep(10);
            }
        }
        System.out.println("unHashedTargetAdvanced = " + unHashedTargetAdvanced);
        executor.shutdown();
    }

    private static boolean checkForMatch(String text) {
        String newHash = calculator.hash(text);
        return newHash.equals(TARGET);
    }

    private static boolean checkForMatchAdvanced(String text) {
        String newHash = calculator.hash(text);
        return newHash.equals(TARGET_ADVANCED);
    }
}
