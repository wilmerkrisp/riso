package life.expert.riso.domain.model.impl.entity;


import static life.expert.common.reactivestreams.Patterns.range;
import static life.expert.common.reactivestreams.Patterns.tryFromMono;
import static reactor.core.publisher.Mono.fromSupplier;

import com.google.common.base.Strings;
import io.vavr.Tuple;
import io.vavr.Tuple2;
import io.vavr.control.Try;
import io.vavr.match.annotation.Unapply;
import java.util.Optional;
import life.expert.riso.common.PositiveSize;
import life.expert.riso.domain.model.Canvas;
import life.expert.riso.domain.model.Figure;
import life.expert.riso.domain.model.factory.CanvasBuilder;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.relational.core.mapping.Table;
import reactor.core.publisher.Mono;


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
@Slf4j
@ToString
@Data
@Table
public class DefaultCanvas
    implements Canvas {

  @Id
  private String id;

  private String name;

  private int width;

  private int height;

  @Setter(AccessLevel.NONE)
  //@Transient
  private int xMax;

  @Setter(AccessLevel.NONE)
  //@Transient
  private int yMax;


  private String screen;

  //<editor-fold desc="basic constructors">

  private DefaultCanvas() {
    throw new UnsupportedOperationException("Please use constructor with parameters.");
  }

  /**
   * reactive repository use this method to create an object with fulfilled screen He himself calls
   * the private constructor to create the object.
   *
   * @param id     the id
   * @param name   the name
   * @param width  the width
   * @param height the height
   * @param xMax   the x max
   * @param yMax   the y max
   * @param screen the screen
   */
  @PersistenceConstructor
  public DefaultCanvas(final String id,
      final String name,
      final int width,
      final int height,
      final int xMax,
      final int yMax,
      final String screen) {
    var precondition = PositiveSize.of(width, height);
		if (id == null || name == null || width < 3 || height < 3 || screen == null || xMax < 1
				|| yMax < 1) {
			throw new IllegalArgumentException(
					"Violated precondition: id == null || name == null || width < 3 || height < 3 || screen == null || xMax < 1 || yMax < 1");
		}

    this.id = id;
    this.name = name;
    this.xMax = xMax;
    this.yMax = yMax;
    this.width = width;  //+ 2;
    this.height = height;// + 2;
    this.screen = screen;
  }

  private DefaultCanvas(int width,
      int height) {
    this("CANVAS" + RandomStringUtils.random(10, false, true), "default", width + 2, height + 2,
        width, height, Strings.repeat(Strings.repeat(" ", width + 2) + "\n", height + 2));

    initScreen_();
  }

  private static Mono<DefaultCanvas> monoOf_(int width,
      int height) {
    return fromSupplier(() -> new DefaultCanvas(width, height));
  }

  //<editor-fold desc="object to tuple conversions">

  /**
   * pattern matching in vavr The method helps with conversion operations Sample-&gt;Tuple
   * <p>
   * - you need add static import to method with pattern matching import static
   * life.expert.riso.common.SamplePatterns.*;
   *
   * @param object the object
   * @return the tuple 2
   */
  @Unapply
  public static Tuple2<Integer, Integer> DefaultCanvas(DefaultCanvas object) {
    return Tuple.of(object.getWidth(), object.getHeight());
  }

  //</editor-fold>

  private void initScreen_() {

    int max_height = this.height - 1;
    int max_width = this.width - 1;

    // Upper and lower borders
    range(0, max_width).subscribe(i ->
    {
      setPixel_(0, i, '-');
      setPixel_(max_height, i, '-');
    });

    // Left and right borders
    range(1, max_height - 1).subscribe(i ->
    {
      setPixel_(i, 0, '|');
      setPixel_(i, max_width, '|');
    });


  }

  //</editor-fold>

  //<editor-fold desc="drawing methods">
  @Override
  public void putPixel(int x,
      int y,
      char c) {
    //coordinates starts from 1
		if (x < 1 || x > this.xMax || y < 1 || y > this.yMax) {
			return;
		} else {
			setPixel_(y, x, c);
		}
  }

  /*
   * Working with screen in this class throught this functions
   * */
  private void setPixel_(int y,
      int x,
      char character) {
		if (x >= this.width || y >= this.height) {
			return;
		}

    int pos = y * (this.width + 1) + x;
    screen = new StringBuilder(screen).replace(pos, pos + 1, String.valueOf(character))
        .toString();

  }

  @Override
  public Optional<Character> getPixel(int x,
      int y) {
    //coordinates starts from 1
    return (x < 1 || x > this.xMax || y < 1 || y > this.yMax) ? Optional.empty()
        : Optional.of(getPixel_(y, x));

  }

  private char getPixel_(int y,
      int x) {
		if (x >= this.width || y >= this.height) {
			return ' ';
		}

    int pos = y * (this.width + 1) + x;
    return screen.charAt(pos);
  }

  /**
   * return terminal image of screen as string
   *
   * @return the string
   */
  @Override
  public Mono<String> makeScreen() {
    return Mono.just(screen.toString());
  }

  /**
   * Put figure on canvas
   *
   * @param figure the figure
   * @return the mono with Canvas
   */
  @Override
  public Mono<Canvas> draw(Figure figure) {
    return figure.draw(this)
        .thenReturn(this);
  }

  /**
   * Put figure (as Mono) on canvas
   *
   * @param figure the figure
   * @return the mono with Canvas
   */
  @Override
  public Mono<Canvas> draw(Mono<Figure> figure) {
    return figure.flatMap(f -> f.draw(this))
        .thenReturn(this);
  }

  //</editor-fold>

  //<editor-fold desc="builder pattern">

  /**
   * <pre>
   * Classic builder patterns for creating  Line.
   * </pre>
   *
   * @return the line builder
   */
  public static CanvasBuilder builder() {
    return new DefaultCanvas.Builder();
  }

  /**
   * <pre> * The type Builder.
   *
   * Preconditions: none
   * Postconditions: none
   * Side effects: none
   * Tread safety: Not thread-safe
   * </pre>
   */
  public static final class Builder
      implements CanvasBuilder {

    private int width;

    private int height;

    Builder() {
    }

    @Override
    public CanvasBuilder size(final int width,
        final int height) {
      this.width = width;
      this.height = height;
      return this;
    }

    @Override
    public CanvasBuilder size(PositiveSize positiveSize) {
      this.width = positiveSize.getWidth();
      this.height = positiveSize.getHeight();
      return this;
    }

    @Override
    public CanvasBuilder width(int width) {
      this.width = width;
      return this;
    }

    @Override
    public CanvasBuilder height(int height) {
      this.height = height;
      return this;
    }

    /**
     * Create Line from simple arguments Only the monoOf.. factory methods is allowed, because it
     * allows you to lazily create objects only with a real subscription
     * <p>
     * - in order not to do the same checks all the time, they are placed in special
     * objects-preconditions. Thus, if such an object is transferred to the input, then we know that
     * it always contains the correct data. Nevertheless, for convenience, methods accepting simple
     * parameters are available, then they internally use the same precondition objects for
     * verification
     *
     * @return the Mono with lazyli created object
     * @implNote to create objects, this method calls the private factory monoOf_ to verify objects,
     * this method uses precondition-objects
     */
    @Override
    public final Mono<Canvas> buildMono() {
      return PositiveSize.monoOf(width, height)
          .then(monoOf_(width, height))
          .cast(Canvas.class);
    }

    /**
     * <pre>
     * Classic builder pattern for creating  Line.
     * This factory method is prohibited because it is intended only for easy creation of objects in tests
     * </pre>
     *
     * @return the figure
     * @throws IllegalArgumentException if the input arguments do not satisfy the preconditions
     */
    @Override
    public final Canvas build() {
      return buildMono().block();
    }

    /**
     * Builder pattern method for creating objects wrapped into Try.
     * <p>
     * For example, if this class is a precondition object and you need to check it and then pass it
     * to the input of another object of the subject domain This method is supposed to be used when
     * you need to get an error immediately (not lazily), for example, if the message is immediately
     * returned to the user UI and not wait when, for example at night, lazy processing occurs and a
     * user error is detected
     *
     * @return the Try with Success or Failure inside
     */
    @Override
    public final Try<Canvas> buildTry() {
      return tryFromMono(buildMono());
    }

  }

  //</editor-fold>

}

