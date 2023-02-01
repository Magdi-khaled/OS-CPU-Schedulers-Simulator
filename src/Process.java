public class Process implements IProcess{
    String PName;
     int AT;
     int BT;
     int WT;
     int TAT;
     int CT; //(COMPLETE)END TIME
     int RT; //REMANE TIME
     int P;
     int AGF;
    public Process(){}
    public String getmName() {return PName;}
    public void setmName(String mName) {this.PName = mName;}
    public int getmArrivalTime() {return AT;}
    public void setmArrivalTime(int mArrivalTime) {this.AT = mArrivalTime;}
    public int getmBurstTime() {return BT;}
    public void setmBurstTime(int mBurstTime) {this.BT = mBurstTime;}
    public int getmWaitingTime() {return WT;}
    public void setmWaitingTime(int mWaitingTime) {this.WT = mWaitingTime;}
    public int getmTurnAroundTime() {return TAT;}
    public void setmTurnAroundTime(int mTurnAroundTime) {this.TAT = mTurnAroundTime;}
    public int getmCompletionTime() {return CT;}
    public void setmCompletionTime(int mCompletionTime) {this.CT = mCompletionTime;}
    public int getmRemainingTime() {return RT;}
    public void setmRemainingTime(int mRemainingTime) {this.RT = mRemainingTime;}
    public int getmPriority() {return P;}
    public void setmPriority(int mPriority) {this.P = mPriority;}
    public int getmAGFactor() {return AGF;}
    public void setmAGFactor(int mAGFactor) {this.AGF = mAGFactor;}
}
