import java.util.Scanner;

public class RoundRobin implements Calculation{

    public void Calculation() {
        int Quantum,sq=0,temp,i,count=0;
        float Average_wait=0;
        float Average_tur_time=0;
        int[] burst_time=new int[100];
        int[] Wait_TIME=new int[100];
        int[] Time_Tur=new int[100];
        int[] burst_time2=new int[100];
        Scanner scanner=new Scanner(System.in);
        System.out.print("Enter Number of Process : ");
        int n=scanner.nextInt();
        for(i=0;i<n;i++){
            System.out.print("P"+(i+1)+":"+"Burst Time : ");
            burst_time[i]=scanner.nextInt();
            burst_time2[i]=burst_time[i];
        }
        System.out.print("Enter the Quantum Time : ");
        Quantum=scanner.nextInt();
        while(true)
        {
            for (i=0,count=0;i<n;i++)
            {
                temp = Quantum;
                if(burst_time2[i] == 0)
                {
                    count++;
                    continue;
                }
                if(burst_time2[i]>Quantum)
                    burst_time2[i]= burst_time2[i] - Quantum;
                else {
                    if (burst_time2[i] >= 0) {
                        temp = burst_time2[i];
                        burst_time2[i] = 0;
                    }
                }
                sq = sq + temp;
                Time_Tur[i] = sq;
            }
            if(n == count)
                break;
        }

        System.out.println(" \t Process \t BurstTime \t TurnAroundTime \t WaitingTime");
        for(i=0;i<n;i++)
        {
            Wait_TIME[i]=Time_Tur[i]-burst_time[i];
            Average_wait=Average_wait+Wait_TIME[i];
            Average_tur_time=Average_tur_time+Time_Tur[i];
            System.out.println("\t\tP"+(i+1)+"\t\t\t"+burst_time[i]+"\t\t\t"+Time_Tur[i]+"\t\t\t\t\t"+Wait_TIME[i]);
        }
        Average_wait=Average_wait/n;
        Average_tur_time=Average_tur_time/n;
        System.out.println("\nAverage waiting Time = "+Average_wait);
        System.out.println("Average turnaround time = "+Average_tur_time);
    }
}
