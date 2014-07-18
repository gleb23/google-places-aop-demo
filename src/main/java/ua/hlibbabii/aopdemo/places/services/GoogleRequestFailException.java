package ua.hlibbabii.aopdemo.places.services;

/**
 * Created by hlib on 7/19/14.
 */
public class GoogleRequestFailException extends RuntimeException {
    private String status;

    public GoogleRequestFailException(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
