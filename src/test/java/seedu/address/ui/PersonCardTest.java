package seedu.address.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javafx.application.Platform;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

public class PersonCardTest {

    @BeforeAll
    public static void initToolkit() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        try {
            Platform.startup(latch::countDown);
        } catch (IllegalStateException e) {
            // Toolkit already initialized
            latch.countDown();
        }
        latch.await();
    }

    private PersonCard createCardOnFxThread(Person person, int index) throws Exception {
        AtomicReference<PersonCard> cardRef = new AtomicReference<>();
        AtomicReference<Throwable> errorRef = new AtomicReference<>();
        CountDownLatch latch = new CountDownLatch(1);
        Platform.runLater(() -> {
            try {
                cardRef.set(new PersonCard(person, index));
            } catch (Throwable e) {
                errorRef.set(e);
            } finally {
                latch.countDown();
            }
        });
        latch.await();
        if (errorRef.get() != null) {
            throw new RuntimeException(errorRef.get());
        }
        return cardRef.get();
    }

    @Test
    public void display_personWithAllFields_showsCorrectInfo() throws Exception {
        Person person = new PersonBuilder().withName("Alice")
                .withPhone("91234567")
                .withAddress("123 Street")
                .withDay("Monday")
                .withStartTime("10:00")
                .withEndTime("12:00")
                .withRate("50")
                .withTags("Math").build();

        PersonCard card = createCardOnFxThread(person, 1);
        assertEquals("Alice", card.person.getName().fullName);
        assertEquals("91234567", card.person.getPhone().value);
        assertEquals("123 Street", card.person.getAddress().value);
        assertEquals("MONDAY", card.person.getDay().value);
        assertEquals("10:00", card.person.getStartTime().value);
        assertEquals("12:00", card.person.getEndTime().value);
        assertEquals("50", card.person.getRate().value);
    }

    @Test
    public void display_personWithoutOptionalFields_showsCorrectInfo() throws Exception {
        Person person = new Person(
                new PersonBuilder().build().getName(),
                new PersonBuilder().withPhone("81234567").build().getPhone(),
                new PersonBuilder().build().getEmail(),
                new PersonBuilder().withAddress("456 Avenue").build().getAddress(),
                new PersonBuilder().build().getTags());

        PersonCard card = createCardOnFxThread(person, 2);
        assertNull(card.person.getDay());
        assertNull(card.person.getStartTime());
        assertNull(card.person.getEndTime());
        assertNull(card.person.getRate());
    }
}
