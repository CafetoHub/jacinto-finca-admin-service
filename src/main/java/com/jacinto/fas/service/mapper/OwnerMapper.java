package com.jacinto.fas.service.mapper;


import com.jacinto.fas.domain.*;
import com.jacinto.fas.service.dto.OwnerDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Owner} and its DTO {@link OwnerDTO}.
 */
@Mapper(componentModel = "spring", uses = {FincaMapper.class})
public interface OwnerMapper extends EntityMapper<OwnerDTO, Owner> {

    @Mapping(source = "finca.id", target = "fincaId")
    OwnerDTO toDto(Owner owner);

    @Mapping(source = "fincaId", target = "finca")
    Owner toEntity(OwnerDTO ownerDTO);

    default Owner fromId(Long id) {
        if (id == null) {
            return null;
        }
        Owner owner = new Owner();
        owner.setId(id);
        return owner;
    }
}
