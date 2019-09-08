package life.expert.riso.domain.model.value;



import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import life.expert.riso.domain.model.Canvas;
import life.expert.riso.domain.model.Figure;
import life.expert.riso.domain.model.entity.DefaultCanvas;
import life.expert.value.numeric.PositiveInteger;
import org.junit.Before;
import org.junit.Test;
import reactor.test.StepVerifier;

//@Header@
//--------------------------------------------------------------------------------
//
//                          riso  life.expert.riso.domain.model.value
//                           wilmer 2019/07/24
//
//--------------------------------------------------------------------------------

/**
 * The type Fill test.
 */
public class FillTest
	{
	
	private Fill fill;
	
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
		var rect = Rectangle.monoOf( PositivePoint.of( 2 , 2 ) , PositivePoint.of( 9 , 9 ) , 'x' , ( PositivePoint a , PositivePoint b , Character c ) -> Line.monoOf( a , b , c )
		                                                                                                                                                      .cast( Figure.class )).cast( Figure.class );
		
		canvas = DefaultCanvas.monoOf( PositiveInteger.of( 10 ) , PositiveInteger.of( 10 ) )
		                      .flatMap( c -> c.draw( rect ) )
		                      .block();
		
		fill = Fill.monoOf( PositivePoint.of( 3 , 3 ) , 'o' )
		           .block();
			
		}
	
	/**
	 * Mono of.
	 */
	@Test
	public void monoOf()
		{
		StepVerifier.create( Fill.monoOf( PositivePoint.of( 10 , 10 ) , 'd' ) )
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
		var c = canvas.draw( fill )
		              .flatMap( x -> canvas.makeScreen() );
		
		StepVerifier.create( c )
		            .expectNext( "------------\n" + "|          |\n" + "| xxxxxxxx |\n" + "| xoooooox |\n" + "| xoooooox |\n" + "| xoooooox |\n" + "| xoooooox |\n" + "| xoooooox |\n" + "| xoooooox |\n" + "| xxxxxxxx |\n" + "|          |\n" + "------------\n" )
		            .expectComplete()
		            .verify();
			
		}
	
	/**
	 * Gets x.
	 */
	@Test
	public void getX()
		{
		assertThat( fill.getX() , is( 3 ) );
		}
	
	/**
	 * Gets y.
	 */
	@Test
	public void getY()
		{
		assertThat( fill.getY() , is( 3 ) );
		}
	
	/**
	 * Gets character.
	 */
	@Test
	public void getCharacter()
		{
		assertThat( fill.getCharacter() , is( 'o' ) );
		}
		
	}