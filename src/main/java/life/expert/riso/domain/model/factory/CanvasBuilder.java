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
import life.expert.riso.common.PositiveSize;
import life.expert.riso.domain.model.Canvas;
import reactor.core.publisher.Mono;


/**
 * The interface Canvas builder.
 */
public interface CanvasBuilder {

  /**
   * Size canvas builder.
   *
   * @param width  the width
   * @param height the height
   * @return the canvas builder
   */
  public CanvasBuilder size(final int width,
      final int height);

  /**
   * Size canvas builder.
   *
   * @param positiveSize the positive size
   * @return the canvas builder
   */
  public CanvasBuilder size(PositiveSize positiveSize);

  /**
   * Width canvas builder.
   *
   * @param width the width
   * @return the canvas builder
   */
  public CanvasBuilder width(int width);

  /**
   * Height canvas builder.
   *
   * @param height the height
   * @return the canvas builder
   */
  public CanvasBuilder height(int height);

  /**
   * Build mono mono.
   *
   * @return the mono
   */
  public Mono<Canvas> buildMono();

  /**
   * Build try try.
   *
   * @return the try
   */
  public Try<Canvas> buildTry();

  /**
   * Build canvas.
   *
   * @return the canvas
   */
  public Canvas build();

}
