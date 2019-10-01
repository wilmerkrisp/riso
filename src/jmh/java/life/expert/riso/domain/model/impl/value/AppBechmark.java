package life.expert.riso.domain.model.impl.value;//@Header@
//--------------------------------------------------------------------------------
//
//                          graph  life.expert.riso.base
//                           wilmer 2019/01/31
//
//--------------------------------------------------------------------------------

import life.expert.riso.domain.model.impl.entity.DefaultCanvas;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.infra.Blackhole;

/**
 * The type App bechmark.
 */
public class AppBechmark {

  /**
   * Fill test.
   *
   * @param data the data
   * @param hole the hole
   */
  @Benchmark
  public void fillTest(ExperimentData data,
      Blackhole hole) {
    DefaultCanvas.builder()
        .size(data.iterations, data.iterations)
        .buildMono()
        .flatMap(c -> c.draw(Fill.builder()
            .point(1, 1)
            .filler('o')
            .build()))
        .subscribe(hole::consume);
  }

}
