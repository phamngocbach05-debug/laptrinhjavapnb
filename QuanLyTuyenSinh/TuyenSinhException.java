package GTS;

// Exception co so cho toan bo he thong Quan ly Tuyen sinh
public class TuyenSinhException extends Exception {
    public TuyenSinhException(String message) {
        super(message);
    }

    public TuyenSinhException(String message, Throwable cause) {
        super(message, cause);
    }
}
