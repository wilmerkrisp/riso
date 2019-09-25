package life.expert.riso.domain.model.factory;
//---------------------------------------------
//      ___       __        _______   ______
//     /   \     |  |      /  _____| /  __  \
//    /  ^  \    |  |     |  |  __  |  |  |  |
//   /  /_\  \   |  |     |  | |_ | |  |  |  |
//  /  _____  \  |  `----.|  |__| | |  `--'  |
// /__/     \__\ |_______| \______|  \______/
//
//               wilmer 2019/09/12
//---------------------------------------------

import io.vavr.control.Try;
import life.expert.riso.common.PositiveSize;
import life.expert.riso.domain.model.Canvas;
import reactor.core.publisher.Mono;

//import static life.expert.common.base.Preconditions.*;  //checkCollection
//import static  reactor.function.TupleUtils.*; //reactor's tuple->R INTO func->R
//import static io.vavr.API.*;                           //conflicts with my reactive For-comprehension
//import static java.util.function.Predicate.*;           //isEqual streamAPI

//import java.util.List;                                  //usual list
//import io.vavr.collection.List;                         //immutable List
//import com.google.common.collect.*;                     //ImmutableList

public interface CanvasBuilder
	{
	
	public CanvasBuilder size( final int width ,
	                           final int height );
	
	public CanvasBuilder size( PositiveSize positiveSize );
	
	public CanvasBuilder width( int width );
	
	public CanvasBuilder height( int height );
	
	public Mono<Canvas> buildMono();
	
	public Try<Canvas> buildTry();
	
	public Canvas build();
	
	}
