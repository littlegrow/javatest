package pattern.behavior.interpreter;

public class SevenDivisible implements IDivisible {
    @Override
    public boolean divisible(int number) {
        if (number % 7 == 0) {
            return true;
        }
        return false;
    }
}
