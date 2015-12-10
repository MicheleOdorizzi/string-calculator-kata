package mo.test;

import java.util.ArrayList;
import java.util.List;

/**
 * Questa classe mette a disposizione il metodo {@link #add(String)} per
 * effettuare delle semplici operazioni di addizione.
 * 
 * @author michele
 *
 */
public class StringCalculator {

    /**
     * Effettua la somma dei numeri interi che vengono passati nel parametro
     * numbers. Questa la logica applicata utilizzando il pattern Compose
     * Method:
     * <ul>
     * <li>se il parametro in ingresso è vuoto, allora il metodo restituisce
     * {@code 0};</li>
     * <li>se la stringa in ingresso contiene le sequenze {@code ',\n'} o
     * {@code '\n,'}, allora viene sollevata un'eccezione;</li>
     * <li>viene determinato il delimitatore che separa i numeri. Se è indicato
     * in maniera esplicita sulla prima riga, allora viene usato questo;
     * altrimenti viene usato il carattere {@code ,} (virgola) o il carattere
     * {@code \n} (newline);</li>
     * <li>viene estratta la lista dei numeri effettivi da sommare utilizzando
     * il separatore determinato in precedenza;</li>
     * <li>viene sollevata un'eccezione se vengono trovati dei numeri negativi;</li>
     * <li>vengono esclusi i <em>big numbers</em>, ovvero quelli superiori a
     * {@code 1000}.
     * <li>i numeri così rimasti vengono sommati.</li>
     * </ul>
     * 
     * @param numbers stringa che contiene una sequenza di numeri interi,
     *        separati da un delimitatore.
     * @return la somma dei numeri presenti in sequenza, ad esclusione dei
     *         <em>big numbers</em>, ovvero dei numeri maggiori di 1000.
     * @throws IllegalArgumentException se la stringa dei numeri interi contiene
     *         delle sequenze non ammesse, oppure se sono stati indicati dei
     *         numeri negativi.
     */
    public int add(String numbers) {

        if (numbers.isEmpty()) {
            return 0;
        }

        throwExceptionIfSequenceNotAdmitted(numbers);
        String delimiter = extractDelimiterFrom(numbers);
        String actualNumbers = getActualNumbersFrom(numbers);
        List<Integer> listOfIntegers = getListOfIntegers(delimiter, actualNumbers);
        throwExceptionIfFoundNegatives(listOfIntegers);
        List<Integer> finalIntegers = excludeBigNumbers(listOfIntegers);
        int sum = getSumOf(finalIntegers);

        return sum;
    }

    private void throwExceptionIfSequenceNotAdmitted(String numbers) {
        if (numbers.contains(",\n")) {
            throw new IllegalArgumentException("La sequenza ',\\n' non è ammessa nell'elenco degli addendi.");
        }

        if (numbers.contains("\n,")) {
            throw new IllegalArgumentException("La sequenza '\\n,' non  è ammessa nell'elenco degli addendi.");
        }
    }

    private String extractDelimiterFrom(String numbers) {
        String delimiter;
        if (numbers.startsWith("//")) {
            delimiter = numbers.substring(2, numbers.indexOf("\n"));
            // Introdotti dei controlli su alcuni caratteri che possono
            // comparire nelle espressioni regolari.
            if ("+".equals(delimiter)) {
                delimiter = "\\+";
            }
            if (".".equals(delimiter)) {
                delimiter = "\\.";
            }
        }
        else {
            delimiter = "[,]|[\n]";
        }
        return delimiter;
    }

    private String getActualNumbersFrom(String numbers) {
        if (numbers.startsWith("//")) {
            return numbers.substring(numbers.indexOf("\n") + 1);
        }
        else {
            return numbers;
        }
    }

    private List<Integer> getListOfIntegers(String delimiter, String actualNumbers) {
        String[] arrayOfNumbers = actualNumbers.split(delimiter);
        List<Integer> listOfIntegers = new ArrayList<Integer>();
        for (int i = 0; i < arrayOfNumbers.length; i++) {
            listOfIntegers.add(Integer.parseInt(arrayOfNumbers[i]));
        }
        return listOfIntegers;
    }

    private void throwExceptionIfFoundNegatives(List<Integer> listOfIntegers) {
        List<Integer> negativesList = new ArrayList<Integer>();
        for (Integer currentInteger : listOfIntegers) {
            if (currentInteger < 0) {
                negativesList.add(currentInteger);
            }
        }
        if (!negativesList.isEmpty()) {
            throw new IllegalArgumentException("negatives not allowed: " + negativesList.toString());
        }
    }

    private List<Integer> excludeBigNumbers(List<Integer> listOfIntegers) {
        List<Integer> result = new ArrayList<Integer>();
        for (Integer currentInteger : listOfIntegers) {
            if (currentInteger <= 1000) {
                result.add(currentInteger);
            }
        }
        return result;
    }

    private int getSumOf(List<Integer> listOfIntegers) {
        int sum = 0;
        for (Integer currentInteger : listOfIntegers) {
            sum += currentInteger;
        }
        return sum;
    }

}
