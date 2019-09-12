package life.expert.riso.domain.model.value;



import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import life.expert.riso.common.PositivePoint;
import life.expert.riso.domain.model.Canvas;
import life.expert.riso.domain.model.Figure;
import life.expert.riso.domain.model.entity.DefaultCanvas;
import life.expert.riso.domain.model.factory.DefaultDrawingFactory;
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
 * The type Rectangle test.
 */
public class RectangleTest
	{
	
	private Rectangle rectangle;
	
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
		canvas = (DefaultCanvas)DefaultCanvas.builder()
		                      .size( 10 , 10 )
		                      .build();
		
		rectangle= (Rectangle) Rectangle.builder( new DefaultDrawingFactory() )
		                    .startPoint( 1 , 1 )
		                    .endPoint( 4 , 4 )
		                    .filler( 'x' )
		                    .build();
		}
	
	/**
	 * Mono of.
	 */
	@Test
	public void monoOf()
		{
		var r=  Rectangle.builder( new DefaultDrawingFactory() )
		                    .startPoint( 1 , 1 )
		                    .endPoint( 4 , 4 )
		                    .filler( 'x' )
		                    .buildMono();
		
		
		StepVerifier.create(r )
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
		var c = canvas.draw( rectangle )
		              .flatMap( x -> canvas.makeScreen() );
//		c.log()
//		 .subscribe();
		StepVerifier.create( c )
		            .expectNext( "------------\n" + "|xxxx      |\n" + "|x  x      |\n" + "|x  x      |\n" + "|xxxx      |\n" + "|          |\n" + "|          |\n" + "|          |\n" + "|          |\n" + "|          |\n" + "|          |\n" + "------------\n" )
		            .expectComplete()
		            .verify();
		}
	
	/**
	 * Gets x 0.
	 */
	@Test
	public void getX0()
		{
		assertThat( rectangle.getX0() , is( 1 ) );
		}
	
	/**
	 * Gets y 0.
	 */
	@Test
	public void getY0()
		{
		assertThat( rectangle.getX0() , is( 1 ) );
		}
	
	/**
	 * Gets x 1.
	 */
	@Test
	public void getX1()
		{
		assertThat( rectangle.getX1() , is( 4 ) );
		}
	
	/**
	 * Gets y 1.
	 */
	@Test
	public void getY1()
		{
		assertThat( rectangle.getY1() , is( 4 ) );
		}
	
	/**
	 * Gets character.
	 */
	@Test
	public void getCharacter()
		{
		assertThat( rectangle.getCharacter() , is( 'x' ) );
		}
		
	}