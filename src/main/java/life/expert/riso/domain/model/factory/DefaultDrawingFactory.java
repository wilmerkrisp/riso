package life.expert.riso.domain.model.factory;
//---------------------------------------------
//      ___       __        _______   ______
//     /   \     |  |      /  _____| /  __  \
//    /  ^  \    |  |     |  |  __  |  |  |  |
//   /  /_\  \   |  |     |  | |_ | |  |  |  |
//  /  _____  \  |  `----.|  |__| | |  `--'  |
// /__/     \__\ |_______| \______|  \______/
//
//               wilmer 2019/08/13
//---------------------------------------------

import life.expert.riso.domain.model.Canvas;
import life.expert.riso.domain.model.DrawingFactory;
import life.expert.riso.domain.model.Figure;
import life.expert.riso.domain.model.builder.LineBuilder;
import life.expert.riso.domain.model.builder.RectangleBuilder;
import life.expert.riso.domain.model.entity.DefaultCanvas;
import life.expert.riso.domain.model.value.Fill;
import life.expert.riso.domain.model.value.Line;

//import static life.expert.common.base.Preconditions.*;  //checkCollection

//import static  reactor.function.TupleUtils.*; //reactor's tuple->R INTO func->R

//import static io.vavr.API.*;                           //conflicts with my reactive For-comprehension

import static io.vavr.API.$;                            // pattern matching
import static io.vavr.API.Case;
import static io.vavr.API.Match;
//import static java.util.function.Predicate.*;           //isEqual streamAPI

import static io.vavr.API.CheckedFunction;//checked functions
import static io.vavr.API.unchecked;    //checked->unchecked
import static io.vavr.API.Function;     //lambda->Function3
import static io.vavr.API.Tuple;

import life.expert.riso.domain.model.value.Rectangle;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

//import java.util.List;                                  //usual list
//import io.vavr.collection.List;                         //immutable List
//import com.google.common.collect.*;                     //ImmutableList

//@Header@
//--------------------------------------------------------------------------------
//
//                          riso  life.expert.riso.domain.model
//                           wilmer 2019/08/13
//
//--------------------------------------------------------------------------------

@Component
public class DefaultDrawingFactory
	implements DrawingFactory
	{
	@Override
	public Mono<Canvas> newMonoOfCanvas( int width ,
	                                     int height )
		{
		return DefaultCanvas.monoOf( width , height )
		                    .cast( Canvas.class );
		}
	
	@Override
	public Mono<Figure> newMonoOfFill( int fillFromStartPointX ,
	                                   int fillFromStartPointY ,
	                                   char character )
		{
		return Fill.monoOf( fillFromStartPointX , fillFromStartPointY , character )
		           .cast( Figure.class );
			
		}
	
	@Override
	public LineBuilder newLineBuilder()
		{
		return Line.builder();
		}
	
	@Override
	public RectangleBuilder newRectangleBuilder()
		{
		return Rectangle.builder( this );
		}
	
	 
	
	
	
	}
