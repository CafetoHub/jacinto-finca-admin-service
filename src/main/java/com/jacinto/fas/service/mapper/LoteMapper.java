package com.jacinto.fas.service.mapper;


import com.jacinto.fas.domain.*;
import com.jacinto.fas.service.dto.LoteDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Lote} and its DTO {@link LoteDTO}.
 */
@Mapper(componentModel = "spring", uses = {CultivoMapper.class})
public interface LoteMapper extends EntityMapper<LoteDTO, Lote> {

    @Mapping(source = "cultivo.id", target = "cultivoId")
    LoteDTO toDto(Lote lote);

    @Mapping(source = "cultivoId", target = "cultivo")
    Lote toEntity(LoteDTO loteDTO);

    default Lote fromId(Long id) {
        if (id == null) {
            return null;
        }
        Lote lote = new Lote();
        lote.setId(id);
        return lote;
    }
}
