package pattern.behavior.interpreter;

public class FiveDivisible implements IDivisible {
    @Override
    public boolean divisible(int number) {
        if (number % 5 == 0) {
            return true;
        }
        return false;
    }
}
