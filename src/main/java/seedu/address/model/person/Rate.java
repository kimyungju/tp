package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's tuition rate in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidRate(String)}
 */
public class Rate {

    public static final String MESSAGE_CONSTRAINTS =
            "Tuition Rate should only contain numbers (non-negative integers).";

    public static final String VALIDATION_REGEX = "^\\d+$";

    public final String value;

    /**
     * Constructs a {@code Tuition Rate}.
     *
     * @param tuitionRate A valid tuition rate.
     */
    public Rate(String tuitionRate) {
        requireNonNull(tuitionRate);
        checkArgument(isValidRate(tuitionRate), MESSAGE_CONSTRAINTS);
        value = tuitionRate;
    }

    /**
     * Returns true if a given string is a valid tuition rate.
     */
    public static boolean isValidRate(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Rate)) {
            return false;
        }

        Rate otherRate = (Rate) other;
        return value.equals(otherRate.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
