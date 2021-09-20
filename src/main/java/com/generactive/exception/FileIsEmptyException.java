package com.generactive.exception;

public class FileIsEmptyException extends RuntimeException {

    private String path;

    public FileIsEmptyException() {
        super("File is empty");
    }

    public FileIsEmptyException(String filePath) {
        super(String.format("File in path :  {%s}  - Is Empty", filePath));
        this.path = filePath;
    }

    public FileIsEmptyException(String filePath, Throwable cause) {
        super(String.format("File in path :  {%s}  - Is Empty", filePath), cause);
        this.path = filePath;
    }
}
