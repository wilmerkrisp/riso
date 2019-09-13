package life.expert.riso.app;



import lombok.extern.slf4j.Slf4j;
import org.jline.terminal.Terminal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

//@Header@
//--------------------------------------------------------------------------------
//
//                          riso  life.expert.riso.app
//                           wilmer 2019/07/21
//
//--------------------------------------------------------------------------------

/**
 * <pre>
 * Output to the console terminal object
 *
 * Preconditions: none
 * Postconditions: none
 * Side effects: output on the terminal object
 * Tread safety: Not thread-safe
 * </pre>
 */
@Slf4j
@Component( "shellHelper" )
public final class ShellOutputHelper
	implements OutputHelper
	{
	
	private final Terminal terminal;
	
	/**
	 * Instantiates a new Shell output helper.
	 *
	 * @param terminal
	 * 	the terminal
	 */
	@Autowired
	public ShellOutputHelper( @Lazy Terminal terminal )
		{
		this.terminal = terminal;
		}
	
	@Override
	public void printAtSuccess( String message )
		{
		
		print( "Success: %s" , message );
		}
	
	@Override
	public void print( String format ,
	                   Object... args )
		{
		terminal.writer()
		        .printf( format , args );
		terminal.writer()
		        .println();
		terminal.flush();
		}
	
	@Override
	public void printAtInfo( String message )
		{
		print( "Info: %s" , message );
		}
	
	@Override
	public void printAtWarning( String message )
		{
		print( "Warning: %s" , message );
		}
	
	@Override
	public void printAtError( Throwable error )
		{
		print( "Error: %s" , error.getMessage() );
		}
	
	@Override
	public void clear()
		{
		terminal.flush();
		}
	
	@Override
	public String getBuffer()
		{
		return terminal.toString();
		}
		
	}
