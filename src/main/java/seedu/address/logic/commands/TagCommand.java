package seedu.address.logic.commands;

import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;


public class TagCommand {
    public static final String COMMAND_WORD = "tag";
    
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a tag to a person in the address book. "
            + "Parameters: "
            + "INDEX (must be a positive integer) "
            + PREFIX_TAG + "TAG (must be a non-empty string)\n"
            + "Example: " + COMMAND_WORD + " 1 friend";

}
