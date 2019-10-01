package life.expert.riso.domain.service.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

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


/**
 * <pre>
 * simple mutable class: int int int int char
 *
 * - pattern new-set-call
 * - not for inheritance
 *
 * {@code
 * //pattern new-set-call
 * LineDataTransferObject o = new LineDataTransferObject();
 * o.setX0("a");
 * o.compute();
 * }**
 *
 *
 *
 * Preconditions: none
 * Postconditions: none
 * Side effects: none
 * Tread safety:  not thread safe
 * </pre>
 */
@Data
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
public final class LineDataTransferObject {

  /**
   * canvas identificator for drawing figure
   * <p>
   * -- SETTER --
   *
   * @param id id
   * <p>
   * -- GETTER --
   * @return the id
   */
  private String canvasId;

  /**
   * x0
   * <p>
   * -- SETTER --
   *
   * @param x0 x0
   * <p>
   * -- GETTER --
   * @return the x0
   */
  private int x0;

  /**
   * y0
   * <p>
   * -- SETTER --
   *
   * @param y0 y0
   * <p>
   * -- GETTER --
   * @return the y0
   */
  private int y0;

  /**
   * x1
   * <p>
   * -- SETTER --
   *
   * @param x1 x1
   * <p>
   * -- GETTER --
   * @return the x1
   */
  private int x1;

  /**
   * y1
   * <p>
   * -- SETTER --
   *
   * @param y1 y1
   * <p>
   * -- GETTER --
   * @return the y1
   */
  private int y1;

  /**
   * character
   * <p>
   * -- SETTER --
   *
   * @param character character
   * <p>
   * -- GETTER --
   * @return the character
   */
  private char character;

}
