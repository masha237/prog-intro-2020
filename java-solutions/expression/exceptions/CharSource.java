package expression.exceptions;

import expression.parser.ParseException;

/**
 * @author Georgiy Korneev (kgeorgiy@kgeorgiy.info)
 */
public interface CharSource {
    boolean hasNext();
    char next();
    ParseException error(final String message);
}
