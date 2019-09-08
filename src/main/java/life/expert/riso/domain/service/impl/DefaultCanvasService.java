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

import life.expert.riso.domain.repository.CanvasRepository;
import life.expert.riso.domain.service.CanvasDataTransferObject;
import life.expert.riso.domain.service.CanvasService;
import life.expert.riso.domain.service.FillDataTransferObject;
import life.expert.riso.domain.service.LineDataTransferObject;
import life.expert.riso.domain.service.RectangleDataTransferObject;
import lombok.AccessLevel;
import lombok.Getter;

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

/**
 * <pre> * The type Canvas service.
 *
 * Preconditions: none
 * Postconditions: none
 * Side effects: none
 * Tread safety: Not thread-safe
 * Immutable
 * Unconditionally thread-safe
 * Conditionally thread-safe
 * Not thread-safe
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
	
	@Getter( AccessLevel.NONE ) Function<Mono<Canvas>,Publisher<Canvas>> LOWLEVEL_EXCEPTION_WRAPPER = s -> s.onErrorMap( not( err -> err.getClass()
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
	public Mono<Canvas> createCanvas( CanvasDataTransferObject canvas )
		{
		var c = drawingFactory.newMonoOfCanvas( canvas.getWidth() , canvas.getHeight() );
		
		return getCanvasRepository().saveAll( c )
		                            .next()
		                            .transform( LOWLEVEL_EXCEPTION_WRAPPER );
		}
	
	@Override
	//@Transactional
	public Mono<Canvas> newLine( LineDataTransferObject line )
		{
		if( line.getCanvasId()
		        .isBlank() )
			return illegalStateMonoError( "canvas id must not be empty" );
		
		var l = drawingFactory.newMonoOfLine( line.getX0() , line.getY0() , line.getX1() , line.getY1() , line.getCharacter() );
		
		return getCanvasRepository().findById( line.getCanvasId() )
		                            .flatMap( c -> c.draw( l ) )
		                            .flatMap( c -> getCanvasRepository().save( c ) )
		                            .transform( LOWLEVEL_EXCEPTION_WRAPPER );
		}
	
	@Override
	//@Transactional
	public Mono<Canvas> newRectangle( RectangleDataTransferObject rectangle )
		{
		if( rectangle.getCanvasId()
		             .isBlank() )
			return illegalStateMonoError( "canvas id must not be empty" );
		
		var r = drawingFactory.newMonoOfRectangle( rectangle.getX0() , rectangle.getY0() , rectangle.getX1() , rectangle.getY1() , rectangle.getCharacter() );
		
		return getCanvasRepository().findById( rectangle.getCanvasId() )
		                            .flatMap( c -> c.draw( r ) )
		                            .flatMap( c -> getCanvasRepository().save( c ) )
		                            .transform( LOWLEVEL_EXCEPTION_WRAPPER );
		}
	
	@Override
	//@Transactional
	public Mono<Canvas> newFill( FillDataTransferObject fill )
		{
		if( fill.getCanvasId()
		        .isBlank() )
			return illegalStateMonoError( "canvas id must not be empty" );
		
		var f = drawingFactory.newMonoOfFill( fill.getX() , fill.getY() , fill.getCharacter() );
		
		return getCanvasRepository().findById( fill.getCanvasId() )
		                            .flatMap( c -> c.draw( f ) )
		                            .flatMap( c -> getCanvasRepository().save( c ) )
		                            .transform( LOWLEVEL_EXCEPTION_WRAPPER );
		}
		
	}
