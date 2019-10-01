package life.expert.riso.common;

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

import com.google.common.collect.ComparisonChain;
import io.vavr.Tuple;
import io.vavr.Tuple2;
import io.vavr.control.Try;
import io.vavr.match.annotation.Patterns;
import io.vavr.match.annotation.Unapply;
import life.expert.common.function.TupleUtils;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

import static life.expert.common.reactivestreams.Patterns.tryFromMono;
import static life.expert.common.reactivestreams.Preconditions.illegalArgumentMonoError;
import static reactor.core.publisher.Mono.fromSupplier;



/**
 * <pre>
 * This is object-precondition represents size with two positive &gt;=1 width and height inside
 * - in order not to do the same checks all the time,
 * - Thus, if such an object is transferred to the input, then we know that it always contains the correct data.
 *
 * - pattern new-call
 * - the class not for inheritance
 *
 * - only the monoOf.. factory methods is allowed, because it allows you to lazily create objects only with a real subscription
 * - 'of' - factory method is prohibited because it is intended only for easy creation of objects in tests, please use pure functional methods monoOf.., without raise exceptions.
 * </pre>
 */
@Value
@AllArgsConstructor( access = AccessLevel.PRIVATE )
@Patterns /*pattern matching in vavr*/
@Slf4j
public final class PositiveSize
	implements Comparable<PositiveSize>
	{
	
	/**
	 * width
	 *
	 * -- SETTER --
	 *
	 * @param width
	 * 	width
	 * @return width
	 *
	 * 	-- GETTER --
	 * @return width
	 * 	the width
	 */
	private final int width;
	
	/**
	 * height
	 *
	 * -- SETTER --
	 *
	 * @param height
	 * 	height
	 * @return height
	 *
	 * 	-- GETTER --
	 * @return height
	 * 	the height
	 */
	private final int height;
	
	//<editor-fold desc="basic constructors">
	
	/*
	Other factories use this method to create an object.
	He himself calls the private constructor to create the object.
	* */
	private static Mono<PositiveSize> monoOf_( final int width ,
	                                           final int height )
		{
		return fromSupplier( () -> new PositiveSize( width , height ) );
		}
	
	/**
	 * Create PositiveSize from simple arguments
	 * Only the monoOf.. factory methods is allowed, because it allows you to lazily create objects only with a real subscription
	 *
	 * @param width
	 * 	the width
	 * @param height
	 * 	the item 2
	 *
	 * @return the Mono with lazyli created object
	 *
	 * @implNote to create objects, this method calls the private factory monoOf_ 	to verify objects, this method uses precondition-objects
	 */
	public static Mono<PositiveSize> monoOf( final int width ,
	                                         final int height )
		{
		
		if( width < 1 || height < 1 )
			return illegalArgumentMonoError( "Input arguments must not be null." );
		else
			return monoOf_( width , height );
			
		}
	
	/**
	 * <pre>
	 * Classic fabric method for creating  PositiveSize.
	 * This factory method is prohibited because it is intended only for easy creation of objects in tests
	 *
	 *
	 * @param width the width
	 * @param height the item 2
	 * @return the positive size
	 * @throws IllegalArgumentException if the input arguments do not satisfy the preconditions
	  </pre>
	 */
	
	public static PositiveSize of( final int width ,
	                               final int height )
		{
		return monoOf( width , height ).block();
		}
	
	/**
	 * Create PositiveSize from Mono with Tuple inside
	 * The method helps chaining flows together
	 *
	 * @param tuple
	 * 	the tuple
	 *
	 * @return the Mono with lazyli created object
	 */
	public static Mono<PositiveSize> monoOfMono( Mono<Tuple2<Integer,Integer>> tuple )
		{
		if( tuple == null )
			return illegalArgumentMonoError( "Input Mono must not be null." );
		else
			return tuple.flatMap( PositiveSize::monoOfTuple );
		}
	
	/**
	 * Standard shallow copy factory
	 *
	 * @param other
	 * 	the other object
	 *
	 * @return the Mono with lazyli created object
	 *
	 * @implNote to create objects, this method calls the private factory monoOf_
	 */
	public static Mono<PositiveSize> copyOf( final PositiveSize other )
		{
		return monoOf_( other.getWidth() , other.getHeight() );
		}
	
	//</editor-fold>
	
	//<editor-fold desc="using and outstanding preconditions">
	
	/**
	 * Fabric method for creating objects wrapped into Try.
	 *
	 * For example, if this class is a precondition object and you need to check it and then pass it to the input of another object of the subject domain
	 * This method is supposed to be used when you need to get an error immediately (not lazily),
	 * for example, if the message is immediately returned to the user UI and not wait
	 * when, for example at night, lazy processing occurs and a user error is detected
	 *
	 * @param width
	 * 	the width
	 * @param height
	 * 	the item 2
	 *
	 * @return the Try with Success or Failure inside
	 */
	public static Try<PositiveSize> tryOf( final int width ,
	                                       final int height )
		{
		return tryFromMono( monoOf( width , height ) );
		}
	
	//</editor-fold>
	
	//<editor-fold desc="object to tuple conversions">
	
	/**
	 * pattern matching in vavr
	 * The method helps with conversion operations PositiveSize-&gt;Tuple
	 *
	 * - you need add static import to method with pattern matching
	 * import static life.expert.riso.common.PositiveSizePatterns.*;
	 *
	 * @param object
	 * 	the object
	 *
	 * @return the tuple 2
	 */
	@Unapply
	public static Tuple2<Integer,Integer> PositiveSize( PositiveSize object )
		{
		return Tuple.of( object.getWidth() , object.getHeight() );
		
		}
	
	/**
	 * Create PositiveSize from Tuple
	 * The method helps with conversion operations Tuple-&gt;PositiveSize
	 *
	 * @param tuple
	 * 	the tuple
	 *
	 * @return the Mono with lazyli created object
	 */
	public static Mono<PositiveSize> monoOfTuple( Tuple2<Integer,Integer> tuple )
		{
		if( tuple == null )
			return illegalArgumentMonoError( "Input tuple must not be null." );
		else
			return TupleUtils.function( PositiveSize::monoOf )
			                 .apply( tuple );
		}
	
	//</editor-fold>
	
	@Override
	public int compareTo( PositiveSize o )
		{
		return ComparisonChain.start()
		                      .compare( this.width , o.width )
		                      .compare( this.height , o.height )
		                      .result();
		}
	}
