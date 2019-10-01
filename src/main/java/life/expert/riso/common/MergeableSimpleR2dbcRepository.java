package life.expert.riso.common;



import org.springframework.data.r2dbc.convert.R2dbcConverter;
import org.springframework.data.r2dbc.core.DatabaseClient;
import org.springframework.data.r2dbc.core.ReactiveDataAccessStrategy;
import org.springframework.data.r2dbc.repository.support.SimpleR2dbcRepository;
import org.springframework.data.relational.repository.query.RelationalEntityInformation;
import reactor.core.publisher.Mono;

/**
 * <pre> Now SAVE=MERGE. It's different from default SimpleR2dbcRepository, where save with nullable id means inserta, otherwise update.
 *
 * How to use: please add the class name to the annotation in the main class
 * {@code
 * @Slf4j
 * @SpringBootApplication
 * @EnableTransactionManagement
 * @ComponentScan( { "life.expert.....app" ,
 *                   "life.expert.....repository" } )
 * @EnableR2dbcRepositories( basePackages = "life.expert.....repository", repositoryBaseClass = MergeableSimpleR2dbcRepository.class )
 * public class Application
 * ...
 * }*
 *
 *
 * Preconditions: none
 * Postconditions: Add merge behaviour  for save method ReactiveCrudRepository
 * Side effects: none
 * Tread safety: Immutable
 * </pre>
 *
 * @param <T>
 * 	the type parameter
 * @param <ID>
 * 	the type parameter
 */
public class MergeableSimpleR2dbcRepository<T, ID>
	extends SimpleR2dbcRepository<T,ID>
	{
	
	private final RelationalEntityInformation<T,ID> entity;
	
	private final DatabaseClient databaseClient;
	
	private final R2dbcConverter converter;
	
	private final ReactiveDataAccessStrategy accessStrategy;
	
	/**
	 * Standart default constructor.
	 *
	 * @param entity
	 * 	the entity
	 * @param databaseClient
	 * 	the database client
	 * @param converter
	 * 	the converter
	 * @param accessStrategy
	 * 	the access strategy
	 */
	public MergeableSimpleR2dbcRepository( final RelationalEntityInformation<T,ID> entity ,
	                                       final DatabaseClient databaseClient ,
	                                       final R2dbcConverter converter ,
	                                       final ReactiveDataAccessStrategy accessStrategy )
		{
		super( entity , databaseClient , converter , accessStrategy );
		
		this.entity         = entity;
		this.databaseClient = databaseClient;
		this.converter      = converter;
		this.accessStrategy = accessStrategy;
		}
	
	@Override
	public <S extends T> Mono<S> save( S objectToSave )
		{
		if( this.entity.isNew( objectToSave ) )
			return super.save( objectToSave );
		else if( existsById( this.entity.getId( objectToSave ) ).block() )
			return this.databaseClient.update()
			                          .table( this.entity.getJavaType() )
			                          .table( this.entity.getTableName() )
			                          .using( objectToSave )
			                          .then()
			                          .thenReturn( objectToSave );
		else
			return this.databaseClient.insert()
			                          .into( this.entity.getJavaType() )
			                          .table( this.entity.getTableName() )
			                          .using( objectToSave )
			                          .then()
			                          .thenReturn( objectToSave );
		}
		
	}

