package patterns.mergeInterval;

public class MaxInterval extends Interval{
    private int max;
    public MaxInterval(int start, int end, int max){
        super(start, end);
        this.max = max;

    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }
}
