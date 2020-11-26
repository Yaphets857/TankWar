package com.yaphets.exception;

/**
 * @author gszqy
 * @date 17:27 2020/11/26
 */
public class GameException extends RuntimeException {

    private static final long serialVersionUID = -5009552354540044678L;

    public GameException() {

    }

    public GameException(String message) {
        super(message);
    }
}
