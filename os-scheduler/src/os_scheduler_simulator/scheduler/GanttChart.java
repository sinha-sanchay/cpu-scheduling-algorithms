package os_scheduler_simulator.scheduler;

import java.util.ArrayList;
import java.util.List;

public class GanttChart {

    private final List<Integer> timeline = new ArrayList<>();

    public void add(int pid) {
        timeline.add(pid);
    }

    public void print() {
        System.out.print("\nGantt Chart:\n| ");
        for (int pid : timeline) {
            System.out.print("P" + pid + " | ");
        }
        System.out.println("\n");
    }
}
