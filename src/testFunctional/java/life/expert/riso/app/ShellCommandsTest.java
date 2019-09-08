package life.expert.riso.app;
//@Header@
//--------------------------------------------------------------------------------
//
//                          riso  life.expert.riso.app
//                           wilmer 2019/07/25
//
//--------------------------------------------------------------------------------

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;
import static org.springframework.util.ReflectionUtils.findMethod;

import life.expert.riso.InvokeHelper;
import life.expert.riso.TestApplicationConfiguration;
import life.expert.riso.app.controller.ShellCommands;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.shell.ConfigurableCommandRegistry;
import org.springframework.shell.MethodTarget;
import org.springframework.shell.standard.StandardMethodTargetRegistrar;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**<pre>
 * Illustrative functional tests for the Drawing application. These functional tests use Spring
 * Shell commands auto-wired by the Spring Test Runner outside of the shell, to test functionality
 * of the commands.
 *
 * Preconditions: auto-wired beans: OutputHelper, DrawingFactory, CanvasRepository
 * Postconditions: passed or failed tests
 * Side effects: io
 * Tread safety: Not thread-safe
 </pre>*/
@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration( classes = { TestApplicationConfiguration.class ,
                                   ShellCommands.class } )
public class ShellCommandsTest
	extends InvokeHelper
	{
	
	private static final Class<ShellCommands> COMMAND_CLASS_UNDER_TEST = ShellCommands.class;
	
	private final ConfigurableCommandRegistry registry = new ConfigurableCommandRegistry();
	
	@Autowired private OutputHelper outputHelper;
	
	@Autowired private ApplicationContext context;
	
	/**
	 * Sets .
	 */
	@Before
	public void setup()
		{
		final StandardMethodTargetRegistrar registrar = new StandardMethodTargetRegistrar();
		registrar.setApplicationContext( context );
		registrar.register( registry );
		outputHelper.clear();
		}
	
	/**
	 * Happy path: Create canvas test.
	 */
	@Test
	public void createCanvasTest()
		{
		final String command       = "C";
		final String commandMethod = "createCanvas";
		
		final MethodTarget commandTarget = lookupCommand( registry , command );
		assertThat( commandTarget , notNullValue() );
		assertThat( commandTarget.getGroup() , is( "Drawing Commands" ) );
		assertThat( commandTarget.getHelp() , is( "Create a new canvas of width w and height h." ) );
		assertThat( commandTarget.getMethod() , is( findMethod( COMMAND_CLASS_UNDER_TEST , commandMethod , int.class , int.class ) ) );
		assertThat( commandTarget.getAvailability()
		                         .isAvailable() , is( true ) );
		invoke( commandTarget , 20 , 4 );
		//@formatter:off
    assertThat(outputHelper.getBuffer(), is(
        "----------------------\n" +
            "|                    |\n" +
            "|                    |\n" +
            "|                    |\n" +
            "|                    |\n" +
            "----------------------\n"));
    //@formatter:on
		}
	
	/**
	 * Happy path: New line test.
	 */
	@Test
	public void newFirstLineTest()
		{
		createCanvasTest();
		outputHelper.clear();
		
		final String command       = "L";
		final String commandMethod = "newLine";
		
		final MethodTarget commandTarget = lookupCommand( registry , command );
		assertThat( commandTarget , notNullValue() );
		assertThat( commandTarget.getGroup() , is( "Drawing Commands" ) );
		assertThat( commandTarget.getHelp() , is( "Create a new line from (x1,y1) to (x2,y2). Currently only\n horizontal or vertical lines are supported. Horizontal and vertical lines\n will be drawn using the 'x' character." ) );
		assertThat( commandTarget.getMethod() , is( findMethod( COMMAND_CLASS_UNDER_TEST , commandMethod , int.class , int.class , int.class , int.class ) ) );
		assertThat( commandTarget.getAvailability()
		                         .isAvailable() , is( true ) );
		invoke( commandTarget , 1 , 2 , 6 , 2 );
		//@formatter:off
    assertThat(outputHelper.getBuffer(), is(
        "----------------------\n" +
            "|                    |\n" +
            "|xxxxxx              |\n" +
            "|                    |\n" +
            "|                    |\n" +
            "----------------------\n"));
    //@formatter:on
			
		}
	
	/**
	 * Happy path: New second line test.
	 */
	@Test
	public void newSecondLineTest()
		{
		newFirstLineTest();
		outputHelper.clear();
		
		final String command       = "L";
		final String commandMethod = "newLine";
		
		final MethodTarget commandTarget = lookupCommand( registry , command );
		assertThat( commandTarget , notNullValue() );
		assertThat( commandTarget.getGroup() , is( "Drawing Commands" ) );
		assertThat( commandTarget.getHelp() , is( "Create a new line from (x1,y1) to (x2,y2). Currently only\n horizontal or vertical lines are supported. Horizontal and vertical lines\n will be drawn using the 'x' character." ) );
		assertThat( commandTarget.getMethod() , is( findMethod( COMMAND_CLASS_UNDER_TEST , commandMethod , int.class , int.class , int.class , int.class ) ) );
		assertThat( commandTarget.getAvailability()
		                         .isAvailable() , is( true ) );
		invoke( commandTarget , 6 , 3 , 6 , 4 );
		
		//@formatter:off
    assertThat(outputHelper.getBuffer(), is(
        "----------------------\n" +
            "|                    |\n" +
            "|xxxxxx              |\n" +
            "|     x              |\n" +
            "|     x              |\n" +
            "----------------------\n"));
    //@formatter:on
			
		}
	
	/**
	 * Happy path: New rectangle test.
	 */
	@Test
	public void newRectangleTest()
		{
		newSecondLineTest();
		outputHelper.clear();
		
		final String command       = "R";
		final String commandMethod = "newRectangle";
		
		final MethodTarget commandTarget = lookupCommand( registry , command );
		assertThat( commandTarget , notNullValue() );
		assertThat( commandTarget.getGroup() , is( "Drawing Commands" ) );
		assertThat( commandTarget.getHelp() , is( "Create a new rectangle, whose upper left corner is (x1,y1) and\n lower right corner is (x2,y2). Horizontal and vertical lines will be drawn\n using the 'x' character." ) );
		assertThat( commandTarget.getMethod() , is( findMethod( COMMAND_CLASS_UNDER_TEST , commandMethod , int.class , int.class , int.class , int.class ) ) );
		assertThat( commandTarget.getAvailability()
		                         .isAvailable() , is( true ) );
		invoke( commandTarget , 14 , 1 , 18 , 3 );
		
		//@formatter:off
    assertThat(outputHelper.getBuffer(), is(
        "----------------------\n" +
            "|             xxxxx  |\n" +
            "|xxxxxx       x   x  |\n" +
            "|     x       xxxxx  |\n" +
            "|     x              |\n" +
            "----------------------\n"));
    //@formatter:on
			
		}
	
	/**
	 * Happy path: Fill area test.
	 */
	@Test
	public void fillAreaTest()
		{
		newRectangleTest();
		outputHelper.clear();
		
		final String command       = "B";
		final String commandMethod = "fillArea";
		
		final MethodTarget commandTarget = lookupCommand( registry , command );
		assertThat( commandTarget , notNullValue() );
		assertThat( commandTarget.getGroup() , is( "Drawing Commands" ) );
		assertThat( commandTarget.getHelp() , is( "Fill the entire area connected to (x,y) with \"colour\" c. The\n behavior of this is the same as that of the \"bucket fill\" tool in paint\n programs." ) );
		assertThat( commandTarget.getMethod() , is( findMethod( COMMAND_CLASS_UNDER_TEST , commandMethod , int.class , int.class , String.class ) ) );
		assertThat( commandTarget.getAvailability()
		                         .isAvailable() , is( true ) );
		invoke( commandTarget , 10 , 3 , "o" );
		
		//@formatter:off
    assertThat(outputHelper.getBuffer(), is(
        "----------------------\n" +
            "|oooooooooooooxxxxxoo|\n" +
            "|xxxxxxooooooox   xoo|\n" +
            "|     xoooooooxxxxxoo|\n" +
            "|     xoooooooooooooo|\n" +
            "----------------------\n"));
    //@formatter:on
		}
		
	}