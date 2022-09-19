package main.web.model.dao;

import com.mongodb.ServerAddress;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Sorts;
import main.web.model.entity.RentalUnit;
import main.web.model.entity.SearchConstraints;
import main.web.model.entity.SearchForm;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;

import java.net.ConnectException;

public class RentalDatabase {
    private MongoCollection db;
    private final int pageSize = 10;
    private final SearchConstraints constraints;

    public RentalDatabase(ServerAddress mongoServer, String databaseName, String collectionName, CodecRegistry codecRegistry) throws ConnectException{
        try {
            this.db = MongoClients.create("mongodb://" + mongoServer.toString())
                    .getDatabase(databaseName)
                    .getCollection(collectionName)
                    .withCodecRegistry(codecRegistry);

            var agg = db.find();
            agg.batchSize(1);

            constraints = new SearchConstraints(
                    ((Document)agg.sort(Sorts.ascending("bedrooms")).iterator().next()).getInteger("bedrooms"),
                    ((Document)agg.sort(Sorts.descending("bedrooms")).iterator().next()).getInteger("bedrooms"),
                    ((Document)agg.sort(Sorts.ascending("beds")).iterator().next()).getInteger("beds"),
                    ((Document)agg.sort(Sorts.descending("beds")).iterator().next()).getInteger("beds"),
                    ((Document)agg.sort(Sorts.ascending("bathrooms")).iterator().next()).getInteger("bathrooms"),
                    ((Document)agg.sort(Sorts.descending("bathrooms")).iterator().next()).getInteger("bathrooms")
            );

            System.out.println(String.format("Connected to %s at port %d", mongoServer.getHost(), mongoServer.getPort()));
        }
        catch (Exception e) {
            System.out.println("Database connection failed.");
            e.printStackTrace();
            throw new ConnectException();
        }
    }

    public FindIterable<RentalUnit> searchRentalUnit(SearchForm userRequest) {
        if (!constraints.isValidInput(userRequest)) {
            return null;
        }
        else {
            return search(userRequest);
        }
    }

    protected SearchConstraints getSearchConstraints() {
        return this.constraints;
    }

    private FindIterable<RentalUnit> search(SearchForm userRequest) {
        var query = Filters.and(
                Filters.gte("bedrooms", userRequest.getBedrooms()),
                Filters.gte("beds", userRequest.getBeds()),
                Filters.gte("bathrooms", userRequest.getBathrooms())
        );

        var res = db.find(query, RentalUnit.class);
        res.batchSize(pageSize);
        return res;
    }
}
