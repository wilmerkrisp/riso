package life.expert.riso.app;
//@Header@
//--------------------------------------------------------------------------------
//
//                          riso  life.expert.riso.app
//                           wilmer 2019/07/25
//
//--------------------------------------------------------------------------------

/**
 * <pre>
 * Designed for console output to a typical screen or its virtual substitute
 *
 * I make the assumption that the size of the canvas is not more than the length of a single text
 * screen 80 characters , what acceptable to most console users. The following are the corresponding
 * constants: MAX_SCREEN_SIZE, MIN_SCREEN_SIZE
 * </pre>
 */
public interface OutputHelper
	{
	
	/**
	 * The constant MAX_SCREEN_SIZE.
	 */
	public static final int MAX_SCREEN_SIZE = 80;
	
	/**
	 * The constant MIN_SCREEN_SIZE.
	 */
	public static final int MIN_SCREEN_SIZE = 0;
	
	/**
	 * The constant MAX_CANVAS_SIZE.
	 */
	public static final int MAX_CANVAS_SIZE = MAX_SCREEN_SIZE - 1;
	
	/**
	 * The constant MIN_SCREEN_SIZE.
	 */
	public static final int MIN_CANVAS_SIZE = MIN_SCREEN_SIZE + 1;
	
	/**
	 * The character to be output figures by default for console graphics
	 */
	public static final char FIGURE_DEFAULT_CHARACTER = 'x';
	
	/**
	 * The main method of printing
	 *
	 * @param format
	 * 	A format string as described in <a href="../util/Formatter.html#syntax">Format
	 * 	string syntax</a>.
	 * @param args
	 * 	Arguments referenced by the format specifiers in the format         string.  If
	 * 	there are more arguments than format specifiers, the         extra arguments are
	 * 	ignored.  The number of arguments is         variable and may be zero.  The
	 * 	maximum number of arguments is         limited by the maximum dimension of a Java
	 * 	array as defined by         <cite>The Java&trade; Virtual Machine
	 * 	Specification</cite>.         The behaviour on a         {@code null} argument
	 * 	depends on the <a         href="../util/Formatter.html#syntax">conversion</a>.
	 */
	public void print( String format ,
	                   Object... args );
	
	/**
	 * The print message is customized for success
	 *
	 * @param message
	 * 	the message
	 */
	public void printAtSuccess( String message );
	
	/**
	 * The print message is customized for information
	 *
	 * @param message
	 * 	the message
	 */
	public void printAtInfo( String message );
	
	/**
	 * The print message is customized for warning.
	 *
	 * @param message
	 * 	the message
	 */
	public void printAtWarning( String message );
	
	/**
	 * The print message is customized for error
	 *
	 * @param error
	 * 	the error
	 */
	public void printAtError( Throwable error );
	
	/**
	 * Clear the screen
	 */
	public void clear();
	
	/**
	 * To obtain the current screen contents
	 *
	 * @return the buffer
	 */
	public String getBuffer();
	}
