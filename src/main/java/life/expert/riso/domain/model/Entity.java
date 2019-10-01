package life.expert.riso.domain.model;
//@Header@
//--------------------------------------------------------------------------------
//
//                          riso  life.expert.riso.domain.model.impl.entity
//                           wilmer 2019/07/23
//
//--------------------------------------------------------------------------------

/**
 * Base abstract class for aggregate (DDD pattern)
 *
 * @param <ID> the type of entity ID
 */
public interface Entity<ID> {

  /**
   * Gets id.
   *
   * @return the id
   */
  ID getId();


  /**
   * Gets name.
   *
   * @return the name
   */
  String getName();


}
