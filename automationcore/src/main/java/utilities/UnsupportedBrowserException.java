package utilities;

public class UnsupportedBrowserException extends Exception {

	public UnsupportedBrowserException() {
		super("Supplied browser type is not supported");
	}
}
