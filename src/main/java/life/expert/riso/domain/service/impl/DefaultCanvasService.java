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

import static io.vavr.Predicates.not;
import static life.expert.common.reactivestreams.Preconditions.illegalStateMonoError;

import java.util.function.Function;
import life.expert.riso.domain.model.Canvas;
import life.expert.riso.domain.model.factory.DrawingFactory;
import life.expert.riso.domain.repository.CanvasRepository;
import life.expert.riso.domain.service.CanvasService;
import life.expert.riso.domain.service.dto.CanvasDataTransferObject;
import life.expert.riso.domain.service.dto.FillDataTransferObject;
import life.expert.riso.domain.service.dto.LineDataTransferObject;
import life.expert.riso.domain.service.dto.RectangleDataTransferObject;
import life.expert.riso.domain.service.dto.ResultDataTransferObject;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;


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
    implements CanvasService {

  private final CanvasRepository canvasRepository;

  private final DrawingFactory drawingFactory;

  @Getter(AccessLevel.NONE)
  Function<Mono<ResultDataTransferObject>, Publisher<ResultDataTransferObject>> LOWLEVEL_EXCEPTION_WRAPPER = s -> s
      .filter(r -> !r.getCanvasId()
          .isBlank())
      .filter(r -> !r.getScreen()
          .isBlank())
      .onErrorMap(not(err -> err.getClass()
              .equals(IllegalArgumentException.class)),
          err -> new RuntimeException("We are sorry, an unexpected error has occurred", err));

  /**
   * Instantiates a new Canvas service.
   *
   * @param canvasRepository the canvas repository
   * @param drawingFactory   the drawing factory
   */
  @Autowired
  public DefaultCanvasService(@Lazy CanvasRepository canvasRepository,
      @Lazy DrawingFactory drawingFactory) {
    this.canvasRepository = canvasRepository;
    this.drawingFactory = drawingFactory;
  }

  @Override
  //@Transactional
  public Mono<ResultDataTransferObject> createCanvas(@NonNull CanvasDataTransferObject canvas) {
    var c = drawingFactory.newCanvasBuilder()
        .size(canvas.getWidth(), canvas.getHeight())
        .buildMono();

    return getCanvasRepository().saveAll(c)
        .next()
        .zipWhen(Canvas::makeScreen)
        .map(t -> new ResultDataTransferObject(t.getT1()
            .getId(), t.getT2()))
        .transform(LOWLEVEL_EXCEPTION_WRAPPER)
        .switchIfEmpty(illegalStateMonoError(
            "life.expert.riso.domain.service.impl.DefaultCanvasService.createCanvas is empty"));
  }

  @Override
  //@Transactional
  public Mono<ResultDataTransferObject> newLine(@NonNull LineDataTransferObject line) {

    final String canvasId = line.getCanvasId();

	  if (canvasId != null && canvasId.isBlank()) {
		  return illegalStateMonoError("canvas id must not be empty" + canvasId);
	  }

    var l = drawingFactory.newLineBuilder()
        .startPoint(line.getX0(), line.getY0())
        .endPoint(line.getX1(), line.getY1())
        .filler(line.getCharacter())
        .buildMono();

    return getCanvasRepository().findById(canvasId)
        .flatMap(c -> c.draw(l))
        .flatMap(c -> getCanvasRepository().save(c))
        .flatMap(Canvas::makeScreen)
        .filter(not(String::isBlank))/*verify postcondition*/
        .map(s -> new ResultDataTransferObject(canvasId, s))
        .transform(LOWLEVEL_EXCEPTION_WRAPPER)
        .switchIfEmpty(illegalStateMonoError(
            "life.expert.riso.domain.service.impl.DefaultCanvasService.newLine is empty"));

  }

  @Override
  //@Transactional
  public Mono<ResultDataTransferObject> newRectangle(
      @NonNull RectangleDataTransferObject rectangle) {
    final String canvasId = rectangle.getCanvasId();

	  if (canvasId != null && canvasId.isBlank()) {
		  return illegalStateMonoError("canvas id must not be empty");
	  }

    var r = drawingFactory.newRectangleBuilder()
        .startPoint(rectangle.getX0(), rectangle.getY0())
        .endPoint(rectangle.getX1(), rectangle.getY1())
        .filler(rectangle.getCharacter())
        .buildMono();

    return getCanvasRepository().findById(rectangle.getCanvasId())
        .flatMap(c -> c.draw(r))
        .flatMap(c -> getCanvasRepository().save(c))
        .flatMap(Canvas::makeScreen)
        .map(s -> new ResultDataTransferObject(canvasId, s))
        .transform(LOWLEVEL_EXCEPTION_WRAPPER)
        .switchIfEmpty(illegalStateMonoError(
            "life.expert.riso.domain.service.impl.DefaultCanvasService.newRectangle is empty"));
  }

  @Override
  //@Transactional
  public Mono<ResultDataTransferObject> newFill(@NonNull FillDataTransferObject fill) {
    final String canvasId = fill.getCanvasId();

	  if (canvasId != null && canvasId.isBlank()) {
		  return illegalStateMonoError("canvas id must not be empty");
	  }

    var f = drawingFactory.newFillBuilder()
        .point(fill.getX(), fill.getY())
        .filler(fill.getCharacter())
        .buildMono();

    return getCanvasRepository().findById(fill.getCanvasId())
        .flatMap(c -> c.draw(f))
        .flatMap(c -> getCanvasRepository().save(c))
        .flatMap(Canvas::makeScreen)
        .map(s -> new ResultDataTransferObject(canvasId, s))
        .transform(LOWLEVEL_EXCEPTION_WRAPPER)
        .switchIfEmpty(illegalStateMonoError(
            "life.expert.riso.domain.service.impl.DefaultCanvasService.newFill is empty"));
  }

}
