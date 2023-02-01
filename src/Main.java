import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static Scanner scanner=new Scanner(System.in);
    public static void main(String[] args){
        while(true){
            ArrayList<AGProcess>Processes = new ArrayList<>();
            System.out.println("---------------------------------");
            System.out.println("1- FCFS                          ");
            System.out.println("2- SRTF                          ");
            System.out.println("3- RR                            ");
            System.out.println("4- Prem Priority Scheduling      ");
            System.out.println("5- AG Scheduling                 ");
            System.out.println("6- EXIT                          ");
            System.out.println("---------------------------------");
            System.out.print  ("Choose Scheduler : ");
            int choice=scanner.nextInt();
            if(choice==1){
                FCFS fcfs=new FCFS();
                fcfs.Calculation();
            }
            else if(choice==2){
                SRTF srtf=new SRTF();
                srtf.Calculation();
            }
            else if(choice==3){
                RoundRobin RR=new RoundRobin();
                RR.Calculation();
            }
            else if(choice==4){
                 PremPriority PremPriority=new PremPriority();
                 PremPriority.Calculation();
            }
            else if(choice==5){
                System.out.print("Enter Number Of Processes :");
                int NOP = scanner.nextInt();
                for (int i = 0; i < NOP; i++) {
                    String processName;
                    int burstTime, arrivalTime, priority, quantum;
                    System.out.print("Enter the name : ");
                    processName = scanner.next();
                    System.out.print("Enter the burst time : ");
                    burstTime = scanner.nextInt();
                    System.out.print("Enter the arrival time : ");
                    arrivalTime = scanner.nextInt();
                    System.out.print("Enter the priority time : ");
                    priority = scanner.nextInt();
                    System.out.print("Enter the Quantum time : ");
                    quantum = scanner.nextInt();
                    AGProcess process = new AGProcess(processName, arrivalTime, burstTime, priority, quantum);
                    Processes.add(process);
                }
                AG ag= new AG(Processes);
                ag.Calculation();
            }
            else if(choice==6){
                break;
            }
        }

    }
}
