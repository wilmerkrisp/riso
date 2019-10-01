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
 * simple mutable class: int int char
 *
 * - pattern new-set-call
 * - not for inheritance
 *
 * {@code
 * 	  //pattern new-set-call
 * 	  FillDataTransferObject o = new FillDataTransferObject();
 * o.setX("a");
 * o.compute();
 *    }*
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
public final class FillDataTransferObject {

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
   * x
   * <p>
   * -- SETTER --
   *
   * @param x x
   * <p>
   * -- GETTER --
   * @return the x
   */
  private int x;

  /**
   * y
   * <p>
   * -- SETTER --
   *
   * @param y y
   * <p>
   * -- GETTER --
   * @return the y
   */
  private int y;

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
