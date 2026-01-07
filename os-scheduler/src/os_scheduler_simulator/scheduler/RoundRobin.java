package os_scheduler_simulator.scheduler;

import os_scheduler_simulator.process.PCB;
import java.util.*;

public class RoundRobin {

    private final int quantum;

    public RoundRobin(int quantum) {
        this.quantum = quantum;
    }

    public void schedule(List<PCB> processes) {

        Queue<PCB> queue = new LinkedList<>();
        processes.sort(Comparator.comparingInt(PCB::getArrivalTime));

        int time = 0;
        int index = 0;
        GanttChart chart = new GanttChart();

        while (!queue.isEmpty() || index < processes.size()) {

            while (index < processes.size() &&
                    processes.get(index).getArrivalTime() <= time) {
                queue.add(processes.get(index));
                index++;
            }

            if (queue.isEmpty()) {
                time++;
                continue;
            }

            PCB p = queue.poll();
            int slice = Math.min(quantum, p.getRemainingTime());

            for (int i = 0; i < slice; i++) {
                chart.add(p.getPid());
                p.executeOneUnit();
                time++;
            }

            if (p.isFinished()) {
                p.setCompletionTime(time);
            } else {
                queue.add(p);
            }
        }

        chart.print();
        new FCFS().printTable(processes);
    }
}
