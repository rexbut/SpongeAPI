package org.spongepowered.api.data.persistence;

import com.google.common.reflect.TypeToken;
import org.spongepowered.api.data.DataContainer;
import org.spongepowered.api.data.DataQuery;
import org.spongepowered.api.data.DataSerializable;
import org.spongepowered.api.data.DataView;
import org.spongepowered.api.data.MemoryDataView;

import java.util.Optional;

/**
 * A compatibility object to serialize and deserialize any type of
 * {@link Object} that is not a {@link DataSerializable}. Natively,
 * {@link MemoryDataView} will attempt to locate a {@code DataSerializer}
 * during {@link DataView#set(DataQuery, Object)}.
 *
 * @param <T> The type of object that this serializer and handle
 */
public interface DataSerializer<T> {

    TypeToken<T> getToken();

    /**
     * Attempts to deserialize the {@code T} object
     * @param view
     * @return
     * @throws InvalidDataException
     */
    Optional<T> deserialize(DataView view) throws InvalidDataException;

    DataContainer serialize(T obj) throws InvalidDataException;
}
