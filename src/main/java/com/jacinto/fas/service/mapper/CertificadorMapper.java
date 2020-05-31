package com.jacinto.fas.service.mapper;


import com.jacinto.fas.domain.*;
import com.jacinto.fas.service.dto.CertificadorDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Certificador} and its DTO {@link CertificadorDTO}.
 */
@Mapper(componentModel = "spring", uses = {EntidadCertificadoraMapper.class})
public interface CertificadorMapper extends EntityMapper<CertificadorDTO, Certificador> {

    @Mapping(source = "entidadCertificadora.id", target = "entidadCertificadoraId")
    CertificadorDTO toDto(Certificador certificador);

    @Mapping(source = "entidadCertificadoraId", target = "entidadCertificadora")
    Certificador toEntity(CertificadorDTO certificadorDTO);

    default Certificador fromId(Long id) {
        if (id == null) {
            return null;
        }
        Certificador certificador = new Certificador();
        certificador.setId(id);
        return certificador;
    }
}
