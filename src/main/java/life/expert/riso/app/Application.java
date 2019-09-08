package life.expert.riso.app;



import life.expert.riso.common.MergeableSimpleR2dbcRepository;
import lombok.extern.slf4j.Slf4j;
import org.jline.terminal.Terminal;
import org.jline.utils.AttributedString;
import org.jline.utils.AttributedStyle;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.shell.jline.PromptProvider;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.util.StringUtils;
import reactor.core.publisher.Hooks;

//@Header@
//--------------------------------------------------------------------------------
//
//                          graph  life.expert.riso.base
//                           wilmer 2019/01/23
//
//--------------------------------------------------------------------------------

/**
 * <pre>
 * The Drawing interactive console Application.
 *
 * Preconditions: JRE 12
 * Postconditions: none
 * Side effects: console/file input output
 * Tread safety: Not thread-safe
 * </pre>
 */
@Slf4j
@SpringBootApplication
@ComponentScan( { "life.expert.riso.app" ,
                  "life.expert.riso.domain" } )
//@EnableTransactionManagement
@EnableR2dbcRepositories( basePackages = "life.expert.riso.domain.repository", repositoryBaseClass = MergeableSimpleR2dbcRepository.class )
public class Application
	{
	
	/**
	 * Main method using Spring main cycle
	 *
	 * @param args
	 * 	the args
	 */
	public static void main( final String... args )
		{
		// todo https://github.com/jline/jline3/issues/263
		System.setProperty( "org.jline.terminal.dumb" , "true" );
		
		// Spring Reactor global error handler
		Hooks.onOperatorError( ( err , data ) ->
		                       {
		                       String s = ( data == null ) ? "No additional data" : "Additional data" + data.toString();
		                       logger_.error( s , err );
		                       return err;
		                       } );
		
		// for overriding standart quit command
		String[] disabledCommands = { "--spring.shell.command.quit.enabled=false" };
		var      fullArgs         = StringUtils.concatenateStringArrays( args , disabledCommands );
		
		SpringApplication.run( Application.class , fullArgs );
		
		
		
		
		}
	
	/**
	 * Bean to customize the prompt string
	 * <p>
	 * After each command invocation, the shell waits for new input from the user, displaying a prompt
	 * in yellow.
	 *
	 * @return the prompt provider
	 */
	@Bean
	public PromptProvider customPromptProvider()
		{
		return () -> new AttributedString( "enter command: " , AttributedStyle.DEFAULT.foreground( AttributedStyle.YELLOW ) );
		}
	
//	@Bean
//	public OutputHelper shellHelper( @Lazy Terminal terminal )
//		{
//		return new ShellOutputHelper( terminal );
//		}
	
	//	/**
	//	 * Output to the console will be done asynchronously through the methods of this Bean
	//	 *
	//	 * @param terminal
	//	 * 	the shell terminal
	//	 *
	//	 * @return the shell helper
	//	 */
	//	@Bean
	//	public OutputHelper shellHelper( @Lazy Terminal terminal )
	//		{
	//		return new ShellOutputHelper( terminal );
	//		}
	//
	//	/**
	//	 * Bean to store the current user session
	//	 *
	//	 * @return the application state
	//	 */
	//	@Bean
	//	public ApplicationState applicationState()
	//		{
	//		return new ApplicationState();
	//		}
	//
	//	/**
	//	 * Bean get repository for storing/getting canvas objects
	//	 *
	//	 * @return the canvas repository
	//	 */
	//	@Bean
	//	public CanvasRepository canvasRepository()
	//		{
	//		return new InMemoryCanvasRepository();
	//		}
	
	}
