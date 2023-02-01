import java.util.*;

public class PremPriority implements Calculation{
    int n;
    String name;
    int Atime;
    int Btime;
    int prio;
    int Initial_time=0;
    int Completed_time;
    ArrayList<Process> result=new ArrayList<Process>();
    ArrayList<Process> mProcesses=new ArrayList<Process>();
    ArrayList<Integer> mTimeLine=new ArrayList<Integer>();
    Scanner sc=new Scanner(System.in);
    Scanner sc1=new Scanner(System.in);
    class Mycomparator implements Comparator<Object>
    {
        public int compare(Object o1,Object o2)
        {
            Process p1=(Process)o1;
            Process p2=(Process)o2;
            if(p1.getmArrivalTime()<p2.getmArrivalTime())
                return (-1);
            else if(p1.getmArrivalTime()==p2.getmArrivalTime() && p1.getmPriority()<p2.getmPriority())
                return (-1);
            else
                return (1);
        }
    }
    TreeSet<Process> queue=new TreeSet<Process>(new Mycomparator());
    @Override
    public void Calculation() {
        System.out.print("Enter Number of Process : ");n=sc.nextInt();
        for(int i=0;i<n;i++)
        {
            Process p=new Process();
            System.out.print("Enter Process Name : ");name=sc1.nextLine();
            System.out.print("Enter Arrival Time : "); Atime=sc.nextInt();
            System.out.print("Enter Burst time   : ");Btime=sc.nextInt();
            System.out.print("Enter Priority     : ");prio=sc.nextInt();
            p.setmName(name);
            p.setmArrivalTime(Atime);
            p.setmBurstTime(Btime);
            p.setmPriority(prio);
            queue.add(p);
            mProcesses.add(p);
        }
        Iterator<Process> it = queue.iterator();
        Initial_time = ((Process)queue.first()).getmArrivalTime();
        while(it.hasNext())
        {
            Process p1 = (Process)it.next();
            this.mTimeLine.add(Initial_time);
            Initial_time+=p1.getmBurstTime();
            Completed_time=Initial_time;
            this.mTimeLine.add(Completed_time);
            p1.setmTurnAroundTime(Completed_time-p1.getmArrivalTime());
            p1.setmWaitingTime(p1.getmTurnAroundTime()-p1.getmBurstTime());
//            if(p1.getmWaitingTime()>=10){
            p1.setmPriority(p1.getmPriority()-1);
//            }
            result.add(p1);
        }
        float sumWT=0;
        float sumTAT=0;
        float avgWT=0;
        float avgTAT=0;
        System.out.println(" \t Process \t Arrival \t Burst \t Wait Time \t TurnAroundTime \t Priority ");
        for(int i=0;i<result.size();i++)
        {
            sumWT=sumWT+result.get(i).getmWaitingTime();
            sumTAT=sumTAT+result.get(i).getmTurnAroundTime();
            System.out.println("\t\t"+ result.get(i).getmName()+"\t\t\t"+result.get(i).getmArrivalTime()+"\t\t\t"+result.get(i).getmBurstTime()+"\t\t"+result.get(i).getmWaitingTime()+"\t\t\t\t"+result.get(i).getmTurnAroundTime()+"\t\t\t\t"+result.get(i).getmPriority());
        }
        avgWT=sumWT/n;
        avgTAT=sumTAT/n;
        System.out.print("average waiting time: " + avgWT + "\n");
        System.out.print("average turn around time: " + avgTAT + "\n");
        System.out.println("[ Processes execution order ]");
        for (int i = 0; i < result.size(); i++) System.out.print(result.get(i).getmName() + "  ");
        System.out.println( );
    }
}
