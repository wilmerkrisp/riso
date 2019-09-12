package life.expert.riso.domain.model.value;

//---------------------------------------------
//      ___       __        _______   ______
//     /   \     |  |      /  _____| /  __  \
//    /  ^  \    |  |     |  |  __  |  |  |  |
//   /  /_\  \   |  |     |  | |_ | |  |  |  |
//  /  _____  \  |  `----.|  |__| | |  `--'  |
// /__/     \__\ |_______| \______|  \______/
//
//               wilmer 2019/08/30
//---------------------------------------------

//import static life.expert.common.base.Preconditions.*;  //checkCollection

import static reactor.core.publisher.Mono.*;
//import static  reactor.function.TupleUtils.*; //reactor's tuple->R INTO func->R

import life.expert.riso.common.PositivePoint;
import life.expert.riso.domain.model.Drawing;

import static life.expert.common.reactivestreams.Preconditions.*; //reactive check
import static life.expert.common.reactivestreams.Patterns.*;    //reactive helper functions

//import static io.vavr.API.*;                           //conflicts with my reactive For-comprehension

import static io.vavr.API.$;                            // pattern matching
import static io.vavr.API.Case;
import static io.vavr.API.Match;
//import static java.util.function.Predicate.*;           //isEqual streamAPI

import static io.vavr.API.CheckedFunction;//checked functions
import static io.vavr.API.unchecked;    //checked->unchecked
import static io.vavr.API.Function;     //lambda->Function3
import static io.vavr.API.Tuple;

import io.vavr.control.Try;                               //try

//import java.util.List;                                  //usual list
//import io.vavr.collection.List;                         //immutable List
//import com.google.common.collect.*;                     //ImmutableList

import life.expert.riso.domain.model.Figure;
import life.expert.riso.domain.model.builder.LineBuilder;
import life.expert.value.string.SolidString;
import lombok.AllArgsConstructor;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;

import io.vavr.Tuple;
import io.vavr.Tuple5
	;
import io.vavr.match.annotation.Patterns;
import io.vavr.match.annotation.Unapply;

import com.google.common.collect.ComparisonChain;

import life.expert.common.function.TupleUtils;
import lombok.AccessLevel;

import reactor.core.publisher.Mono;

/**<pre>
 * simple immutable class: int int int int char
 *
 * - pattern new-call
 * - the class not for inheritance
 *
 * - only the monoOf.. factory methods is allowed, because it allows you to lazily create objects only with a real subscription
 * - 'of' - factory method is prohibited because it is intended only for easy creation of objects in tests, please use pure functional methods monoOf.., without raise exceptions.
 *
 * - in order not to do the same checks all the time, they are placed in special objects-preconditions.
 * Thus, if such an object is transferred to the input, then we know that it always contains the correct data.
 * Nevertheless, for convenience, methods accepting simple parameters are available, then they internally use the same precondition objects for verification
 *
 *
 *
 *
 * Preconditions: in outer precondition-objects or inside (see explanation above)
 * Postconditions: none
 * Side effects: none
 * Tread safety:  Immutable
 </pre>*/
