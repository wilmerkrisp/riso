package life.expert.riso.domain.model.builder;

//---------------------------------------------
//      ___       __        _______   ______
//     /   \     |  |      /  _____| /  __  \
//    /  ^  \    |  |     |  |  __  |  |  |  |
//   /  /_\  \   |  |     |  | |_ | |  |  |  |
//  /  _____  \  |  `----.|  |__| | |  `--'  |
// /__/     \__\ |_______| \______|  \______/
//
//               wilmer 2019/09/11
//---------------------------------------------

import io.vavr.control.Try;
import life.expert.riso.common.PositivePoint;
import life.expert.riso.domain.model.Figure;
import reactor.core.publisher.Mono;

//import static life.expert.common.base.Preconditions.*;  //checkCollection
//import static  reactor.function.TupleUtils.*; //reactor's tuple->R INTO func->R
//import static io.vavr.API.*;                           //conflicts with my reactive For-comprehension
//import static java.util.function.Predicate.*;           //isEqual streamAPI

//import java.util.List;                                  //usual list
//import io.vavr.collection.List;                         //immutable List
//import com.google.common.collect.*;                     //ImmutableList

/**
 * for using with abstract factory
 */
public interface LineBuilder
	{
	
	public LineBuilder startPoint( final int x0 ,
	                               final int y0 );
	
	public LineBuilder startPoint( PositivePoint startPoint );
	
	public LineBuilder startPointX( int x0 );
	
	public LineBuilder startPointY( int y0 );
	
	public LineBuilder endPoint( final int x1 ,
	                             final int y1 );
	
	public LineBuilder endPoint( PositivePoint endPoint );
	
	public LineBuilder endPointX( int x1 );
	
	public LineBuilder endPointY( int y1 );
	
	public LineBuilder filler( char character );
	
	public Mono<Figure> buildMono();
	
	public Try<Figure> buildTry();
	
	public Figure build();
	}
