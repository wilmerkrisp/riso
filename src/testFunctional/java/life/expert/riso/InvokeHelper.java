package life.expert.riso;
//@Header@
//--------------------------------------------------------------------------------
//
//                          riso  life.expert.riso
//                           wilmer 2019/07/25
//
//--------------------------------------------------------------------------------

import static org.springframework.util.ReflectionUtils.invokeMethod;

import javax.validation.constraints.NotNull;

import org.springframework.shell.CommandRegistry;
import org.springframework.shell.MethodTarget;

/**
 * The type Invoke helper.
 */
public abstract class InvokeHelper
	{
	
	/**
	 * Invoke t.
	 *
	 * @param <T>
	 * 	the type parameter
	 * @param methodTarget
	 * 	the method target
	 * @param args
	 * 	the args
	 *
	 * @return the t
	 */
	@SuppressWarnings( "unchecked" )
	protected <T> T invoke( final MethodTarget methodTarget ,
	                        final Object... args )
		{
		return (T) invokeMethod( methodTarget.getMethod() , methodTarget.getBean() , args );
		}
	
	/**
	 * Lookup command method target.
	 *
	 * @param registry
	 * 	the registry
	 * @param command
	 * 	the command
	 *
	 * @return the method target
	 */
	protected MethodTarget lookupCommand( @NotNull final CommandRegistry registry ,
	                                      @NotNull final String command )
		{
		return registry.listCommands()
		               .get( command );
		}
	}
