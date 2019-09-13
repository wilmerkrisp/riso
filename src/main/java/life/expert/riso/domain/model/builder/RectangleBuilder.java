package life.expert.riso.domain.model.builder;
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

public interface RectangleBuilder
	{
	public RectangleBuilder startPoint( final int x0 ,
	                                    final int y0 );
	
	public RectangleBuilder startPoint( PositivePoint startPoint );
	
	public RectangleBuilder startPointX( int x0 );
	
	public RectangleBuilder startPointY( int y0 );
	
	public RectangleBuilder endPoint( final int x1 ,
	                                  final int y1 );
	
	public RectangleBuilder endPoint( PositivePoint endPoint );
	
	public RectangleBuilder endPointX( int x1 );
	
	public RectangleBuilder endPointY( int y1 );
	
	public RectangleBuilder filler( char character );
	
	public Mono<Figure> buildMono();
	
	public Try<Figure> buildTry();
	
	public Figure build();
	}
