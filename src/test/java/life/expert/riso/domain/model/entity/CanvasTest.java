package life.expert.riso.domain.model.entity;



import com.google.common.base.Strings;
import life.expert.riso.domain.model.Canvas;
import life.expert.riso.domain.model.factory.DefaultDrawingFactory;
import life.expert.riso.domain.model.value.Rectangle;
import org.junit.Before;
import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.time.Duration;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

//import static  reactor.function.TupleUtils.*; //reactor's tuple->R INTO func->R

//import static java.util.function.Predicate.*;           //isEqual streamAPI

//@Header@
//--------------------------------------------------------------------------------
//
//                          riso  life.expert.riso.domain.model.entity
//                           wilmer 2019/07/24
//
//--------------------------------------------------------------------------------

/**
 * The type Canvas test.
 */
public class CanvasTest
	{
	
	private Canvas canvas;
	
	/**
	 * Sets up.
	 *
	 * @throws Exception
	 * 	the exception
	 */
	@Before
	public void setUp()
	throws Exception
		{
		StepVerifier.setDefaultTimeout( Duration.ofSeconds( 3 ) );
		
		canvas = DefaultCanvas.builder()
		                      .size( 3 , 3 )
		                      .build();
			
		}
	
	public static Flux<Integer> intRange( final int start ,
	                                      final int end )
		{
		boolean reverse = start > end;
		return Flux.generate( () -> start , ( i , f ) ->
		{
		f.next( i );
		if( i == end )
			{
			f.complete();
			}
		return reverse ? i - 1 : i + 1;
		} );
		}
	
	@Test
	public void initScreenTest()
		{
		var s = new StringBuilder( Strings.repeat( " " , 80 ) );
		System.out.println( "1CanvasTest initScreenTest " + s );
		s.replace( 1 , 1 , "z" );
		System.out.println( "2CanvasTest initScreenTest " + s );
		}
	
	/**
	 * Gets x max test.
	 */
	@Test
	public void getXMaxTest()
		{
		//		var f=intRange( 0 , 8 ).flatMap( i -> intRange( 10 , 18 ).map( j -> Tuples.of( i , j ) ) );
		//		f.subscribe( logAtInfoConsumer("NEXT") , logAtErrorConsumer("ERROR") , logAtInfoRunnable("COMPLETE") );
		
		assertThat( canvas.getXMax() , is( 3 ) );
		}
	
	/**
	 * Gets y max test.
	 */
	@Test
	public void getYMaxTest()
		{
		assertThat( canvas.getYMax() , is( 3 ) );
		}
	
	/**
	 * Mono of test.
	 */
	@Test
	public void monoOfTest()
		{
		var c = DefaultCanvas.builder()
		                     .size( 3 , 3 )
		                     .buildMono();
		
		StepVerifier.create( c )
		            .expectNextCount( 1 )
		            .expectComplete()
		            .verify();
		}
	
	/**
	 * Make screen test.
	 */
	@Test
	public void makeScreenTest()
		{
		var screen = canvas.makeScreen();
		
		StepVerifier.create( screen )
		            .expectNext( "-----\n" + "|   |\n" + "|   |\n" + "|   |\n" + "-----\n" )
		            .expectComplete()
		            .verify();
		
		//assertThat( screen , is(Mono.just( "-----\n" + "|   |\n" + "|   |\n" + "|   |\n" + "-----\n") ) );
		
		}
	
	/**
	 * Draw figure test.
	 */
	@Test
	public void drawFigureTest()
		{
		
		var rect = Rectangle.builder( new DefaultDrawingFactory() )
		                    .startPoint( 1 , 1 )
		                    .endPoint( 2 , 2 )
		                    .filler( 'x' )
		                    .build();
		
		StepVerifier.create( canvas.draw( rect ) )
		            .expectNextCount( 1 )
		            .expectComplete()
		            .verify();
			
		}
	
	/**
	 * Draw mono test.
	 */
	@Test
	public void drawMonoTest()
		{
		var r = Rectangle.builder( new DefaultDrawingFactory() )
		                 .startPoint( 1 , 1 )
		                 .endPoint( 2 , 2 )
		                 .filler( 'x' )
		                 .buildMono();
		
		var p = canvas.draw( r );//.log( "VO draw" , Level.FINE , SignalType.ON_NEXT );
		//p.subscribe( logAtInfoConsumer( "NEXT" ) , logAtErrorConsumer( "ERROR" ) , logAtInfoRunnable( "COMPLETE" ) );
		
		StepVerifier.create( p )
		            .expectNextCount( 1 )
		            .expectComplete()
		            .verify();
		//
		}
	
	/**
	 * Put get pixel test.
	 */
	@Test
	public void putGetPixelTest()
		{
		canvas.putPixel( 1 , 1 , 'z' );
		
		assertThat( canvas.getPixel( 1 , 1 )
		                  .get() , is( 'z' ) );
		}
		
	}