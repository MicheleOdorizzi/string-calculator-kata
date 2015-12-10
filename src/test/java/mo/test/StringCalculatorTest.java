package mo.test;

import java.util.Random;

import org.junit.Assert;
import org.junit.Test;

public class StringCalculatorTest {

    @Test
    public void testSumEmptyString() {
        StringCalculator stringCalculator = new StringCalculator();
        int result = stringCalculator.add("");
        Assert.assertEquals("Il risultato atteso è 0.", 0, result);
    }

    @Test
    public void testSumSingleFixedAddend() {
        StringCalculator stringCalculator = new StringCalculator();
        int result = stringCalculator.add("2");
        Assert.assertEquals("Il risultato atteso è 2.", 2, result);
    }

    @Test
    public void testSumSingleRandomAddend() {
        StringCalculator stringCalculator = new StringCalculator();
        int randomAddend = new Random().nextInt(100);
        int result = stringCalculator.add(String.valueOf(randomAddend));
        Assert.assertEquals("Il risultato atteso è " + randomAddend + ".", randomAddend, result);
    }

    @Test
    public void testSumTwoFixedAddends() {
        StringCalculator stringCalculator = new StringCalculator();
        int result = stringCalculator.add("2,1");
        Assert.assertEquals("Il risultato atteso è 3.", 3, result);
    }

    @Test
    public void testSumTwoRandomAddends() {
        StringCalculator stringCalculator = new StringCalculator();
        int firstRandomAddend = new Random().nextInt(100);
        int secondRandomAddend = new Random().nextInt(100);
        int expectedResult = firstRandomAddend + secondRandomAddend;
        Assert.assertEquals("Il risultato atteso è " + expectedResult + ".", expectedResult, stringCalculator.add(String.valueOf(firstRandomAddend) + "," + String.valueOf(secondRandomAddend)));
    }

    @Test
    public void testSumThreeRandomAddends() {
        StringCalculator stringCalculator = new StringCalculator();
        int firstRandomAddend = new Random().nextInt(100);
        int secondRandomAddend = new Random().nextInt(100);
        int thirdRandomAddend = new Random().nextInt(100);
        int expectedResult = firstRandomAddend + secondRandomAddend + thirdRandomAddend;
        Assert.assertEquals("Il risultato atteso è " + expectedResult + ".", expectedResult, stringCalculator.add(String.valueOf(firstRandomAddend) + "," + String.valueOf(secondRandomAddend) + "," + String.valueOf(thirdRandomAddend)));
    }

    @Test
    public void testSumSingleFixedAddendWithComma() {
        StringCalculator stringCalculator = new StringCalculator();
        int result = stringCalculator.add("2,");
        Assert.assertEquals("Il risultato atteso è 2.", 2, result);
    }

    @Test
    public void testSumSingleFixedAddendWithNewline() {
        StringCalculator stringCalculator = new StringCalculator();
        int result = stringCalculator.add("3\n");
        Assert.assertEquals("Il risultato atteso è 3.", 3, result);
    }

    @Test
    public void testHandleNewlineAndCommaAsSeparators() {
        StringCalculator stringCalculator = new StringCalculator();
        int result = stringCalculator.add("1\n2,3");
        Assert.assertEquals("Il risultato atteso è 6.", 6, result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testHandleCommaAndNewlineAsJoinedSeparator() {
        StringCalculator stringCalculator = new StringCalculator();
        stringCalculator.add("1,\n");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testHandleNewlineAndCommaAsJoinedSeparator() {
        StringCalculator stringCalculator = new StringCalculator();
        stringCalculator.add("1\n,");
    }

    @Test
    public void testHandleSemicolonAsDelimiter() {
        StringCalculator stringCalculator = new StringCalculator();
        int result = stringCalculator.add("//;\n1;2");
        Assert.assertEquals("Il risultato atteso è 3.", 3, result);
    }

    @Test
    public void testHandlePlusCharAsDelimiter() {
        StringCalculator stringCalculator = new StringCalculator();
        int result = stringCalculator.add("//+\n2+3");
        Assert.assertEquals("Il risultato atteso è 5.", 5, result);
    }

    @Test
    public void testHandleDotCharAsDelimiter() {
        StringCalculator stringCalculator = new StringCalculator();
        int result = stringCalculator.add("//.\n2.3");
        Assert.assertEquals("Il risultato atteso è 5.", 5, result);
    }

    @Test
    public void testHandleSingleFixedNegativeNumber() {
        try {
            StringCalculator stringCalculator = new StringCalculator();
            stringCalculator.add("1,4,-1");
            Assert.fail("Doveva essere sollevata un'eccezione.");
        }
        catch (RuntimeException e) {
            Assert.assertEquals("negatives not allowed: [-1]", e.getMessage());
        }
    }

    @Test
    public void testHandleTwoFixedNegativeNumbers() {
        try {
            StringCalculator stringCalculator = new StringCalculator();
            stringCalculator.add("1,-4,-1");
            Assert.fail("Doveva essere sollevata un'eccezione.");
        }
        catch (RuntimeException e) {
            Assert.assertEquals("negatives not allowed: [-4, -1]", e.getMessage());
        }
    }

    @Test
    public void testIgnoreBigNumber() {
        StringCalculator stringCalculator = new StringCalculator();
        int result = stringCalculator.add("//;\n2;1002");
        Assert.assertEquals("Il risultato atteso è 2.", 2, result);
    }

    @Test
    public void testAllBigNumbers() {
        StringCalculator stringCalculator = new StringCalculator();
        int result = stringCalculator.add("//;\n1001;1002;1003");
        Assert.assertEquals("Il risultato atteso è 0.", 0, result);
    }
}
