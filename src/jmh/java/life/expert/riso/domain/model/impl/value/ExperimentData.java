package life.expert.riso.domain.model.impl.value;//@Header@
//--------------------------------------------------------------------------------
//
//                          graph  life.expert.riso.base
//                           wilmer 2019/01/31
//
//--------------------------------------------------------------------------------

import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

/**
 * The type Experiment data.
 */
@State(Scope.Thread)
public class ExperimentData {

  @Param({"100",
      "200",
      "300",
      "400",
      "500",
      "600",
      "700",
      "800",
      "900",
      "1000"})
  int iterations;

}
