package org.spongepowered.api.world.gen.structure;

import org.spongepowered.api.world.biome.BiomeType;

import java.util.List;

public interface Village extends Structure {

    int getSize();
    
    void setSize(int size);
    
    int getDistance();
    
    void setDistance(int dist);
    
    List<BiomeType> getValidBiomes();
    
}
