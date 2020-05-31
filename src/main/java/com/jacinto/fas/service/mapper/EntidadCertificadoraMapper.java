package com.jacinto.fas.service.mapper;


import com.jacinto.fas.domain.*;
import com.jacinto.fas.service.dto.EntidadCertificadoraDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link EntidadCertificadora} and its DTO {@link EntidadCertificadoraDTO}.
 */
@Mapper(componentModel = "spring", uses = {CultivoMapper.class})
public interface EntidadCertificadoraMapper extends EntityMapper<EntidadCertificadoraDTO, EntidadCertificadora> {

    @Mapping(source = "cultivo.id", target = "cultivoId")
    EntidadCertificadoraDTO toDto(EntidadCertificadora entidadCertificadora);

    @Mapping(target = "certificadores", ignore = true)
    @Mapping(target = "removeCertificadores", ignore = true)
    @Mapping(source = "cultivoId", target = "cultivo")
    EntidadCertificadora toEntity(EntidadCertificadoraDTO entidadCertificadoraDTO);

    default EntidadCertificadora fromId(Long id) {
        if (id == null) {
            return null;
        }
        EntidadCertificadora entidadCertificadora = new EntidadCertificadora();
        entidadCertificadora.setId(id);
        return entidadCertificadora;
    }
}
