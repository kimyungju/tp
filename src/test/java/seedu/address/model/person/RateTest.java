package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class RateTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Rate(null));
    }

    @Test
    public void constructor_invalidRate_throwsIllegalArgumentException() {
        String invalidRate = "";
        assertThrows(IllegalArgumentException.class, () -> new Rate(invalidRate));
    }

    @Test
    public void isValidRate() {
        // null Rate number
        assertThrows(NullPointerException.class, () -> Rate.isValidRate(null));

        // invalid Rate numbers
        assertFalse(Rate.isValidRate(""), "Error: An empty string should not be allowed."); // empty string
        assertFalse(Rate.isValidRate(" "), "Error: Spaces only should not be allowed."); // spaces only
        assertFalse(Rate.isValidRate("45.00"), "Error: Decimal points should not be allowed."); // decimal
        assertFalse(Rate.isValidRate("Rate"), "Error: Non-numeric only should not be allowed."); // non-numeric
        assertFalse(Rate.isValidRate("$40"), "Error: Symbols should not be allowed."); // digits and symbols
        assertFalse(Rate.isValidRate("-40"), "Error: Negative numbers hould not be allowed."); // implied negative

        // valid Rate numbers
        assertTrue(Rate.isValidRate("0"), "Error: 0 should be allowed."); // free
        assertTrue(Rate.isValidRate("40"), "Error: 40 should be allowed.");
        // long tuition rate (may exclude this next time)
        assertTrue(Rate.isValidRate("99999999999999"),
                "Error: 99999999999999 should be allowed.");
    }

    @Test
    public void equals() {
        Rate rate = new Rate("40");

        // same values -> returns true
        assertTrue(rate.equals(new Rate("40")));

        // same object -> returns true
        assertTrue(rate.equals(rate));

        // null -> returns false
        assertFalse(rate.equals(null));

        // different types -> returns false
        assertFalse(rate.equals(40));

        // different values -> returns false
        assertFalse(rate.equals(new Rate("45")));
    }
}
