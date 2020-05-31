package com.jacinto.fas.service.mapper;


import com.jacinto.fas.domain.*;
import com.jacinto.fas.service.dto.FincaDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Finca} and its DTO {@link FincaDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface FincaMapper extends EntityMapper<FincaDTO, Finca> {


    @Mapping(target = "cultivos", ignore = true)
    @Mapping(target = "removeCultivos", ignore = true)
    @Mapping(target = "owners", ignore = true)
    @Mapping(target = "removeOwners", ignore = true)
    Finca toEntity(FincaDTO fincaDTO);

    default Finca fromId(Long id) {
        if (id == null) {
            return null;
        }
        Finca finca = new Finca();
        finca.setId(id);
        return finca;
    }
}
