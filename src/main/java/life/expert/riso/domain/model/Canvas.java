package life.expert.riso.domain.model;



import reactor.core.publisher.Mono;



//@Header@
//--------------------------------------------------------------------------------
//
//                          riso  life.expert.riso.domain.model.impl.entity
//                           wilmer 2019/07/23
//
//--------------------------------------------------------------------------------

/**
 * <pre>
 * It is a canvas for drawing where you can add shapes. Stores drawn shapes as raster graphics in an
 * array. To do: alter  to the DDD event sourcing pattern with storing only the latest snapshot
 *
 * Preconditions: none
 * Postconditions: none
 * Side effects: none
 * Tread safety:  Immutable
 * </pre>
 */
public interface Canvas
	extends Drawing,
	        Entity<String>
	{
	/**
	 * Draw mono.
	 *
	 * @param figure
	 * 	the figure
	 *
	 * @return the mono
	 */
	public Mono<Canvas> draw( Figure figure );
	
	/**
	 * Draw mono.
	 *
	 * @param figure
	 * 	the figure
	 *
	 * @return the mono
	 */
	public Mono<Canvas> draw( Mono<Figure> figure );
	
	/**
	 * Make screen mono.
	 *
	 * @return the mono
	 */
	public Mono<String> makeScreen();
	
	}
