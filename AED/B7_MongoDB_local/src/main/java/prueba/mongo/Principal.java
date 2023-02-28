package prueba.mongo;

import org.bson.Document;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.ServerApi;
import com.mongodb.ServerApiVersion;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class Principal {
	public static void main(String[] args) {
		ConnectionString connectionString = new ConnectionString("mongodb://alumno:onmula@localhost/");
		MongoClientSettings settings = MongoClientSettings.builder().applyConnectionString(connectionString)
				.serverApi(ServerApi.builder().version(ServerApiVersion.V1).build()).build();
		MongoClient mongoClient = MongoClients.create(settings);

		for (Document base_datos : mongoClient.listDatabases()) {
			System.out.println("\nBASE DE DATOS: " + base_datos.getString("name"));
			MongoDatabase database = mongoClient.getDatabase(base_datos.getString("name"));
			for (Document coleccion : database.listCollections()) {
				System.out.println("\n  COLECCION    " + coleccion.getString("name"));
				// El log no se muestra porque es enorme
				if (coleccion.getString("name").equals("startup_log") == false)
					for (Document documento : database.getCollection(coleccion.getString("name")).find()) {
						System.out.printf(" ----> %s \n", documento);
						System.out.println();
						for (String key : documento.keySet()) {
							System.out.printf("      %s: %s\n", key, documento.get(key));
						}
					}
			}
		}
	}
}
