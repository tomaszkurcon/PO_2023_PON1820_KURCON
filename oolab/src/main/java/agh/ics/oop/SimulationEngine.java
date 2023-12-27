package agh.ics.oop;


import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SimulationEngine {
    private final List<Simulation> listOfSimulations;
    private final List<Thread> simulationTasks = new LinkedList<>();
    private final ExecutorService executorService = Executors.newFixedThreadPool(4);

    public SimulationEngine(List<Simulation> listOfSimulations) {
        this.listOfSimulations = listOfSimulations;
    }

    public void runSync() {
        for (Simulation simulation : listOfSimulations) {
            simulation.run();
        }
    }

    public void runAsync() {
        for (Simulation simulation : listOfSimulations) {
            Thread simulationTask = new Thread(simulation);
            simulationTasks.add(simulationTask);
            simulationTask.start();
        }
        //App waits for simulations to be ended and then update GUI, how to fix it?
//        try {
//            awaitSimulationsEnd();
//        } catch (InterruptedException err) {
//        }

    }

    public void awaitSimulationsEnd() throws InterruptedException {
        for (Thread simulationTaskThread : simulationTasks) {
            simulationTaskThread.join();
        }
        executorService.shutdown();
        executorService.awaitTermination(10, TimeUnit.SECONDS);
    }

    public void runAsyncInThreadPool() {

        for (Simulation simulation : listOfSimulations) {
            executorService.submit(simulation);
        }
        try {
            awaitSimulationsEnd();
        } catch (InterruptedException err) {
        }

    }
}
