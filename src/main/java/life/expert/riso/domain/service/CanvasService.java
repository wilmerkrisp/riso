package life.expert.riso.domain.service;
//@Header@
//--------------------------------------------------------------------------------
//
//                          riso  life.expert.riso.domain.service
//                           wilmer 2019/08/13
//
//--------------------------------------------------------------------------------

import life.expert.riso.domain.service.dto.CanvasDataTransferObject;
import life.expert.riso.domain.service.dto.FillDataTransferObject;
import life.expert.riso.domain.service.dto.LineDataTransferObject;
import life.expert.riso.domain.service.dto.RectangleDataTransferObject;
import life.expert.riso.domain.service.dto.ResultDataTransferObject;
import reactor.core.publisher.Mono;

/**
 * The interface Canvas service.
 */
public interface CanvasService {

  /**
   * Create canvas mono.
   *
   * @param canvas the canvas
   * @return the mono
   */
  public Mono<ResultDataTransferObject> createCanvas(CanvasDataTransferObject canvas);

  /**
   * New line mono.
   *
   * @param line the line
   * @return the mono
   */
  public Mono<ResultDataTransferObject> newLine(LineDataTransferObject line);

  /**
   * New rectangle mono.
   *
   * @param rectangle the rectangle
   * @return the mono
   */
  public Mono<ResultDataTransferObject> newRectangle(RectangleDataTransferObject rectangle);

  /**
   * New fill mono.
   *
   * @param fill the fill
   * @return the mono
   */
  public Mono<ResultDataTransferObject> newFill(FillDataTransferObject fill);

}
