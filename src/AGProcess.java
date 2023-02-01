public class AGProcess {
    String PName;
    int AT;
    int BT;
    int WT;
    int TAT;
    int CT; //(COMPLETE)END TIME
    int P;
    int QU;
    int QQU;
    int originalExecution;
    AGProcess(String processName , int arrivalTime , int burstTime, int priority, int quantum)
    {
        this.PName = processName;
        this.BT = burstTime;
        this.AT = arrivalTime;
        this.P = priority;
        this.QU = quantum;
        QQU = (quantum + 3)/4;
        this.originalExecution = burstTime;
    }
}
