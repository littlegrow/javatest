package pattern.behavior.interpreter;

public class ElevenDivisible implements IDivisible {
    @Override
    public boolean divisible(int number) {
        if (number % 11 == 0) {
            return true;
        }
        return false;
    }
}
