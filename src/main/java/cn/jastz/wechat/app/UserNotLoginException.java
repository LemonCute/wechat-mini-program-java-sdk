package cn.jastz.wechat.app;

/**
 * @author zhiwen
 */
public class UserNotLoginException extends RuntimeException {

    public UserNotLoginException() {
    }

    public UserNotLoginException(String message) {
        super(message);
    }

    public UserNotLoginException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserNotLoginException(Throwable cause) {
        super(cause);
    }
}
