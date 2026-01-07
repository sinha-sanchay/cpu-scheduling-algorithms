package os_scheduler_simulator.app;

import os_scheduler_simulator.process.PCB;
import os_scheduler_simulator.scheduler.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        List<PCB> processes = new ArrayList<>();

        System.out.print("Enter number of processes: ");
        int n = sc.nextInt();

        for (int i = 1; i <= n; i++) {

            System.out.println("\nEnter details for Process P" + i);

            System.out.print("Arrival Time: ");
            int at = sc.nextInt();

            System.out.print("Burst Time: ");
            int bt = sc.nextInt();

            System.out.print("Priority (lower number = higher priority): ");
            int pr = sc.nextInt();

            processes.add(new PCB(i, at, bt, pr));
        }

        System.out.println("\n=== FCFS Scheduling ===");
        new FCFS().schedule(clone(processes));

        System.out.println("\n=== SJF Scheduling ===");
        new SJF().schedule(clone(processes));

        System.out.println("\n=== Priority Scheduling ===");
        new PriorityScheduler().schedule(clone(processes));

        System.out.println("\n=== Round Robin Scheduling (Quantum = 2) ===");
        new RoundRobin(2).schedule(clone(processes));

        sc.close();
    }

    private static List<PCB> clone(List<PCB> list) {
        List<PCB> copy = new ArrayList<>();
        for (PCB p : list) {
            copy.add(new PCB(
                    p.getPid(),
                    p.getArrivalTime(),
                    p.getBurstTime(),
                    p.getPriority()
            ));
        }
        return copy;
    }
}
