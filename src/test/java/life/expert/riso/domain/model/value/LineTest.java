package life.expert.riso.domain.model.value;



import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import io.vavr.Tuple;
import io.vavr.control.Try;
import life.expert.riso.domain.model.entity.DefaultCanvas;
import life.expert.value.numeric.PositiveInteger;
import org.junit.Before;
import org.junit.Test;
import reactor.test.StepVerifier;

import static life.expert.common.async.LogUtils.*;        //logAtInfo
import static life.expert.common.function.CheckedUtils.*;// .map(consumerToBoolean)
//import static reactor.function.TupleUtils.*; // tuple->R INTO func->R
import static life.expert.common.function.TupleUtils.*; //vavr's tuple->R INTO func->R

import static io.vavr.API.*;                              //switch
//import static io.vavr.Predicates.*;                       //switch - case

import static java.util.function.Predicate.*;           //isEqual streamAPI

//@Header@
//--------------------------------------------------------------------------------
//
//                          riso  life.expert.riso.domain.model.value
//                           wilmer 2019/07/25
//
//--------------------------------------------------------------------------------

/**
 * The type Line test.
 */
public class LineTest
	{
	
	private Line line;
	
	private DefaultCanvas canvas;
	
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
		canvas = DefaultCanvas.monoOf( PositiveInteger.of( 10 ) , PositiveInteger.of( 10 ) )
		                      .block();
		line = Line.monoOf( PositivePoint.of( 1 , 1 ) , PositivePoint.of( 1 , 4 ) , 'x' )
		           .block();
			
		}
	
	/**
	 * Mono of.
	 */
	@Test
	public void monoOf()
		{
		StepVerifier.create( Line.monoOf( PositivePoint.of( 1 , 1 ) , PositivePoint.of( 1 , 4 ) , 'x' ) )
		            .expectNextCount( 1 )
		            .expectComplete()
		            .verify();
		}
	
	/**
	 * Draw.
	 */
	@Test
	public void draw()
		{
		var c = canvas.draw( line )
		              .flatMap( v -> canvas.makeScreen() );
		
		StepVerifier.create( c )
		            .expectNext( "------------\n" + "|x         |\n" + "|x         |\n" + "|x         |\n" + "|x         |\n" + "|          |\n" + "|          |\n" + "|          |\n" + "|          |\n" + "|          |\n" + "|          |\n" + "------------\n" )
		            .expectComplete()
		            .verify();
		}
	
	/**
	 * Gets x 0.
	 */
	@Test
	public void getX0()
		{
		assertThat( line.getX0() , is( 1 ) );
		}
	
	/**
	 * Gets y 0.
	 */
	@Test
	public void getY0()
		{
		assertThat( line.getX0() , is( 1 ) );
		}
	
	/**
	 * Gets x 1.
	 */
	@Test
	public void getX1()
		{
		assertThat( line.getX1() , is( 1 ) );
		}
	
	/**
	 * Gets y 1.
	 */
	@Test
	public void getY1()
		{
		assertThat( line.getY1() , is( 4 ) );
		}
	
	/**
	 * Gets character.
	 */
	@Test
	public void getCharacter()
		{
		assertThat( line.getCharacter() , is( 'x' ) );
		}
	
	public static String fakeString( int x0 ,
	                                 int y0 ,
	                                 int x1 ,
	                                 int y1 ,
	                                 String character )
		{
		return "fakeString " + x0 + y0 + x1 + y1 + character;
		}
	
	@Test
	@SuppressWarnings( "unchecked" )
	public void tryOf()
		{
		int    x0        = 2;
		int    y0        = 3;
		int    x1        = 5;
		int    y1        = 6;
		String character = null;
		
		var in = Tuple.of( x0 , y0 , x1 , y1 , character );
		
		//@formatter:off
		Try<String> r= Match(in ).of(
		                   Case( $(not(predicate(  ( x_0 , y_0 , x_1 , y_1, ch ) -> x_0 >= 1 && y_0 >= 1 && x_1 >= 1 && y_1 >= 1 ) )) ,
		                         illegalArgumentFailure( "bad args" )) ,
		                   Case( $() ,
		                          t-> Success(function( LineTest::fakeString ).apply(t)) ) ,
		                   Case( $() ,
		                            Success("other result"))
		                            );
		//@formatter:on
		
		//log( "RESULT:: " + r );
		
		}
		
	}