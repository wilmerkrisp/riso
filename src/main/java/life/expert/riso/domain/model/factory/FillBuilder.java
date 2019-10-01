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
import life.expert.riso.common.PositivePoint;
import life.expert.riso.domain.model.Figure;
import reactor.core.publisher.Mono;


/**
 * <pre>
 *
 *
 * }*</pre>
 *
 * @author wilmer
 * @version 1.0
 * @since 1.0 wilmer draft
 */
public interface FillBuilder {

  /**
   * Point fill builder.
   *
   * @param x0 the x 0
   * @param y0 the y 0
   * @return the fill builder
   */
  public FillBuilder point(final int x0,
      final int y0);

  /**
   * Point fill builder.
   *
   * @param startPoint the start point
   * @return the fill builder
   */
  public FillBuilder point(PositivePoint startPoint);

  /**
   * Filler fill builder.
   *
   * @param character the character
   * @return the fill builder
   */
  public FillBuilder filler(char character);

  /**
   * Point x fill builder.
   *
   * @param x0 the x 0
   * @return the fill builder
   */
  public FillBuilder pointX(int x0);

  /**
   * Point y fill builder.
   *
   * @param y0 the y 0
   * @return the fill builder
   */
  public FillBuilder pointY(int y0);

  /**
   * Build mono mono.
   *
   * @return the mono
   */
  public Mono<Figure> buildMono();

  /**
   * Build try try.
   *
   * @return the try
   */
  public Try<Figure> buildTry();

  /**
   * Build figure.
   *
   * @return the figure
   */
  public Figure build();

}
