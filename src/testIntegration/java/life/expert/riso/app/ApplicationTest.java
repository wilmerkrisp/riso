package life.expert.riso.app;
//@Header@
//--------------------------------------------------------------------------------
//
//                          riso  life.expert.riso.app
//                           wilmer 2019/07/21
//
//--------------------------------------------------------------------------------

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import org.junit.Test;

/**<pre>
 * Integration test uses pure java Runtime.exec  to run the application
 *
 * Preconditions: need to build jar and put in the folder
 * Postconditions: passed or failed tests
 * Side effects: io, runned and terminated process in an OS
 * Tread safety: Not thread-safe
 </pre>*/
public class ApplicationTest
	{
	
	/**
	 * Integration Test.
	 *
	 * @throws IOException
	 * 	the io exception
	 */
	@Test
	public void test()
	throws IOException
		{
		//@formatter:off
		
		String verified_ethalon = "██████╗ ██████╗  █████╗ ██╗    ██╗██╗███╗   ██╗ ██████╗\n" +
		                          "██╔══██╗██╔══██╗██╔══██╗██║    ██║██║████╗  ██║██╔════╝\n" +
		                          "██║  ██║██████╔╝███████║██║ █╗ ██║██║██╔██╗ ██║██║  ███╗\n" +
		                          "██║  ██║██╔══██╗██╔══██║██║███╗██║██║██║╚██╗██║██║   ██║\n" +
		                          "██████╔╝██║  ██║██║  ██║╚███╔███╔╝██║██║ ╚████║╚██████╔╝\n" +
		                          "╚═════╝ ╚═╝  ╚═╝╚═╝  ╚═╝ ╚══╝╚══╝ ╚═╝╚═╝  ╚═══╝ ╚═════╝\n" +
		                          "                                                 (v1.0)\n" +
		                          "\n" +
		                          "enter command: ---\n" +
		                          "| |\n" +
		                          "---\n" +
		                          "\n" +
		                          "enter command: " +
		                          "\n";
		//@formatter:on
		
		var verified_output = new StringBuilder();
		
		String         line;
		BufferedReader reader;
		
		Process process = Runtime.getRuntime()
		                         .exec( "java -jar build/libs/riso-1.0.jar" );
		
		OutputStream stdin  = process.getOutputStream();
		InputStream  stderr = process.getErrorStream();
		InputStream  stdout = process.getInputStream();
		
		// write into stdin
		line = "C 1 1" + "\n";
		stdin.write( line.getBytes() );
		stdin.flush();
		stdin.close();
		
		// read from stdout
		reader = new BufferedReader( new InputStreamReader( stdout ) );
		while( ( line = reader.readLine() ) != null )
			{
			verified_output.append( line )
			               .append( '\n' );
			}
		reader.close();
		
		// todo fix Hooks.onOperatorError( ( err , data ) ->
		verified_output.delete(0, verified_output.indexOf("\n") + 1);
		verified_output.delete(0, verified_output.indexOf("\n") + 1);
		
		assertThat( verified_output.toString() , is( verified_ethalon ) );
		
		}
		
	}
