package com.generactive.exception;

public class ExceptionHandler implements Thread.UncaughtExceptionHandler{
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        if (e instanceof FileIsEmptyException) {
            FileIsEmptyException fIsEE= (FileIsEmptyException) e;
            System.out.println(fIsEE.getMessage());
        }
    }
}
