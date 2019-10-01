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
 * The interface Rectangle builder.
 */
public interface RectangleBuilder {

  /**
   * Start point rectangle builder.
   *
   * @param x0 the x 0
   * @param y0 the y 0
   * @return the rectangle builder
   */
  public RectangleBuilder startPoint(final int x0,
      final int y0);

  /**
   * Start point rectangle builder.
   *
   * @param startPoint the start point
   * @return the rectangle builder
   */
  public RectangleBuilder startPoint(PositivePoint startPoint);

  /**
   * Start point x rectangle builder.
   *
   * @param x0 the x 0
   * @return the rectangle builder
   */
  public RectangleBuilder startPointX(int x0);

  /**
   * Start point y rectangle builder.
   *
   * @param y0 the y 0
   * @return the rectangle builder
   */
  public RectangleBuilder startPointY(int y0);

  /**
   * End point rectangle builder.
   *
   * @param x1 the x 1
   * @param y1 the y 1
   * @return the rectangle builder
   */
  public RectangleBuilder endPoint(final int x1,
      final int y1);

  /**
   * End point rectangle builder.
   *
   * @param endPoint the end point
   * @return the rectangle builder
   */
  public RectangleBuilder endPoint(PositivePoint endPoint);

  /**
   * End point x rectangle builder.
   *
   * @param x1 the x 1
   * @return the rectangle builder
   */
  public RectangleBuilder endPointX(int x1);

  /**
   * End point y rectangle builder.
   *
   * @param y1 the y 1
   * @return the rectangle builder
   */
  public RectangleBuilder endPointY(int y1);

  /**
   * Filler rectangle builder.
   *
   * @param character the character
   * @return the rectangle builder
   */
  public RectangleBuilder filler(char character);

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
