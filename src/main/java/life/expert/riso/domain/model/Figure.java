package life.expert.riso.domain.model;
//@Header@
//--------------------------------------------------------------------------------
//
//                          riso  life.expert.riso.domain.model.value
//                           wilmer 2019/07/23
//
//--------------------------------------------------------------------------------

import reactor.core.publisher.Mono;

/**
 * Basic interface for different shapes (lines, triangles, etc.) that can draw themselves on canvas
 */
public interface Figure
	{
	
	/**
	 * Draw figure on canvas.
	 *
	 * @param canvas
	 * 	the canvas
	 *
	 * @return the mono with Figure
	 */
	Mono<Figure> draw( Drawing canvas );
	
	}
