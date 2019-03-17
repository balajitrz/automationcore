package utilities;

public class ThreadContextHolder {
	private ThreadContextHolder() {
	}
	
	private static ThreadLocal<ThreadConfig> threadContext = ThreadLocal.withInitial(() -> new ThreadConfig());
	
	public static ThreadConfig getThreadContext() {
		return threadContext.get();
	}
	
	public static void removeThreadContext() {
		threadContext.remove();
	}
	
}
