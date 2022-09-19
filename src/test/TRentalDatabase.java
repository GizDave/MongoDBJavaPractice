package test;

import com.mongodb.MongoClientSettings;
import com.mongodb.ServerAddress;
import com.mongodb.client.FindIterable;
import main.web.model.dao.RentalDatabase;
import main.web.model.entity.RentalUnit;
import main.web.model.entity.RentalUnitCodec;
import main.web.model.entity.RentalUnitCodecProvider;
import main.web.model.entity.SearchForm;
import org.bson.codecs.IntegerCodec;
import org.bson.codecs.configuration.CodecRegistries;

import java.net.ConnectException;

public class TRentalDatabase {
    public void test() {
        try {
            var connection = TDatabaseConnection();

            var invalidF1 = SearchForm.createForm(-1, 0, 0);
            var invalidF2 = SearchForm.createForm(0, -1, 0);
            var invalidF3 = SearchForm.createForm(0, 0, -1);
            var validF1 = SearchForm.createForm(0, 0, 0);

            TSearchRentalUnits(connection, invalidF1);
            TSearchRentalUnits(connection, invalidF2);
            TSearchRentalUnits(connection, invalidF3);

            var counter = 0;
            for (var unit : TSearchRentalUnits(connection, validF1)) {
                System.out.println(unit);
                counter++;
            }
            System.out.println(String.format("%d entries were found with the valid form...", counter));
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        var test = new TRentalDatabase();
        test.test();
    }

    private RentalDatabase TDatabaseConnection() throws ConnectException {
        var server = new ServerAddress("localhost", 27017);
        var databaseName = "Practice";
        var collectionName = "AustinAirBnB";
        var codecRegistry = CodecRegistries.fromRegistries(
                CodecRegistries.fromProviders(new RentalUnitCodecProvider()),
                MongoClientSettings.getDefaultCodecRegistry());
        return new RentalDatabase(server, databaseName, collectionName, codecRegistry);
    }

    private FindIterable<RentalUnit> TSearchRentalUnits(RentalDatabase conn, SearchForm request) {
        return conn.searchRentalUnit(request);
    }
}
