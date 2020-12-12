package ru.otus.exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/*
 * @created 12/12 - otus-spring
 * @author Ilya Rogatkin
 */
@RequiredArgsConstructor
public class NotFoundException extends RuntimeException {

    @Getter
    private final String message;

}
