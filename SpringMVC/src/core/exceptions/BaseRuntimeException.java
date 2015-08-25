package core.exceptions;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class BaseRuntimeException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/** 换行符（用于异常信息的格式排版） */
	private static final String	lnSpt				= System.getProperty("line.separator");

	/** 缩进（用于异常信息的格式排版） */
	private static final String	indtn				= "\t";

	/** 为多样化异常提供支持 */
	private List<Throwable>		rootExceptions		= new ArrayList<Throwable>();

	/** 为国际化保留 */
	private String				messageKey			= null;

	/** 为国际化保留 */
	private Object[]			messageArgs			= null;

	public BaseRuntimeException() {
		super();
	}

	/**
	 * 这个构造函数在包装异常的时候将会把被包装的异常添加到根异常集中
	 * 
	 * @param rootCause
	 */
	public BaseRuntimeException(Throwable rootCause) {
		super(rootCause);
		rootExceptions.add(rootCause);
	}

	/**
	 * 这个构造函数在包装异常的时候将会把被包装的异常添加到根异常集中
	 * 
	 * @param message
	 * @param rootCause
	 */
	public BaseRuntimeException(String message, Throwable rootCause) {
		super(message, rootCause);
		rootExceptions.add(rootCause);
	}

	public BaseRuntimeException(String message) {
		super(message);
	}

	/**
	 * 这个方法在对异常进行处理分析的时候可能会用到
	 * 
	 * @return
	 */
	public List<Throwable> getRootExceptions() {
		return rootExceptions;
	}

	/**
	 * 这个方法可以作为initCause方法的替代，它们影响不同，此方法功能更强，它可以达到initCause方法的效果，<br>
	 * 同时可以填充多样化异常数组，但是initCause只有初始化根异常的作用，并没有填充多样化异常数组的功能，但<br>
	 * 可以在调用initCause方法后再调用addException方法，但这与直接调用addException方法产生的效果一样。
	 * 
	 * @param ex
	 */
	public synchronized void addRootException(Throwable ex) {
		if (getCause() == null && rootExceptions.size() < 1) {
			initCause(ex);
		}
		rootExceptions.add(ex);
	}

	public void setMessageKey(String key) {
		this.messageKey = key;
	}

	public String getMessageKey() {
		return messageKey;
	}

	public void setMessageArgs(Object[] args) {
		this.messageArgs = args;
	}

	public Object[] getMessageArgs() {
		return messageArgs;
	}

	public void printStackTrace() {
		printStackTrace(System.err);
	}

	public void printStackTrace(PrintStream outStream) {
		printStackTrace(new PrintWriter(outStream));
	}

	public void printStackTrace(PrintWriter writer) {
		writer.append(lnSpt);// 先输出此异常信息所需要的头信息（仅输出一个换行）
		// 接着输出此异常保留的堆栈信息
		StackTraceElement[] elements = onlyPrintStackTrace(writer, this, 0);
		// 迭代输出根异常集rootExceptions的信息
		if (rootExceptions.size() > 0) {
			printRootExceptions(writer, rootExceptions, elements.length - 1);
		}

		writer.flush();
	}

	/**
	 * 这个方法主要是将一个给定的异常中栈信息输出，输出的格式是这样的:先输出这个异常的类名，紧接着输出一个冒号<br>
	 * 再输出这个异常的详细信息，然后换行缩进输出栈中每条具体的信息。栈中每条信息的格式为：以at:开头，然后是栈<br>
	 * 信息中保存的类名、方法名、文件名、调用行号。
	 * 
	 * @param writer
	 * @return
	 */
	protected StackTraceElement[] onlyPrintStackTrace(PrintWriter writer, Throwable exception, int b) {
		String t = lnSpt + indtn;
		StackTraceElement[] elements = exception.getStackTrace();
		writer.append(exception.getClass().getName() + ": " + exception.getMessage());
		for (int i = 0; i < elements.length - b; i++) {
			writer.append(t + "at " + elements[i].getClassName() + "." + elements[i].getMethodName() + "("
				+ elements[i].getFileName() + ":" + elements[i].getLineNumber() + ")");
		}
		// 主要针对根异常类型的信息进行特别处理
		if (b > 0) {
			writer.append(t + "... " + b + " more");
		}
		return elements;
	}

	protected void printRootExceptions(PrintWriter writer, List<Throwable> rootExceptions, int b) {
		int i = 1;
		for (Iterator<Throwable> iter = rootExceptions.iterator(); iter.hasNext();) {
			Throwable rootException = (Throwable) iter.next();
			writer.append(lnSpt + "Caused By " + (i++) + " ");// 先输出根异常信息的开头
			// 接着输出异常的保留的堆栈信息
			StackTraceElement[] stackTraceElements = onlyPrintStackTrace(writer, rootException, b);

			// 如果此根异常是一个BaseException，那么迭代输出此根异常的根异常集rootExceptions
			if (rootException instanceof BaseException) {
				BaseException newRootException = (BaseException) rootException;
				printRootExceptions(writer, newRootException.getRootExceptions(), stackTraceElements.length - 1);
			} else if (rootException instanceof BaseRuntimeException) {// 如果此根异常是一个BaseRuntimeException，那么迭代输出此根异常的根异常集rootExceptions
				BaseRuntimeException newRootException = (BaseRuntimeException) rootException;
				printRootExceptions(writer, newRootException.getRootExceptions(), stackTraceElements.length - 1);
			} else {// 如果此根异常不是BaseException类型，则它就没有根异常集rootExceptions
				// 如果没有根异常集就看它有没有原始的根异常，如果有就把它包装成一个根异常集newRootException再输出
				if (rootException.getCause() != null) {
					List<Throwable> newRootException = new ArrayList<Throwable>();
					newRootException.add(rootException.getCause());
					printRootExceptions(writer, newRootException, stackTraceElements.length - 1);
				}
			}
		}
	}

}
