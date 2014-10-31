package mashape.shaochen.db;

import java.net.UnknownHostException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoException;
import com.mongodb.MongoURI;

/**
 * Util class for mongo db related operations.
 * <p>
 * 
 * @author shuang
 * 
 */
public class MongoDbUtils {

	private final static Logger log = LoggerFactory
			.getLogger(MongoDbUtils.class);

	private final static String staticMongoUrl = "mongodb://heroku:_PGOmXCJo-wOsS1TKwDk4oHe9cKwJ0BYCDexbHVQsaClXaRNqjXwanI5G05zkDXX4kWT4WbejOHH1v16NR0YEA@linus.mongohq.com:10055/app30540551";

	/**
	 * Best effort util method to try to get mongo DB connection in mongoHQ of
	 * heroku app.
	 * <p>
	 * 
	 * @return {@code DB} if found else return null
	 */
	public static DB getMongoDb() {
		DB db = null;

		try {
			db = getMongoDbFromSys();
		} catch (Exception e) {
			log.debug(
					"Exception getting mongo db from sys config, check your sys config. ",
					e);
		}

		if (db == null) {
			try {
				db = getMongoDbFromStatic();
			} catch (Exception e) {
				log.debug(
						"Exception getting mongo db from sys config, check your sys config. ",
						e);
			}
		}

		return db;
	}

	/**
	 * Get mongo db from system configuration.
	 * <p>
	 * 
	 * @return
	 * @throws Exception
	 */
	private static DB getMongoDbFromSys() throws Exception {
		MongoURI mongoUri = new MongoURI(System.getenv("MONGOHQ_URL"));
		return authDb(mongoUri);
	}

	/**
	 * Get mondb from static configuration. fall back strategy
	 * 
	 * @return
	 * @throws Exception
	 */
	private static DB getMongoDbFromStatic() throws Exception {
		MongoURI mongoUri = new MongoURI(staticMongoUrl);
		return authDb(mongoUri);
	}

	/**
	 * Authenticate db to ensure correct conenction, throws exception is auth is
	 * unsuccessful.
	 * 
	 * @param mongoUri
	 * @return
	 * @throws Exception
	 */
	private static DB authDb(MongoURI mongoUri) throws Exception {
		DB db = mongoUri.connectDB();
		boolean isAuth = db.authenticate(mongoUri.getUsername(),
				mongoUri.getPassword());
		if (isAuth)
			return db;
		else
			throw new MongoException("Unable to authorize user name: "
					+ mongoUri.getUsername() + ", and pass word: "
					+ new String(mongoUri.getPassword()));
	}

	private final static String collectionName = "todoitems";

	/**
	 * Tied to business logic, this method retrieves {@link DBCollection} for to
	 * do list items
	 * 
	 * @return
	 * @throws MongoException
	 * @throws UnknownHostException
	 */
	public static DBCollection getToDoItemsCollection() throws MongoException,
			UnknownHostException {
		return getMongoDb().getCollection(collectionName);
	}

}
