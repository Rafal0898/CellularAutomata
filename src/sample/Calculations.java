package sample;

public class Calculations {
    public static boolean[] calculateNextTimeStep(boolean[] currentTimeStep, int rule, boolean ifPeriodicBoundaryConditions) {
        int length = currentTimeStep.length;
        boolean[] nextGeneration = new boolean[length];
        boolean[] ruleset = new boolean[8];

        String bin = Integer.toBinaryString(rule);
        while (bin.length() < 8) {
            bin = "0" + bin;
        }
        for (int i = 0; i < 8; i++) {
            if (String.valueOf(bin.charAt(i)).equals("0")) {
                ruleset[7 - i] = false;
            } else {
                ruleset[7 - i] = true;
            }
        }
        if (ifPeriodicBoundaryConditions) {
            String bitValue = returnPositionInBinary(currentTimeStep[length - 1], currentTimeStep[0], currentTimeStep[1]);
            nextGeneration[0] = ruleset[Integer.parseInt(bitValue, 2)];
            bitValue = returnPositionInBinary(currentTimeStep[length - 2], currentTimeStep[length - 1], currentTimeStep[0]);
            nextGeneration[length - 1] = ruleset[Integer.parseInt(bitValue, 2)];
        }
        for (int i = 1; i < currentTimeStep.length - 1; i++) {
            String bitValue = returnPositionInBinary(currentTimeStep[i - 1], currentTimeStep[i], currentTimeStep[i + 1]);
            nextGeneration[i] = ruleset[Integer.parseInt(bitValue, 2)];
        }
        return nextGeneration;
    }

    private static String returnPositionInBinary(boolean left, boolean middle, boolean right) {
        String binValue = "";
        if (left) binValue += "1";
        else binValue += "0";

        if (middle) binValue += "1";
        else binValue += "0";

        if (right) binValue += "1";
        else binValue += "0";

        return binValue;
    }
}
