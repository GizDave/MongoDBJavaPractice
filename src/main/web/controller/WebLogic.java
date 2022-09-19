package main.web.controller;

import com.mongodb.MongoClientSettings;
import com.mongodb.ServerAddress;
import com.mongodb.client.FindIterable;
import main.web.model.dao.RentalDatabase;
import main.web.model.entity.RentalUnit;
import main.web.model.entity.RentalUnitCodecProvider;
import main.web.model.entity.SearchForm;
import org.bson.codecs.configuration.CodecRegistries;

import java.net.ConnectException;

public class WebLogic {
    private RentalDatabase db;

    public WebLogic() throws ConnectException {
        db = new RentalDatabase(
                new ServerAddress("localhost", 27017),
                "Practice",
                "AustinAirBnB",
                CodecRegistries.fromRegistries(
                CodecRegistries.fromProviders(new RentalUnitCodecProvider()),
                MongoClientSettings.getDefaultCodecRegistry())
        );
    }

    public long[] searchRentalUnit(long visitorId, int bedrooms, int beds, int bathrooms) {
        var start = System.currentTimeMillis();

        var userRequest = SearchForm.createForm(bedrooms, beds, bathrooms);
        var counter = 0;
        for (var unit : db.searchRentalUnit(userRequest)) counter++;

        var finish = System.currentTimeMillis();
        var timeElapsed = finish - start;

        return new long[] {visitorId, counter, timeElapsed};
    }
}
