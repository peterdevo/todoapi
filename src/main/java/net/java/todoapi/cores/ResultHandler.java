package net.java.todoapi.cores;

public class ResultHandler<T> {

    boolean isSuccess;
    T value;
    String error;

    public ResultHandler(boolean isSuccess, T value) {
        this.isSuccess = isSuccess;
        this.value = value;

    }

    public ResultHandler(boolean isSuccess, String error) {
        this.isSuccess = isSuccess;
        this.error = error;

    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public static <T> ResultHandler<T> success(T value) {
        return new ResultHandler<T>(true, value);
    }

    public static <T> ResultHandler<T> failure(String error) {
        return new ResultHandler<T>(false, error);
    }
}
