package com.teste_tecnico.ekan.mappers;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

import com.teste_tecnico.ekan.dto.PatientUpdateDTO;
import com.teste_tecnico.ekan.entity.Patient;

public interface GenericMapper<SourceDTO, TargetEntity> {

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(SourceDTO sourceDto, @MappingTarget TargetEntity targetEntity);
}

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
interface PatientMapper extends GenericMapper<PatientUpdateDTO, Patient> {
}
