import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
public class SRTF implements Calculation{
        int arrivalTime;
        int burstTime;
        int Context, ContextCounter = 0;
        int min, i = 0, total = 0, t = 0, c;
        float avgwt = 0, avgta = 0;
        ArrayList<Process> Final_list = new ArrayList<Process>();
        ArrayList<Process> last = new ArrayList<Process>();
        ArrayList<Process> processNames = new ArrayList<Process>();
        ArrayList<Process> output = new ArrayList<Process>();
        Scanner sc = new Scanner(System.in);
        Scanner nameSc = new Scanner(System.in);
        @Override
        public void Calculation() {
            System.out.print("Enter number of processes : " );int  NumberOfP = sc.nextInt();
            System.out.print("context switch overhead time : ");Context = sc.nextInt();
            for (int i = 0; i < NumberOfP; i++) {
                Process P = new Process();
                System.out.print("Enter process name : ");String PName = nameSc.nextLine();
                System.out.print("Enter arrival time : ");arrivalTime = sc.nextInt();
                System.out.print("Enter burst time   : ");burstTime = sc.nextInt();
                P.setmArrivalTime(arrivalTime);
                P.setmBurstTime(burstTime);
                P.setmRemainingTime(burstTime);
                P.setmName(PName);
                P.setmCompletionTime(0);
                processNames.add(P);
                Final_list.add(P);
            }
            while (true) {
                min = 1000;
                c = NumberOfP;
                if (total == NumberOfP) {
                    last.get(i - 1).getmName();
                    break;
                }
                // Find process with minimum burst time
                for (int j = 0; j < Final_list.size(); j++) {
                    if ((Final_list.get(j).getmArrivalTime() <= t) && (Final_list.get(j).getmRemainingTime() > 0)
                            && (Final_list.get(j).getmRemainingTime() < min)) {
                        min = Final_list.get(j).getmRemainingTime();
                        last.add(i, Final_list.get(j));
                        c = j;
                    }
                }
                if (c == NumberOfP) {
                    t++;
                } else {
                    if (i == 0) {
                        Final_list.get(c).setmRemainingTime((Final_list.get(c).getmRemainingTime() - 1));
                        output.add(Final_list.get(c));
                        t += (Context + 1);
//                        t++;
                        i++;
                    } else if ((last.get(i - 1).getmName().equals(Final_list.get(c).getmName()))) {
                        Final_list.get(c).setmRemainingTime((Final_list.get(c).getmRemainingTime() - 1));
                        t++;
                        i++;

                    } else {
                        Final_list.get(c).setmRemainingTime((Final_list.get(c).getmRemainingTime() - 1));
                        ContextCounter += (Context * 2);
                        output.add(Final_list.get(c));
                        t += ((Context * 2) + 1);
//                        t++;
                        i++;
                    }
                    if (Final_list.get(c).getmRemainingTime() == 0) {
                        Final_list.get(c).setmCompletionTime(Final_list.get(c).getmCompletionTime() + t);
                        total++;
                    }
                }

            }
            for (int i = 0; i < Final_list.size(); i++) {
                Final_list.get(i).setmTurnAroundTime(Final_list.get(i).getmCompletionTime() - Final_list.get(i).getmArrivalTime());
                Final_list.get(i).setmWaitingTime(Final_list.get(i).getmTurnAroundTime() - Final_list.get(i).getmBurstTime());
                avgwt += Final_list.get(i).getmWaitingTime();
                avgta += Final_list.get(i).getmTurnAroundTime();
            }

            System.out.println(" \t Process \t Arrival \t Burst \t Complete \t TurnAroundTime \t WaitTime ");

            for (int i = 0; i < Final_list.size(); i++) {
                System.out.println("\t\t"+ Final_list.get(i).getmName()+"\t\t\t"+Final_list.get(i).getmArrivalTime()+"\t\t\t"+Final_list.get(i).getmBurstTime()+"\t\t"+Final_list.get(i).getmCompletionTime()+"\t\t\t\t"+Final_list.get(i).getmTurnAroundTime()+"\t\t\t\t"+Final_list.get(i).getmWaitingTime());
            }
            System.out.println("AVGWaiting time = " + avgwt / NumberOfP);
            System.out.println("AVGTurnAround time = " + avgta / NumberOfP);
            System.out.println("[ Processes execution order ]");
            for (int i = 0; i < output.size(); i++) System.out.print(output.get(i).getmName() + "  ");
            System.out.println();

    }
}
