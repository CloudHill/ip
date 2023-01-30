package sam.parser;

import sam.SamException;

/**
 * A SamException thrown when an error occurs during parsing a date.
 */
public class SamInvalidDateException extends SamException {
    public SamInvalidDateException() {
        super("Please write dates as 'd/M/yyyy'!");
    }
}
