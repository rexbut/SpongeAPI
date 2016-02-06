package org.spongepowered.api.data.persistence;

import com.flowpowered.math.imaginary.Complexd;
import com.flowpowered.math.imaginary.Complexf;
import com.flowpowered.math.imaginary.Quaterniond;
import com.flowpowered.math.imaginary.Quaternionf;
import com.flowpowered.math.vector.Vector2d;
import com.flowpowered.math.vector.Vector2f;
import com.flowpowered.math.vector.Vector2i;
import com.flowpowered.math.vector.Vector2l;
import com.flowpowered.math.vector.Vector3d;
import com.flowpowered.math.vector.Vector3f;
import com.flowpowered.math.vector.Vector3i;
import com.flowpowered.math.vector.Vector3l;
import com.flowpowered.math.vector.Vector4d;
import com.flowpowered.math.vector.Vector4f;
import com.flowpowered.math.vector.Vector4i;
import com.flowpowered.math.vector.Vector4l;
import com.google.common.reflect.TypeToken;
import org.spongepowered.api.data.DataContainer;
import org.spongepowered.api.data.DataQuery;
import org.spongepowered.api.data.DataView;
import org.spongepowered.api.data.MemoryDataContainer;
import org.spongepowered.api.data.Queries;

import java.util.Optional;
import java.util.UUID;

public final class DataSerializers {

    public static final DataSerializer<UUID> UUID_DATA_SERIALIZER;
    public static final DataSerializer<Vector2d> VECTOR_2_D_DATA_SERIALIZER;
    public static final DataSerializer<Vector2f> VECTOR_2_F_DATA_SERIALIZER;
    public static final DataSerializer<Vector2i> VECTOR_2_I_DATA_SERIALIZER;
    public static final DataSerializer<Vector2l> VECTOR_2_L_DATA_SERIALIZER;
    public static final DataSerializer<Vector3d> VECTOR_3_D_DATA_SERIALIZER;
    public static final DataSerializer<Vector3f> VECTOR_3_F_DATA_SERIALIZER;
    public static final DataSerializer<Vector3i> VECTOR_3_I_DATA_SERIALIZER;
    public static final DataSerializer<Vector3l> VECTOR_3_L_DATA_SERIALIZER;
    public static final DataSerializer<Vector4d> VECTOR_4_D_DATA_SERIALIZER;
    public static final DataSerializer<Vector4f> VECTOR_4_F_DATA_SERIALIZER;
    public static final DataSerializer<Vector4i> VECTOR_4_I_DATA_SERIALIZER;
    public static final DataSerializer<Vector4l> VECTOR_4_L_DATA_SERIALIZER;
    public static final DataSerializer<Complexd> COMPLEXD_DATA_SERIALIZER;
    public static final DataSerializer<Complexf> COMPLEXF_DATA_SERIALIZER;
    public static final DataSerializer<Quaterniond> QUATERNIOND_DATA_SERIALIZER;
    public static final DataSerializer<Quaternionf> QUATERNIONF_DATA_SERIALIZER;


    static {
        UUID_DATA_SERIALIZER = new DataSerializer<UUID> () {
            final TypeToken<UUID> token = TypeToken.of(UUID.class);
            @Override
            public TypeToken<UUID> getToken() {
                return this.token;
            }

            @Override
            public Optional<UUID> deserialize(DataView view) throws InvalidDataException {
                if (!view.contains(Queries.UUID_LEAST, Queries.UUID_MOST)) {
                    return Optional.empty();
                }
                final long least = view.getLong(Queries.UUID_LEAST).get();
                final long most = view.getLong(Queries.UUID_MOST).get();
                return Optional.of(new UUID(least, most));
            }

            @Override
            public DataContainer serialize(UUID obj) throws InvalidDataException {
                return new MemoryDataContainer()
                    .set(Queries.UUID_LEAST, obj.getLeastSignificantBits())
                    .set(Queries.UUID_MOST, obj.getMostSignificantBits());
            }
        };
    }

}
