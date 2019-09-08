package life.expert.riso;



import life.expert.common.async.LogUtils;
import life.expert.riso.app.OutputHelper;
import lombok.extern.slf4j.Slf4j;

/**
 * Spy for recording all output to the console
 */
//@Header@
//--------------------------------------------------------------------------------
//
//                          riso  life.expert.riso
//                           wilmer 2019/07/25
//
//--------------------------------------------------------------------------------
@Slf4j
public final class TestFunctionalHelper
	implements OutputHelper
	{
	
	StringBuilder terminal = new StringBuilder();
	
	@Override
	public void printAtSuccess( String message )
		{
		print( message );
		}
	
	@Override
	public void print( String format ,
	                   Object... args )
		{
		terminal.append( String.format( format , args ) );
		LogUtils.print( format );
		}
	
	@Override
	public void printAtInfo( String message )
		{
		print( message );
		}
	
	@Override
	public void printAtWarning( String message )
		{
		print( "Warning: %s" , message );
		}
	
	@Override
	public void printAtError( Throwable error )
		{
		print( "Error: %s" , error );
		}
	
	@Override
	public void clear()
		{
		terminal.setLength( 0 );
		}
	
	@Override
	public String getBuffer()
		{
		return terminal.toString();
		}
	}
