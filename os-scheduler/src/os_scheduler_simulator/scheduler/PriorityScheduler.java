package os_scheduler_simulator.scheduler;

import os_scheduler_simulator.process.PCB;
import java.util.*;

public class PriorityScheduler {

    public void schedule(List<PCB> processes) {

        processes.sort(Comparator.comparingInt(PCB::getArrivalTime));

        PriorityQueue<PCB> pq = new PriorityQueue<>(
                Comparator.comparingInt(PCB::getPriority)
        );

        int time = 0;
        int index = 0;
        GanttChart chart = new GanttChart();

        while (!pq.isEmpty() || index < processes.size()) {

            while (index < processes.size() &&
                    processes.get(index).getArrivalTime() <= time) {
                pq.add(processes.get(index));
                index++;
            }

            if (pq.isEmpty()) {
                time++;
                continue;
            }

            PCB p = pq.poll();

            while (!p.isFinished()) {
                chart.add(p.getPid());
                p.executeOneUnit();
                time++;
            }

            p.setCompletionTime(time);
        }

        chart.print();
        new FCFS().printTable(processes);
    }
}
