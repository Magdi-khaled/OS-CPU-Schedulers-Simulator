import java.util.ArrayList;
import java.util.HashMap;
public class AG implements Calculation{
    int mode;
    ArrayList<AGProcess> Processes, Que;
    HashMap<Integer, ArrayList<AGProcess>> InProcess;
    HashMap<String, ArrayList<Integer>> HistoryQTime;
    AG(ArrayList<AGProcess> Processes){
        this.Processes = Processes;
        HistoryQTime = new HashMap<>();
        Que = new ArrayList<>();
        mode = 0;
        InProcess = new HashMap<>();
        for(int i = 0; i < Processes.size(); i++){
            if(!InProcess.containsKey(Processes.get(i).AT)){
                InProcess.put(Processes.get(i).AT, new ArrayList<>());
            }
            InProcess.get(Processes.get(i).AT).add(Processes.get(i));

            if(!HistoryQTime.containsKey(Processes.get(i).PName)){
                HistoryQTime.put(Processes.get(i).PName, new ArrayList<>());
            }
            HistoryQTime.get(Processes.get(i).PName).add(Processes.get(i).QU);
        }
    }
    public void Calculation(){
        int curTime = 0;
        AGProcess AGCurrentProcess = null;
        int timeTaken = 0;
        int totalTime = 0;
        int priorityIndex = -1;
        int lastTime = 0;
        System.out.println("Order of Execution:");
        while (!InProcess.isEmpty() || !Que.isEmpty() || (AGCurrentProcess != null && AGCurrentProcess.BT > 0)){
            if(InProcess.containsKey(curTime)){
                for(int i = 0; i < InProcess.get(curTime).size(); i++){
                    Que.add(InProcess.get(curTime).get(i));
                }
                InProcess.remove(curTime);
            }
            if(AGCurrentProcess == null){
                if(!Que.isEmpty()){
                    AGCurrentProcess = Que.get(0);
                    Que.remove(0);
                    mode = 0;
                }
            }
            if(AGCurrentProcess != null){
                if(AGCurrentProcess.BT == 0){
                    // senario 4
                    AGCurrentProcess.QU = 0;
                    AGCurrentProcess.QQU = 0;
                    HistoryQTime.get(AGCurrentProcess.PName).add(AGCurrentProcess.QU);
                    AGCurrentProcess.CT = curTime;
                    AGCurrentProcess.WT = AGCurrentProcess.CT - AGCurrentProcess.AT - AGCurrentProcess.originalExecution;
                    AGCurrentProcess.TAT = AGCurrentProcess.CT - AGCurrentProcess.AT;
                    System.out.println("Process " + AGCurrentProcess.PName + ": " + lastTime + " to " + curTime);
                    System.out.println();
                    lastTime = curTime;
                    for(int i = 0; i < Processes.size();i++){
                        if(Processes.get(i).PName.equals(AGCurrentProcess.PName)){
                            Processes.set(i, AGCurrentProcess);
                            break;
                        }
                    }
                    if(!Que.isEmpty()){
                        AGCurrentProcess = Que.get(0);
                        Que.remove(0);
                        timeTaken = 0;
                        totalTime = 0;
                        priorityIndex = -1;
                        mode = 0;
                        continue;
                    }
                }
                else if(AGCurrentProcess.QU - totalTime < 0){
                    // senario 1
                    AGCurrentProcess.QU += 2;
                    AGCurrentProcess.QQU = (AGCurrentProcess.QU + 3)/4;
                    Que.add(AGCurrentProcess);
                    HistoryQTime.get(AGCurrentProcess.PName).add(AGCurrentProcess.QU);
                    AGCurrentProcess.CT = curTime;
                    AGCurrentProcess.WT = AGCurrentProcess.CT - AGCurrentProcess.AT - AGCurrentProcess.originalExecution;
                    AGCurrentProcess.TAT = AGCurrentProcess.CT - AGCurrentProcess.AT;
                    System.out.println("Process " + AGCurrentProcess.PName + ": " + lastTime + " to " + curTime);
                    System.out.println();
                    lastTime = curTime;
                    for(int i = 0; i < Processes.size();i++){
                        if(Processes.get(i).PName.equals(AGCurrentProcess.PName)){
                            Processes.set(i, AGCurrentProcess);
                            break;
                        }
                    }
                    AGCurrentProcess = Que.get(0);
                    Que.remove(0);
                    timeTaken = 0;
                    totalTime = 0;
                    priorityIndex = -1;
                    mode = 0;
                    continue;
                }
                else{
                    if(AGCurrentProcess.QQU == timeTaken){
                        mode++;
                        AGCurrentProcess.QQU = (AGCurrentProcess.QU - totalTime + 3)/4;
                        timeTaken = 0;
                    }
                    if(mode == 0){}
                    else if(mode == 1){
                        int tempPriority = AGCurrentProcess.P;
                        for (int i = 0; i < Que.size(); i++) {
                            if (Que.get(i).P < tempPriority) {
                                priorityIndex = i;
                            }
                        }
                        if((timeTaken == 0 || timeTaken + 1 == AGCurrentProcess.QQU) && priorityIndex != -1){
                            // senario 2
                            AGCurrentProcess.QU += (AGCurrentProcess.QU - totalTime + 1) / 2;
                            AGCurrentProcess.QQU = (AGCurrentProcess.QU + 3) / 4;
                            HistoryQTime.get(AGCurrentProcess.PName).add(AGCurrentProcess.QU);
                            Que.add(AGCurrentProcess);
                            AGCurrentProcess.CT = curTime;
                            AGCurrentProcess.WT = AGCurrentProcess.CT - AGCurrentProcess.AT - AGCurrentProcess.originalExecution;
                            AGCurrentProcess.TAT = AGCurrentProcess.CT - AGCurrentProcess.AT;
                            System.out.println("Process " + AGCurrentProcess.PName + ": " + lastTime + " to " + curTime);
                            System.out.println();
                            lastTime = curTime;
                            for(int i = 0; i < Processes.size();i++){
                                if(Processes.get(i).PName.equals(AGCurrentProcess.PName)){
                                    Processes.set(i, AGCurrentProcess);
                                    break;
                                }
                            }
                            AGCurrentProcess = Que.get(priorityIndex);
                            Que.remove(priorityIndex);
                            priorityIndex = -1;
                            timeTaken = 0;
                            totalTime = 0;
                            mode = 0;
                            continue;
                        }
                    }
                    else{
                        int index = -1, minBurstTime = AGCurrentProcess.BT;
                        for (int i = 0; i < Que.size(); i++) {
                            if (Que.get(i).BT < minBurstTime) {
                                index = i;
                            }
                        }
                        if(index != -1){
                            // senario 3
                            AGCurrentProcess.QU += (AGCurrentProcess.QU - totalTime);
                            AGCurrentProcess.QQU = (AGCurrentProcess.QU + 3)/4;
                            HistoryQTime.get(AGCurrentProcess.PName).add(AGCurrentProcess.QU);
                            Que.add(AGCurrentProcess);
                            AGCurrentProcess.CT = curTime;
                            AGCurrentProcess.WT = AGCurrentProcess.CT - AGCurrentProcess.AT - AGCurrentProcess.originalExecution;
                            AGCurrentProcess.TAT = AGCurrentProcess.CT - AGCurrentProcess.AT;
                            System.out.println("Process " + AGCurrentProcess.PName + ": " + lastTime + " to " + curTime);
                            System.out.println();
                            lastTime = curTime;
                            for(int i = 0; i < Processes.size();i++){
                                if(Processes.get(i).PName.equals(AGCurrentProcess.PName)){
                                    Processes.set(i, AGCurrentProcess);
                                    break;
                                }
                            }
                            AGCurrentProcess = Que.get(index);
                            Que.remove(index);
                            timeTaken = 0;
                            totalTime = 0;
                            priorityIndex = -1;
                            mode = 0;
                            continue;
                        }
                    }
                    AGCurrentProcess.BT--;
                    totalTime++;
                    timeTaken++;
                }
            }
            curTime++;
        }
        System.out.println("Process " + AGCurrentProcess.PName + ": " + lastTime + " to " + curTime);
        System.out.println();
        System.out.println("\n Answer: ");
        int sumTurnAroundTime = 0, sumWaitingTime = 0;
        for(int i = 0; i < Processes.size(); i++){
            System.out.println("Process Name: " + Processes.get(i).PName);
            System.out.println("Waiting Time: " + Processes.get(i).WT);
            System.out.println("Turnaround Time: " + Processes.get(i).TAT);
            ArrayList<Integer> history = HistoryQTime.get(Processes.get(i).PName);
            for(int j = 0; j < history.size(); j++){
                System.out.println("Quantum History #" + (j + 1) + " = " + history.get(j));
            }
            sumWaitingTime += Processes.get(i).WT;
            sumTurnAroundTime += Processes.get(i).TAT;
            System.out.println();
        }
        double averageTurnAroundTime = (double) sumTurnAroundTime / Processes.size();
        double averageWaitingTime = (double) sumWaitingTime / Processes.size();
        System.out.println("Total Time Taken: " + (curTime));
        System.out.println("Average Waiting Time: " + averageWaitingTime);
        System.out.println("Average Turn Around Time: " + averageTurnAroundTime);
    }
}