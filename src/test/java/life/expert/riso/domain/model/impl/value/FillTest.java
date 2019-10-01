package life.expert.riso.domain.model.impl.value;


import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import life.expert.riso.domain.model.Canvas;
import life.expert.riso.domain.model.impl.entity.DefaultCanvas;
import life.expert.riso.domain.model.impl.factory.DefaultDrawingFactory;
import org.junit.Before;
import org.junit.Test;
import reactor.test.StepVerifier;

//@Header@
//--------------------------------------------------------------------------------
//
//                          riso  life.expert.riso.domain.model.impl.value
//                           wilmer 2019/07/24
//
//--------------------------------------------------------------------------------

/**
 * The type Fill test.
 */
public class FillTest {

  private Fill fill;

  private Canvas canvas;

  /**
   * Sets up.
   *
   * @throws Exception the exception
   */
  @Before
  public void setUp()
      throws Exception {

    var rect = Rectangle.builder(new DefaultDrawingFactory())
        .startPoint(2, 2)
        .endPoint(9, 9)
        .filler('x')
        .build();

    canvas = DefaultCanvas.builder()
        .size(10, 10)
        .buildMono()
        .flatMap(c -> c.draw(rect))
        .block();

    fill = (Fill) Fill.builder()
        .point(3, 3)
        .filler('o')
        .build();

  }

  /**
   * Mono of.
   */
  @Test
  public void monoOf() {
    var f = Fill.builder()
        .point(10, 10)
        .filler('d')
        .buildMono();
    StepVerifier.create(f)
        .expectNextCount(1)
        .expectComplete()
        .verify();
  }

  /**
   * Draw.
   */
  @Test
  public void draw() {
    var c = canvas.draw(fill)
        .flatMap(x -> canvas.makeScreen());

    StepVerifier.create(c)
        .expectNext("------------\n" + "|          |\n" + "| xxxxxxxx |\n" + "| xoooooox |\n"
            + "| xoooooox |\n" + "| xoooooox |\n" + "| xoooooox |\n" + "| xoooooox |\n"
            + "| xoooooox |\n" + "| xxxxxxxx |\n" + "|          |\n" + "------------\n")
        .expectComplete()
        .verify();

  }

  /**
   * Gets x.
   */
  @Test
  public void getX() {
    assertThat(fill.getX(), is(3));
  }

  /**
   * Gets y.
   */
  @Test
  public void getY() {
    assertThat(fill.getY(), is(3));
  }

  /**
   * Gets character.
   */
  @Test
  public void getCharacter() {
    assertThat(fill.getCharacter(), is('o'));
  }

}