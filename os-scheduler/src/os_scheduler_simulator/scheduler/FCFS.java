package os_scheduler_simulator.scheduler;

import os_scheduler_simulator.process.PCB;
import java.util.*;

public class FCFS {

    public void schedule(List<PCB> processes) {

        processes.sort(Comparator.comparingInt(PCB::getArrivalTime));
        int time = 0;
        GanttChart chart = new GanttChart();

        for (PCB p : processes) {

            if (time < p.getArrivalTime()) {
                time = p.getArrivalTime();
            }

            while (!p.isFinished()) {
                chart.add(p.getPid());
                p.executeOneUnit();
                time++;
            }

            p.setCompletionTime(time);
        }

        chart.print();
        printTable(processes);
    }

    protected void printTable(List<PCB> processes) {
        System.out.println("PID\tAT\tBT\tCT\tTAT\tWT");
        for (PCB p : processes) {
            System.out.println(
                    "P" + p.getPid() + "\t" +
                            p.getArrivalTime() + "\t" +
                            p.getBurstTime() + "\t" +
                            p.getCompletionTime() + "\t" +
                            p.getTurnaroundTime() + "\t" +
                            p.getWaitingTime()
            );
        }
    }
}
