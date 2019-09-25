package life.expert.riso.domain.model.impl.value;



import life.expert.riso.domain.model.impl.entity.DefaultCanvas;
import org.junit.Before;
import org.junit.Test;
import reactor.test.StepVerifier;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

//import static reactor.function.TupleUtils.*; // tuple->R INTO func->R
//import static io.vavr.Predicates.*;                       //switch - case

//@Header@
//--------------------------------------------------------------------------------
//
//                          riso  life.expert.riso.domain.model.impl.value
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
		
		canvas = (DefaultCanvas) DefaultCanvas.builder()
		                                      .size( 10 , 10 )
		                                      .build();
		
		line = (Line) Line.builder()
		                  .startPoint( 1 , 1 )
		                  .endPoint( 1 , 4 )
		                  .filler( 'x' )
		                  .build();
		}
	
	/**
	 * Mono of.
	 */
	@Test
	public void monoOf()
		{
		var l = Line.builder()
		            .startPoint( 1 , 1 )
		            .endPoint( 1 , 4 )
		            .filler( 'x' )
		            .buildMono();
		
		StepVerifier.create( l )
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
		
	}