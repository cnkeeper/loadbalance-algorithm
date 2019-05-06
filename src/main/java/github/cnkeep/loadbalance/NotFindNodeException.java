package github.cnkeep.loadbalance;

/**
 * 描述~
 *
 * @Author <a href="zhangleili924@gmail.com">LeiLi.Zhang</a>
 * @Version 0.0.0
 * @Date 2019/4/28
 */
public class NotFindNodeException extends Exception {
    public NotFindNodeException() {
        super();
    }

    public NotFindNodeException(String message) {
        super(message);
    }

    public NotFindNodeException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotFindNodeException(Throwable cause) {
        super(cause);
    }

    protected NotFindNodeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
