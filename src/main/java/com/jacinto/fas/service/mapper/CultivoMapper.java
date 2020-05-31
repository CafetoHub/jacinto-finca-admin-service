package com.jacinto.fas.service.mapper;


import com.jacinto.fas.domain.*;
import com.jacinto.fas.service.dto.CultivoDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Cultivo} and its DTO {@link CultivoDTO}.
 */
@Mapper(componentModel = "spring", uses = {FincaMapper.class})
public interface CultivoMapper extends EntityMapper<CultivoDTO, Cultivo> {

    @Mapping(source = "finca.id", target = "fincaId")
    CultivoDTO toDto(Cultivo cultivo);

    @Mapping(target = "agronomos", ignore = true)
    @Mapping(target = "removeAgronomos", ignore = true)
    @Mapping(target = "agregados", ignore = true)
    @Mapping(target = "removeAgregados", ignore = true)
    @Mapping(target = "lotes", ignore = true)
    @Mapping(target = "removeLotes", ignore = true)
    @Mapping(target = "certificadoras", ignore = true)
    @Mapping(target = "removeCertificadoras", ignore = true)
    @Mapping(source = "fincaId", target = "finca")
    Cultivo toEntity(CultivoDTO cultivoDTO);

    default Cultivo fromId(Long id) {
        if (id == null) {
            return null;
        }
        Cultivo cultivo = new Cultivo();
        cultivo.setId(id);
        return cultivo;
    }
}
