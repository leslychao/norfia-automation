package com.vkim.skyeng.mapper;

public interface BeanMapper<T, E> {

  E mapToEntity(T dto);

  T mapToDto(E entity);

  E updateEntityWithDto(T dto, E entity);
}
