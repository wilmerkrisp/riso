package life.expert.riso.domain.model;



import static life.expert.common.reactivestreams.Preconditions.checkArgumentAndMap;

import reactor.core.publisher.Mono;

//import static reactor.function.TupleUtils.*; // tuple->R INTO func->R

//import static io.vavr.API.*;                           //conflicts with my reactive For-comprehension

import static io.vavr.API.$;                            // pattern matching
import static io.vavr.API.Case;
import static io.vavr.API.Match;
//import static java.util.function.Predicate.*;           //isEqual streamAPI

import static io.vavr.API.CheckedFunction;//checked functions
import static io.vavr.API.unchecked;    //checked->unchecked
import static io.vavr.API.Function;     //lambda->Function3
import static io.vavr.API.Tuple;

//@Header@
//--------------------------------------------------------------------------------
//
//                          riso  life.expert.riso.domain.model.entity
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
	public Mono<Canvas> draw( Figure figure );
	
	public Mono<Canvas> draw( Mono<Figure> figure );
	
	public Mono<String> makeScreen();
	
	
	
	}