@Value
@AllArgsConstructor( access = AccessLevel.PRIVATE )
@Patterns /*pattern matching in vavr*/
@Slf4j
public final class Line
	implements Figure,
	           Comparable<Line>
	{
	
	/**
	 * x0
	 *
	 * -- SETTER --
	 *
	 * @param x0
	 * 	x0
	 * @return x0
	 *
	 * 	-- GETTER --
	 * @return x0
	 * 	the x0
	 */
	private final int x0;
	
	/**
	 * y0
	 *
	 * -- SETTER --
	 *
	 * @param y0
	 * 	y0
	 * @return y0
	 *
	 * 	-- GETTER --
	 * @return y0
	 * 	the y0
	 */
	private final int y0;
	
	/**
	 * x1
	 *
	 * -- SETTER --
	 *
	 * @param x1
	 * 	x1
	 * @return x1
	 *
	 * 	-- GETTER --
	 * @return x1
	 * 	the x1
	 */
	private final int x1;
	
	/**
	 * y1
	 *
	 * -- SETTER --
	 *
	 * @param y1
	 * 	y1
	 * @return y1
	 *
	 * 	-- GETTER --
	 * @return y1
	 * 	the y1
	 */
	private final int y1;
	
	/**
	 * character
	 *
	 * -- SETTER --
	 *
	 * @param character
	 * 	character
	 * @return character
	 *
	 * 	-- GETTER --
	 * @return character
	 * 	the character
	 */
	private final char character;
	
	//<editor-fold desc="basic constructors">
	
	/*
	Other factories use this method to create an object.
	He himself calls the private constructor to create the object.
	* */
	private static Mono<Line> monoOf_( final int x0 ,
	                                   final int y0 ,
	                                   final int x1 ,
	                                   final int y1 ,
	                                   final char character )
		{
		return fromSupplier( () -> new Line( x0 , y0 , x1 , y1 , character ) );
		}
	
	//</editor-fold>
	
	//<editor-fold desc="object to tuple conversions">
	
	/**
	 * pattern matching in vavr
	 * The method helps with conversion operations Line-&gt;Tuple
	 *
	 * - you need add static import to method with pattern matching
	 * import static life.expert.riso.common.LinePatterns.*;
	 *
	 * @param object
	 * 	the object
	 *
	 * @return the tuple 5
	 */
	@Unapply
	public static Tuple5<Integer,Integer,Integer,Integer,Character> Line( Line object )
		{
		return Tuple.of( object.getX0() , object.getY0() , object.getX1() , object.getY1() , object.getCharacter() );
		}
	
	//</editor-fold>
	
	@Override
	public int compareTo( Line o )
		{
		return ComparisonChain.start()
		                      .compare( this.x0 , o.x0 )
		                      .compare( this.y0 , o.y0 )
		                      .compare( this.x1 , o.x1 )
		                      .compare( this.y1 , o.y1 )
		                      .compare( this.character , o.character )
		                      .result();
		}
	
	@Override
	public Mono<Figure> draw( final Drawing canvas )
		{
		if( canvas == null )
			return illegalArgumentMonoError( "Canvas must not be empty" );
		else if( !( canvas.getXMax() >= this.getX0() && canvas.getXMax() >= this.getX1() && canvas.getYMax() >= this.getY0() && canvas.getYMax() >= this.getY1() ) )
			return illegalArgumentMonoError( "Figure must be inside canvas" );
		else
			return just( canvas ).flatMap( this::draw_ )
			                     .thenReturn( this );
		}
	
	private Mono<Void> draw_( final Drawing canvas )
		{
		int x_0 = this.x0;
		int x_1 = this.x1;
		int y_0 = this.y0;
		int y_1 = this.y1;
		
		if( x_1 - x_0 < 0 )
			{
			// Swap x0 and x1
			x_0 = x_0 ^ x_1;
			x_1 = x_0 ^ x_1;
			x_0 = x_0 ^ x_1;
			// Swap y0 and y1
			y_0 = y_0 ^ y_1;
			y_1 = y_0 ^ y_1;
			y_0 = y_0 ^ y_1;
			}
		
		float deltaX     = x_1 - x_0;
		float deltaY     = y_1 - y_0;
		float signumY    = Math.signum( deltaY );
		float error      = 0;
		float deltaError = ( deltaX == 0 ) ? Math.abs( deltaY ) + 1 : Math.abs( deltaY / deltaX );
		int   y          = y_0;
		
		// Finds and fills all pixels between the two points
		for( int x = x_0 ;
		     x <= x_1 ;
		     x++ )
			{
			canvas.putPixel( x , y , character );
			error += deltaError;
			
			while( error >= 0.5 && signumY * y <= signumY * y_1 )
				{
				canvas.putPixel( x , y , character );
				y += signumY;
				error--;
				}
			}
		
		//log("inside line:"+ ((Canvas)canvas).makeScreen() );
		return empty();
		}
	
	//<editor-fold desc="builder pattern">
	
	/**
	 * <pre>
	 * Classic builder patterns for creating  Line.
	 * </pre>
	 *
	 * @return the line builder
	 */
	public static LineBuilder builder()
		{
		return new Builder();
		}
	
	/**
	 * <pre> * The type Builder.
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
	public static final class Builder
		implements LineBuilder
		{
		
		private int x0;
		
		private int y0;
		
		private int x1;
		
		private int y1;
		
		private char character;
		
		Builder()
			{
			}
		
		//to do Please use AKA preconditions
		@Override
		public Builder startPoint( final int x0 ,
		                           final int y0 )
			{
			this.x0 = x0;
			this.y0 = y0;
			return this;
			}
		
		@Override
		public Builder endPoint( final int x1 ,
		                         final int y1 )
			{
			this.x1 = x1;
			this.y1 = y1;
			return this;
			}
		
		@Override
		public Builder filler( char character )
			{
			this.character = character;
			return this;
			}
		
		@Override
		public Builder startPoint( PositivePoint startPoint )
			{
			this.x0 = startPoint.getX();
			this.y0 = startPoint.getY();
			return this;
			}
		
		@Override
		public Builder endPoint( PositivePoint endPoint )
			{
			this.x1 = endPoint.getX();
			this.y1 = endPoint.getY();
			return this;
			}
		
		@Override
		public Builder startPointX( int x0 )
			{
			this.x0 = x0;
			return this;
			}
		
		@Override
		public Builder startPointY( int y0 )
			{
			this.x0 = x0;
			return this;
			}
		
		@Override
		public Builder endPointX( int x1 )
			{
			this.x1 = x1;
			return this;
			}
		
		@Override
		public Builder endPointY( int y1 )
			{
			this.y1 = y1;
			return this;
			}
		
		/**
		 * Create Line from simple arguments
		 * Only the monoOf.. factory methods is allowed, because it allows you to lazily create objects only with a real subscription
		 *
		 * - in order not to do the same checks all the time, they are placed in special objects-preconditions.
		 * Thus, if such an object is transferred to the input, then we know that it always contains the correct data.
		 * Nevertheless, for convenience, methods accepting simple parameters are available, then they internally use the same precondition objects for verification
		 *
		 * @return the Mono with lazyli created object
		 *
		 * @implNote to create objects, this method calls the private factory monoOf_
		 * 	to verify objects, this method uses precondition-objects
		 */
		@Override
		public final Mono<Figure> buildMono()
			{
			return PositivePoint.monoOf( x0 , y0 )
			                    .then( PositivePoint.monoOf( x1 , y1 ) )
			                    .then( monoOf_( x0 , y0 , x1 , y1 , character ) )
			                    .cast( Figure.class );
			}
		
		/**
		 * <pre>
		 * Classic builder pattern for creating  Line.
		 * This factory method is prohibited because it is intended only for easy creation of objects in tests
		 * </pre>
		 *
		 * @return the figure
		 *
		 * @throws IllegalArgumentException
		 * 	if the input arguments do not satisfy the preconditions
		 * @deprecated please use pure functional methods monoOf.., without raise exceptions.
		 */
		@Deprecated
		public final Figure build()
			{
			return buildMono().block();
			}
		
		/**
		 * Builder pattern method for creating objects wrapped into Try.
		 *
		 * For example, if this class is a precondition object and you need to check it and then pass it to the input of another object of the subject domain
		 * This method is supposed to be used when you need to get an error immediately (not lazily),
		 * for example, if the message is immediately returned to the user UI and not wait
		 * when, for example at night, lazy processing occurs and a user error is detected
		 *
		 * @return the Try with Success or Failure inside
		 */
		@Override
		public final Try<Figure> buildTry()
			{
			return tryFromMono( buildMono() );
			}
			
		}
	
	//</editor-fold>
	
	}

