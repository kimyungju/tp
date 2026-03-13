package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TimeTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Time(null));
    }

    @Test
    public void constructor_invalidTime_throwsIllegalArgumentException() {
        String invalidTime = "Not a Time";
        assertThrows(IllegalArgumentException.class, () -> new Time(invalidTime));
    }

    @Test
    public void isValidTime() {
        // invalid Time numbers
        assertFalse(Time.isValidTime("")); // empty string
        assertFalse(Time.isValidTime(" ")); // spaces only
        assertFalse(Time.isValidTime("91")); // numeric
        assertFalse(Time.isValidTime("Time")); // non-numeric
        assertFalse(Time.isValidTime("Six30")); // alphabets within digits
        assertFalse(Time.isValidTime("18 30")); // spaces within characters
        assertFalse(Time.isValidTime("1830")); // missing ":"
        assertFalse(Time.isValidTime("18:60")); // numbers do not following 24 hours format
        assertFalse(Time.isValidTime("24:00")); // numbers do not following 24 hours format
        assertFalse(Time.isValidTime("25:00")); // numbers do not following 24 hours format

        // valid Times
        assertTrue(Time.isValidTime("00:00"));
        assertTrue(Time.isValidTime("08:00"));
        assertTrue(Time.isValidTime("13:00"));
        assertTrue(Time.isValidTime("17:30"));
        assertTrue(Time.isValidTime("23:59"));

    }

    @Test
    public void equals() {
        Time time = new Time("11:30");

        // same values -> returns true
        assertTrue(time.equals(new Time("11:30")));

        // same object -> returns true
        assertTrue(time.equals(time));

        // null -> returns false
        assertFalse(time.equals(null));

        // different types -> returns false
        assertFalse(time.equals(5.0f));

        // different values -> returns false
        assertFalse(time.equals(new Time("23:59")));
    }
}
