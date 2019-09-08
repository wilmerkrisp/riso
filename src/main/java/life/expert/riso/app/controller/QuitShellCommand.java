package life.expert.riso.app.controller;
//@Header@
//--------------------------------------------------------------------------------
//
//                          riso  life.expert.riso.app
//                           wilmer 2019/07/21
//
//--------------------------------------------------------------------------------

import lombok.extern.slf4j.Slf4j;
import org.springframework.shell.ExitRequest;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.commands.Quit;

/**
 * The type Quit shell command.
 */
@ShellComponent
@Slf4j
public class QuitShellCommand
	implements Quit.Command
	{
	
	/**
	 * Quit.
	 */
	@ShellMethod( value = "Exit the shell.", key = { "quit" ,
	                                                 "exit" ,
	                                                 "Q" } )
	public void quit()
		{
		throw new ExitRequest( 0 );
		}
	}