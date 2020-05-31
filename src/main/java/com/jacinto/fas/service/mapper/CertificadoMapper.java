package com.jacinto.fas.service.mapper;


import com.jacinto.fas.domain.*;
import com.jacinto.fas.service.dto.CertificadoDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Certificado} and its DTO {@link CertificadoDTO}.
 */
@Mapper(componentModel = "spring", uses = {AgregadoMapper.class})
public interface CertificadoMapper extends EntityMapper<CertificadoDTO, Certificado> {

    @Mapping(source = "agregado.id", target = "agregadoId")
    CertificadoDTO toDto(Certificado certificado);

    @Mapping(source = "agregadoId", target = "agregado")
    Certificado toEntity(CertificadoDTO certificadoDTO);

    default Certificado fromId(Long id) {
        if (id == null) {
            return null;
        }
        Certificado certificado = new Certificado();
        certificado.setId(id);
        return certificado;
    }
}
