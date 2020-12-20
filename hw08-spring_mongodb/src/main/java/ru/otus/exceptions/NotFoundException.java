package ru.otus.exceptions;

import lombok.RequiredArgsConstructor;

/*
 * @created 19/12 - otus-spring
 * @author Ilya Rogatkin
 */
@RequiredArgsConstructor
public class NotFoundException extends Throwable {
    private final String message;
}
