package life.expert.riso;
//@Header@
//--------------------------------------------------------------------------------
//
//                          riso  life.expert.riso
//                           wilmer 2019/07/25
//
//--------------------------------------------------------------------------------

import life.expert.riso.app.OutputHelper;
import life.expert.riso.common.MergeableSimpleR2dbcRepository;
import org.jline.utils.AttributedString;
import org.jline.utils.AttributedStyle;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.shell.jline.PromptProvider;

/**
 * The type Test application configuration.
 */
@TestConfiguration
@ComponentScan( { "life.expert.riso.app" ,
                  "life.expert.riso.domain.repository" ,
                  "life.expert.riso.domain.model" ,
                  "life.expert.riso.domain.service" ,
                  "life.expert.riso.domain.model.impl.entity" } )
@EnableR2dbcRepositories( basePackages = "life.expert.riso.domain.repository", repositoryBaseClass = MergeableSimpleR2dbcRepository.class )
public class TestApplicationConfiguration
	{
	
	/**
	 * After each command invocation, the shell waits for new input from the user, displaying a prompt
	 * in yellow.
	 *
	 * @return the prompt provider
	 */
	@Bean
	public PromptProvider customPromptProvider()
		{
		return () -> new AttributedString( "enter command: " , AttributedStyle.DEFAULT.foreground( AttributedStyle.YELLOW ) );
		}
	
	/**
	 * Test Shell helper .
	 *
	 * @return the shell helper
	 */
	@Bean
	public OutputHelper shellHelper()
		{
		return new TestFunctionalHelper();
		}

	
	}
