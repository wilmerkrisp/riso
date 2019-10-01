package life.expert.riso.domain.model.impl.factory;
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

import life.expert.riso.domain.model.factory.CanvasBuilder;
import life.expert.riso.domain.model.factory.DrawingFactory;
import life.expert.riso.domain.model.factory.FillBuilder;
import life.expert.riso.domain.model.factory.LineBuilder;
import life.expert.riso.domain.model.factory.RectangleBuilder;
import life.expert.riso.domain.model.impl.entity.DefaultCanvas;
import life.expert.riso.domain.model.impl.value.Fill;
import life.expert.riso.domain.model.impl.value.Line;
import life.expert.riso.domain.model.impl.value.Rectangle;
import org.springframework.stereotype.Component;

//@Header@
//--------------------------------------------------------------------------------
//
//                          riso  life.expert.riso.domain.model
//                           wilmer 2019/08/13
//
//--------------------------------------------------------------------------------

/**
 * <pre> * The type Default drawing factory.
 *
 * Preconditions: none
 * Postconditions: none
 * Side effects: none
 * Tread safety: Not thread-safe
 *
 * </pre>
 */
@Component
public class DefaultDrawingFactory
    implements DrawingFactory {

  @Override
  public CanvasBuilder newCanvasBuilder() {
    return DefaultCanvas.builder();
  }

  @Override
  public FillBuilder newFillBuilder() {
    return Fill.builder();

  }

  @Override
  public LineBuilder newLineBuilder() {
    return Line.builder();
  }

  @Override
  public RectangleBuilder newRectangleBuilder() {
    return Rectangle.builder(this);
  }

}
