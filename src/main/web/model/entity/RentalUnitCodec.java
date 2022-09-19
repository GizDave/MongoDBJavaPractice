package main.web.model.entity;

import org.bson.BsonReader;
import org.bson.BsonType;
import org.bson.BsonWriter;
import org.bson.codecs.Codec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;
import org.bson.codecs.configuration.CodecRegistry;

public class RentalUnitCodec implements Codec<RentalUnit> {
    private Codec<Integer> integerCodec;
    private Codec<Long> longCodec;
    private Codec<Double> doubleCodec;
    private Codec<String> stringCodec;

    public RentalUnitCodec(CodecRegistry registry) {
        this.integerCodec = registry.get(Integer.class);
        this.longCodec = registry.get(Long.class);
        this.doubleCodec = registry.get(Double.class);
        this.stringCodec = registry.get(String.class);
    }

    @Override
    public RentalUnit decode(BsonReader bsonReader, DecoderContext decoderContext) {
        RentalUnit unit = new RentalUnit();

        bsonReader.readStartDocument();

        while (bsonReader.readBsonType() != BsonType.END_OF_DOCUMENT) {
            String fieldName = bsonReader.readName();

            if (fieldName.equals("_id")) {
                unit.setId(bsonReader.readObjectId());
            } else if (fieldName.equals("name")) {
                unit.setName(stringCodec.decode(bsonReader, decoderContext));
            } else if (fieldName.equals("latitude")) {
                unit.setLatitude(doubleCodec.decode(bsonReader, decoderContext));
            } else if (fieldName.equals("longitude")) {
                unit.setLongitude(doubleCodec.decode(bsonReader, decoderContext));
            } else if (fieldName.equals("bathrooms")) {
                unit.setBathrooms(integerCodec.decode(bsonReader, decoderContext));
            } else if (fieldName.equals("bedrooms")) {
                unit.setBedrooms(integerCodec.decode(bsonReader, decoderContext));
            } else if (fieldName.equals("beds")) {
                unit.setBeds(integerCodec.decode(bsonReader, decoderContext));
            } else if (fieldName.equals("picture_url")) {
                unit.setPicture_url(stringCodec.decode(bsonReader, decoderContext));
            } else if (fieldName.equals("amenities")) {
                var amenities = stringCodec.decode(bsonReader, decoderContext);

                if (amenities.length() > 2) {
                    amenities = amenities.substring(1, amenities.length()-1);
                }

                unit.setAmenities(amenities.split(","));
            } else if (fieldName.equals("description")) {
                unit.setDescription(stringCodec.decode(bsonReader, decoderContext));
            }
            else {
                bsonReader.skipValue();
            }
        }

        bsonReader.readEndDocument();

        return unit;
    }

    @Override
    public void encode(BsonWriter bsonWriter, RentalUnit rentalUnit, EncoderContext encoderContext) {

    }

    @Override
    public Class<RentalUnit> getEncoderClass() {
        return RentalUnit.class;
    }
}
