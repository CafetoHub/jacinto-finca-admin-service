package com.jacinto.fas.service.mapper;


import com.jacinto.fas.domain.*;
import com.jacinto.fas.service.dto.AgronomoDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Agronomo} and its DTO {@link AgronomoDTO}.
 */
@Mapper(componentModel = "spring", uses = {CultivoMapper.class})
public interface AgronomoMapper extends EntityMapper<AgronomoDTO, Agronomo> {

    @Mapping(source = "cultivo.id", target = "cultivoId")
    AgronomoDTO toDto(Agronomo agronomo);

    @Mapping(source = "cultivoId", target = "cultivo")
    Agronomo toEntity(AgronomoDTO agronomoDTO);

    default Agronomo fromId(Long id) {
        if (id == null) {
            return null;
        }
        Agronomo agronomo = new Agronomo();
        agronomo.setId(id);
        return agronomo;
    }
}
