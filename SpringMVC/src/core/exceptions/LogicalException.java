package core.exceptions;

/**
 * 程序处理中出现逻辑错误抛出此异常
 * @author jiazh
 *
 */
public class LogicalException extends BaseRuntimeException {
	private static final long	serialVersionUID	= 1L;

	public LogicalException() {
		super();
	}

	public LogicalException(String message, Throwable rootCause) {
		super(message, rootCause);
	}

	public LogicalException(String message) {
		super(message);
	}

	public LogicalException(Throwable rootCause) {
		super(rootCause);
	}
}
