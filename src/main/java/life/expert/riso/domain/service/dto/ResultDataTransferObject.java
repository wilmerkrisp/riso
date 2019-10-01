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
//               wilmer 2019/09/10
//---------------------------------------------


/**
 * <pre>
 * simple mutable class: String String
 *
 * - pattern new-set-call
 * - not for inheritance
 *
 * {@code
 * 	  //pattern new-set-call
 * 	  ResultDataTransferObject o = new ResultDataTransferObject();
 * o.setCanvasId("a");
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
public final class ResultDataTransferObject {

  /**
   * canvasId
   * <p>
   * -- SETTER --
   *
   * @param canvasId canvasId
   * <p>
   * -- GETTER --
   * @return the canvasId
   */
  private String canvasId;

  /**
   * screen
   * <p>
   * -- SETTER --
   *
   * @param screen screen
   * <p>
   * -- GETTER --
   * @return the screen
   */
  private String screen;

}
