package guessme;

public class BcCount {
    public int bullCount = 0;
    public int cowCount = 0;

    public BcCount(int b, int c) {
        bullCount = b;
        cowCount = c;
    }

    public String toString() {
        return bullCount + " " + cowCount;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        BcCount other = (BcCount) obj;
        return bullCount == other.bullCount && cowCount == other.cowCount;
    }
}
