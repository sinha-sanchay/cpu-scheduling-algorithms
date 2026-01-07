package os_scheduler_simulator.process;

public class PCB {

    private final int pid;
    private final int arrivalTime;
    private final int burstTime;
    private final int priority;

    private int remainingTime;
    private int completionTime;

    public PCB(int pid, int arrivalTime, int burstTime, int priority) {
        this.pid = pid;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.priority = priority;
        this.remainingTime = burstTime;
    }

    public int getPid() {
        return pid;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public int getBurstTime() {
        return burstTime;
    }

    public int getPriority() {
        return priority;
    }

    public int getRemainingTime() {
        return remainingTime;
    }

    public void executeOneUnit() {
        remainingTime--;
    }

    public boolean isFinished() {
        return remainingTime == 0;
    }

    public void setCompletionTime(int time) {
        this.completionTime = time;
    }

    public int getCompletionTime() {
        return completionTime;
    }

    public int getTurnaroundTime() {
        return completionTime - arrivalTime;
    }

    public int getWaitingTime() {
        return getTurnaroundTime() - burstTime;
    }
}
