package notation;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ReversePolishNotationCalculatorTest {

    private static ReversePolishNotationCalculator polishNotationCalculator;

    @BeforeAll
    public static void setup() {
        polishNotationCalculator = new ReversePolishNotationCalculator();
    }

    @Test
    public void shouldCalculateAdditionWithPositiveOperands() {
        String notationString = "1 2 +";
        int result = polishNotationCalculator.calculatePolishNotation(notationString);
        assertEquals(3, result, "Ошибка при выполнении операци сложения");
    }

    @Test
    public void shouldCalculateAdditionWithNegativeOperands() {
        String notationString = "-1 -2 +";
        int result = polishNotationCalculator.calculatePolishNotation(notationString);
        assertEquals(-3, result, "Ошибка при выполнении операци сложения");
    }

    @Test
    public void shouldCalculateAdditionWithPositiveAndNegativeOperands() {
        String notationString = "-1 +2 +";
        int result = polishNotationCalculator.calculatePolishNotation(notationString);
        assertEquals(1, result, "Ошибка при выполнении операци сложения");
    }

    @Test
    public void shouldCalculateSubtractionWithPositiveOperands() {
        String notationString = "2 2 -";
        int result = polishNotationCalculator.calculatePolishNotation(notationString);
        assertEquals(0, result, "Ошибка при выполнении операци вычитания");
    }

    @Test
    public void shouldCalculateSubtractionWithNegativeOperands() {
        String notationString = "-2 -2 -";
        int result = polishNotationCalculator.calculatePolishNotation(notationString);
        assertEquals(0, result, "Ошибка при выполнении операци вычитания");
    }

    @Test
    public void shouldCalculateSubtractionWithPositiveAndNegativeOperands() {
        String notationString = "2 -2 -";
        int result = polishNotationCalculator.calculatePolishNotation(notationString);
        assertEquals(4, result, "Ошибка при выполнении операци вычитания");
    }

    @Test
    public void shouldCalculateMultiplicationWithPositiveOperands() {
        String notationString = "2 2 *";
        int result = polishNotationCalculator.calculatePolishNotation(notationString);
        assertEquals(4, result, "Ошибка при выполнении операци умножения");
    }

    @Test
    public void shouldCalculateMultiplicationWithNegativeOperands() {
        String notationString = "-2 -2 *";
        int result = polishNotationCalculator.calculatePolishNotation(notationString);
        assertEquals(4, result, "Ошибка при выполнении операци умножения");
    }

    @Test
    public void shouldCalculateMultiplicationWithPositiveAndNegativeOperands() {
        String notationString = "-2 2 *";
        int result = polishNotationCalculator.calculatePolishNotation(notationString);
        assertEquals(-4, result, "Ошибка при выполнении операци умножения");
    }

    @Test
    public void shouldCalculateAdditionAndSubtraction() {
        String notationString = "6 5 1 + -";
        int result = polishNotationCalculator.calculatePolishNotation(notationString);
        assertEquals(0, result, "Ошибка при выполнении операци сложения и вычитания");
    }

    @Test
    public void shouldCalculateSubtractionAndAddition() {
        String notationString = "6 5 1 - +";
        int result = polishNotationCalculator.calculatePolishNotation(notationString);
        assertEquals(10, result, "Ошибка при выполнении операци выитания и сложения");
    }

    @Test
    public void shouldCalculateAdditionAndMultiplication() {
        String notationString = "6 5 1 + *";
        int result = polishNotationCalculator.calculatePolishNotation(notationString);
        assertEquals(36, result, "Ошибка при выполнении операци сложения и умножения");
    }

    @Test
    public void shouldCalculateMultiplicationAndAddition() {
        String notationString = "6 5 1 * +";
        int result = polishNotationCalculator.calculatePolishNotation(notationString);
        assertEquals(11, result, "Ошибка при выполнении операци умножения и сложения");
    }

    @Test
    public void shouldCalculateSubtractionAndMultiplication() {
        String notationString = "6 5 1 - *";
        int result = polishNotationCalculator.calculatePolishNotation(notationString);
        assertEquals(24, result, "Ошибка при выполнении операци вычитания умножения");
    }

    @Test
    public void shouldCalculateMultiplicationAndSubtraction() {
        String notationString = "6 5 1 * -";
        int result = polishNotationCalculator.calculatePolishNotation(notationString);
        assertEquals(1, result, "Ошибка при выполнении операци умножения и вычитания");
    }

    @Test
    public void shouldCalculateAllAction() {
        String notationString = "6 5 1 4 * - +";
        int result = polishNotationCalculator.calculatePolishNotation(notationString);
        assertEquals(7, result, "Ошибка при выполнении нескольких");
    }

    @Test
    public void calculationWithoutSpacesInTheActionsPartShouldThrowException() {
        String notationString = "6 5 1 4 *-+";
        NumberFormatException ex = assertThrows(NumberFormatException.class,
                () -> polishNotationCalculator.calculatePolishNotation(notationString));
    }

    @Test
    public void calculationWithoutSpacesInTheOperandsPartShouldThrowException() {
        String notationString = "6514 * - +";
        assertThrows(NoSuchElementException.class,
                () -> polishNotationCalculator.calculatePolishNotation(notationString));
    }

    @Test
    public void calculationWithoutSpacesBetweenOperandsAndActionsParts() {
        String notationString = "6 5 1 4* - +";
        assertThrows(NumberFormatException.class,
                () -> polishNotationCalculator.calculatePolishNotation(notationString));
    }

    @Test
    public void calculationWithSeveralSpacesInTheOperandsPartSuccess() {
        String notationString = "6     5    1          4 * - +";
        int result = polishNotationCalculator.calculatePolishNotation(notationString);
        assertEquals(7, result, "Ошибка при наличии нескольких пробелов в операндах");
    }

    @Test
    public void calculationWithSeveralSpacesInTheActionsPartSuccess() {
        String notationString = "6 5 1 4 *   -               +";
        int result = polishNotationCalculator.calculatePolishNotation(notationString);
        assertEquals(7, result, "Ошибка при наличии нескольких пробелов в дейсвтиях");
    }

    @Test
    public void calculationWithSeveralSpacesBetweenActionsAndOperandPartsSuccess() {
        String notationString = "6 5 1 4          * - +";
        int result = polishNotationCalculator.calculatePolishNotation(notationString);
        assertEquals(7, result, "Ошибка при наличии нескольких пробелов между операндами и действиями");
    }
}
