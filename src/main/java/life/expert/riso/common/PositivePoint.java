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

import static life.expert.common.reactivestreams.Patterns.tryFromMono;
import static life.expert.common.reactivestreams.Preconditions.illegalArgumentMonoError;
import static reactor.core.publisher.Mono.fromSupplier;

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


/**
 * <pre>
 * This is object-precondition represents point with two positive &gt;=0 coordinates inside
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
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Patterns /*pattern matching in vavr*/
@Slf4j
public final class PositivePoint
    implements Comparable<PositivePoint> {

  /**
   * x
   * <p>
   * -- SETTER --
   *
   * @param x x
   * @return x
   * <p>
   * -- GETTER --
   * @return x the x
   */
  private final int x;

  /**
   * y
   * <p>
   * -- SETTER --
   *
   * @param y y
   * @return y
   * <p>
   * -- GETTER --
   * @return y the y
   */
  private final int y;

  //<editor-fold desc="basic constructors">

  /*
  Other factories use this method to create an object.
  He himself calls the private constructor to create the object.
  * */
  private static Mono<PositivePoint> monoOf_(final int x,
      final int y) {
    return fromSupplier(() -> new PositivePoint(x, y));
  }

  /**
   * Create PositivePoint from simple arguments Only the monoOf.. factory methods is allowed,
   * because it allows you to lazily create objects only with a real subscription
   *
   * @param x the x
   * @param y the y
   * @return the Mono with lazyli created object
   * @implNote to create objects, this method calls the private factory monoOf_
   */
  public static Mono<PositivePoint> monoOf(final int x,
      final int y) {

	  if (x < 0 || y < 0) {
		  return illegalArgumentMonoError("Point coordinates must be positive");
	  } else {
		  return monoOf_(x, y);
	  }
  }

  /**
   * <pre>
   * Classic fabric method for creating  PositivePoint.
   * This factory method is prohibited because it is intended only for easy creation of objects in tests
   *
   *
   * @param x the x
   * @param y the y
   * @return the positive point
   * @throws IllegalArgumentException if the input arguments do not satisfy the preconditions
   * </pre>
   */
  public static PositivePoint of(final int x,
      final int y) {
    return monoOf(x, y).block();
  }

  /**
   * Create PositivePoint from Mono with Tuple inside The method helps chaining flows together
   *
   * @param tuple the tuple
   * @return the Mono with lazyli created object
   */
  public static Mono<PositivePoint> monoOfMono(Mono<Tuple2<Integer, Integer>> tuple) {
	  if (tuple == null) {
		  return illegalArgumentMonoError("Input Mono must not be null.");
	  } else {
		  return tuple.flatMap(PositivePoint::monoOfTuple);
	  }
  }

  /**
   * Standard shallow copy factory
   *
   * @param other the other object
   * @return the Mono with lazyli created object
   * @implNote to create objects, this method calls the private factory monoOf_
   */
  public static Mono<PositivePoint> copyOf(final PositivePoint other) {
    return monoOf_(other.getX(), other.getY());
  }

  //</editor-fold>

  //<editor-fold desc="using and outstanding preconditions">

  /**
   * Fabric method for creating objects wrapped into Try.
   * <p>
   * For example, if this class is a precondition object and you need to check it and then pass it
   * to the input of another object of the subject domain This method is supposed to be used when
   * you need to get an error immediately (not lazily), for example, if the message is immediately
   * returned to the user UI and not wait when, for example at night, lazy processing occurs and a
   * user error is detected
   *
   * @param x the x
   * @param y the y
   * @return the Try with Success or Failure inside
   */
  public static Try<PositivePoint> tryOf(final int x,
      final int y) {
    return tryFromMono(monoOf(x, y));
  }

  //</editor-fold>

  //<editor-fold desc="object to tuple conversions">

  /**
   * pattern matching in vavr The method helps with conversion operations PositivePoint-&gt;Tuple
   * <p>
   * - you need add static import to method with pattern matching import static
   * life.expert.riso.common.PositivePointPatterns.*;
   *
   * @param object the object
   * @return the tuple 2
   */
  @Unapply
  public static Tuple2<Integer, Integer> PositivePoint(PositivePoint object) {
    return Tuple.of(object.getX(), object.getY());

  }

  /**
   * Create PositivePoint from Tuple The method helps with conversion operations
   * Tuple-&gt;PositivePoint
   *
   * @param tuple the tuple
   * @return the Mono with lazyli created object
   */
  public static Mono<PositivePoint> monoOfTuple(Tuple2<Integer, Integer> tuple) {
	  if (tuple == null) {
		  return illegalArgumentMonoError("Input tuple must not be null.");
	  } else {
		  return TupleUtils.function(PositivePoint::monoOf)
			  .apply(tuple);
	  }
  }

  //</editor-fold>

  @Override
  public int compareTo(PositivePoint o) {
    return ComparisonChain.start()
        .compare(this.x, o.x)
        .compare(this.y, o.y)
        .result();
  }
}
