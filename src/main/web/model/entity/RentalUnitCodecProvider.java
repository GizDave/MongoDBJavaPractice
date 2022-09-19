package main.web.model.entity;

import org.bson.codecs.Codec;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;

public class RentalUnitCodecProvider implements CodecProvider {
    @Override
    @SuppressWarnings("unchecked")
    public <T> Codec<T> get(Class<T> aClass, CodecRegistry codecRegistry) {
        if (aClass == RentalUnit.class) {
            return (Codec<T>) new RentalUnitCodec(codecRegistry);
        }

        return null;
    }
}
