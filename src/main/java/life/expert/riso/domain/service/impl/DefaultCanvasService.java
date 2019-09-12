package life.expert.riso.domain.service.impl;
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

import life.expert.riso.domain.model.DrawingFactory;
import life.expert.riso.domain.model.Canvas;

import life.expert.riso.domain.model.Figure;
import life.expert.riso.domain.repository.CanvasRepository;
import life.expert.riso.domain.service.CanvasDataTransferObject;
import life.expert.riso.domain.service.CanvasService;
import life.expert.riso.domain.service.FillDataTransferObject;
import life.expert.riso.domain.service.LineDataTransferObject;
import life.expert.riso.domain.service.RectangleDataTransferObject;
import life.expert.riso.domain.service.ResultDataTransferObject;
import lombok.AccessLevel;
import lombok.Getter;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;

//import static life.expert.common.base.Preconditions.*;  //checkCollection

import java.util.function.*;                            //producer supplier

//import static  reactor.function.TupleUtils.*; //reactor's tuple->R INTO func->R

//import static io.vavr.API.*;                           //conflicts with my reactive For-comprehension

import static io.vavr.API.Case;
import static io.vavr.Predicates.*;                       //switch - case
//import static java.util.function.Predicate.*;           //isEqual streamAPI

import static io.vavr.API.CheckedFunction;//checked functions
import static io.vavr.API.unchecked;    //checked->unchecked
import static io.vavr.API.Tuple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

//import static life.expert.common.base.Preconditions.*;  //checkCollection

//import static  reactor.function.TupleUtils.*; //reactor's tuple->R INTO func->R

import static life.expert.common.reactivestreams.Preconditions.*; //reactive check

//import static io.vavr.API.*;                           //conflicts with my reactive For-comprehension

import static io.vavr.API.$;                            // pattern matching
import static io.vavr.API.Match;
//import static java.util.function.Predicate.*;           //isEqual streamAPI

import static io.vavr.API.Function;     //lambda->Function3

import life.expert.value.string.*;
import life.expert.value.numeric.*;

import static life.expert.common.async.LogUtils.*;        //logAtInfo
import static life.expert.common.function.NullableUtils.*;//.map(nullableFunction)
import static life.expert.common.function.CheckedUtils.*;// .map(consumerToBoolean)
import static life.expert.common.reactivestreams.Preconditions.*; //reactive check
import static life.expert.common.reactivestreams.Patterns.*;    //reactive helper functions
import static life.expert.common.base.Objects.*;          //deepCopyOfObject
import static life.expert.common.reactivestreams.ForComprehension.*; //reactive for-comprehension

/**
 * <pre> * The type Canvas service.
 *
 * Preconditions: none
 * Postconditions: none
 * Side effects: none
 * Tread safety: Not thread-safe
 *
 * </pre>
 */
@Service
@Getter
@Slf4j
public class DefaultCanvasService
	implements CanvasService
	{
	private final CanvasRepository canvasRepository;
	
	private final DrawingFactory drawingFactory;
	
	@Getter( AccessLevel.NONE ) Function<Mono<ResultDataTransferObject>,Publisher<ResultDataTransferObject>> LOWLEVEL_EXCEPTION_WRAPPER = s -> s.onErrorMap( not( err -> err.getClass()
	                                                                                                                                                                        .equals( IllegalArgumentException.class ) ) , err -> new RuntimeException( "We are sorry, an unexpected error has occurred" , err ) );
	
	/**
	 * Instantiates a new Canvas service.
	 *
	 * @param canvasRepository
	 * 	the canvas repository
	 */
	@Autowired
	public DefaultCanvasService( @Lazy CanvasRepository canvasRepository ,
	                             @Lazy DrawingFactory drawingFactory )
		{
		this.canvasRepository = canvasRepository;
		this.drawingFactory = drawingFactory;
		}
	
	@Override
	//@Transactional
	public Mono<ResultDataTransferObject> createCanvas( @NonNull CanvasDataTransferObject canvas )
		{
		var c = drawingFactory.newMonoOfCanvas( canvas.getWidth() , canvas.getHeight() );
		
		return getCanvasRepository().saveAll( c )
		                            .next()
		                            .flatMap( Canvas::makeScreen )
		                            .zipWith( c )
		                            .map( t -> new ResultDataTransferObject( t.getT2()
		                                                                      .getId() , t.getT1() ) )
		                            .transform( LOWLEVEL_EXCEPTION_WRAPPER );
		}
	
	@Override
	//@Transactional
	public Mono<ResultDataTransferObject> newLine( @NonNull LineDataTransferObject line )
		{
		
		final String canvasId = line.getCanvasId();
		
		if( canvasId != null && canvasId.isBlank() )
			return illegalStateMonoError( "canvas id must not be empty" + canvasId );
		
		var l = drawingFactory.newLineBuilder()
		                      .startPoint( line.getX0() , line.getY0() )
		                      .endPoint( line.getX1() , line.getY1() )
		                      .filler( line.getCharacter() )
		                      .buildMono();
		
		//System.out.println( "DefaultCanvasService newLine " + canvasId );
		
		//		var a = getCanvasRepository().findById( canvasId )
		//		                             .subscribe( printConsumer() );
		
		return getCanvasRepository().findById( canvasId )
		                            .flatMap( c -> c.draw( l ) )
		                            .flatMap( c -> getCanvasRepository().save( c ) )
		                            .flatMap( Canvas::makeScreen )
		                            .map( s -> new ResultDataTransferObject( canvasId , s ) )
		                            .transform( LOWLEVEL_EXCEPTION_WRAPPER );
		}
	
	@Override
	//@Transactional
	public Mono<ResultDataTransferObject> newRectangle( @NonNull RectangleDataTransferObject rectangle )
		{
		final String canvasId = rectangle.getCanvasId();
		
		if( canvasId != null && canvasId.isBlank() )
			return illegalStateMonoError( "canvas id must not be empty" );
		
		var r = drawingFactory.newRectangleBuilder()
		                      .startPoint( rectangle.getX0() , rectangle.getY0() )
		                      .endPoint( rectangle.getX1() , rectangle.getY1() )
		                      .filler( rectangle.getCharacter() )
		                      .buildMono();
		
		return getCanvasRepository().findById( rectangle.getCanvasId() )
		                            .flatMap( c -> c.draw( r ) )
		                            .flatMap( c -> getCanvasRepository().save( c ) )
		                            .flatMap( Canvas::makeScreen )
		                            .map( s -> new ResultDataTransferObject( canvasId , s ) )
		                            .transform( LOWLEVEL_EXCEPTION_WRAPPER );
		}
	
	@Override
	//@Transactional
	public Mono<ResultDataTransferObject> newFill( @NonNull FillDataTransferObject fill )
		{
		final String canvasId = fill.getCanvasId();
		
		if( canvasId != null && canvasId.isBlank() )
			return illegalStateMonoError( "canvas id must not be empty" );
		
		var f = drawingFactory.newFillBuilder()
		                      .point( fill.getX() , fill.getY() )
		                      .filler( fill.getCharacter() )
		                      .buildMono();
		
		return getCanvasRepository().findById( fill.getCanvasId() )
		                            .flatMap( c -> c.draw( f ) )
		                            .flatMap( c -> getCanvasRepository().save( c ) )
		                            .flatMap( Canvas::makeScreen )
		                            .map( s -> new ResultDataTransferObject( canvasId , s ) )
		                            .transform( LOWLEVEL_EXCEPTION_WRAPPER );
		}
		
	}
