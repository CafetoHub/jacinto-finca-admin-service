package com.jacinto.fas.service.mapper;


import com.jacinto.fas.domain.*;
import com.jacinto.fas.service.dto.AgregadoDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Agregado} and its DTO {@link AgregadoDTO}.
 */
@Mapper(componentModel = "spring", uses = {CultivoMapper.class})
public interface AgregadoMapper extends EntityMapper<AgregadoDTO, Agregado> {

    @Mapping(source = "cultivo.id", target = "cultivoId")
    AgregadoDTO toDto(Agregado agregado);

    @Mapping(target = "certificados", ignore = true)
    @Mapping(target = "removeCertificados", ignore = true)
    @Mapping(source = "cultivoId", target = "cultivo")
    Agregado toEntity(AgregadoDTO agregadoDTO);

    default Agregado fromId(Long id) {
        if (id == null) {
            return null;
        }
        Agregado agregado = new Agregado();
        agregado.setId(id);
        return agregado;
    }
}
