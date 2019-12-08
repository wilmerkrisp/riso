package life.expert.riso.app;

//---------------------------------------------
//      ___       __        _______   ______
//     /   \     |  |      /  _____| /  __  \
//    /  ^  \    |  |     |  |  __  |  |  |  |
//   /  /_\  \   |  |     |  | |_ | |  |  |  |
//  /  _____  \  |  `----.|  |__| | |  `--'  |
// /__/     \__\ |_______| \______|  \______/
//
//               wilmer 2019/08/28
//---------------------------------------------



import static life.expert.common.async.PrintUtils.print;

import io.r2dbc.spi.ConnectionFactory;
import life.expert.riso.domain.model.Canvas;
import life.expert.riso.domain.model.impl.entity.DefaultCanvas;
import life.expert.riso.domain.repository.CanvasRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.r2dbc.DataR2dbcTest;
import org.springframework.data.annotation.Id;
import org.springframework.data.r2dbc.core.DatabaseClient;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;


@RunWith(SpringRunner.class)
@DataR2dbcTest
public class CanvasRepositoryTest {

  @Autowired
  private CanvasRepository canvasRepository;

  @Autowired
  ConnectionFactory connectionFactory;

  @Data
  @AllArgsConstructor
  @Table
  public static class Reservation {

    @Id
    public Long id;

    public String name;
  }

  /**
   * Context loads.
   */
  @Test
  public void connectionFactoryTest() {
    print("tst_________________1__________________");

    var client = DatabaseClient.create(connectionFactory);
    client.execute("CREATE TABLE RESERVATION (id IDENTITY NOT NULL PRIMARY KEY, name VARCHAR(255))")
        .fetch()
        .all()
        //.doOnEach( x -> System.out.println( "++++++++++ " + x ) )
        .log()
        .as(StepVerifier::create)
        .verifyComplete();

    client.insert()
        .into(Reservation.class)
        .using(new Reservation(null, "NEW RESERVATION"))
        .then()
        .as(StepVerifier::create)
        .verifyComplete();

    client.select()
        .from(Reservation.class)
        .fetch()
        .all()
        //.doOnEach( x -> System.out.println( "++++++++++ " + x ) )
        .as(StepVerifier::create)
        .expectNext(new Reservation(1L, "NEW RESERVATION"))
        .verifyComplete();
  }

  @Test
  public void repositoryTest() {
    //@formatter:off
                var canvas_for_test=
		    "----------------------\n" +
	            "|                    |\n" +
	            "|                    |\n" +
	            "|                    |\n" +
	            "|                    |\n" +
	            "----------------------\n";
                //@formatter:on

    Canvas before_and_after = new DefaultCanvas("x", "x", 22, 6, 20, 4, canvas_for_test);

    Flux.just(before_and_after)
        .flatMap(r -> this.canvasRepository.save(r))
        .log()
        .as(StepVerifier::create)
        .expectNextCount(1)
        .verifyComplete();

    this.canvasRepository.findById("x")
        .doOnEach(x -> System.out.println("++ITOGO++++ " + x))
        .as(StepVerifier::create)
        .expectNext(before_and_after)
        .verifyComplete();


  }

}


