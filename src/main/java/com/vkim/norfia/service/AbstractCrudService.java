package com.vkim.norfia.service;

import static com.vkim.norfia.util.ErrorMessages.ENTITY_NOT_FOUND;
import static java.util.Objects.requireNonNull;

import com.vkim.norfia.dto.BaseDto;
import com.vkim.norfia.entity.LongIdEntity;
import com.vkim.norfia.mapper.BeanMapper;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public abstract class AbstractCrudService<T extends BaseDto, E extends LongIdEntity> {

  public T save(T dto) {
    return Optional.of(dto)
        .map(this::mapToEntity)
        .map(this::entityPreSaveAction)
        .map(getRepository()::save)
        .map(this::mapToDto)
        .get();
  }

  public List<T> saveAll(List<T> dtos) {
    requireNonNull(dtos);
    return dtos
        .stream()
        .map(this::save)
        .collect(Collectors.toList());
  }

  public T update(T dto) {
    requireNonNull(dto);
    return getRepository().findById(dto.getId())
        .map(entity -> updateEntityWithDto(entity, dto))
        .map(this::entityPreUpdateAction)
        .map(entity -> getRepository().save(entity))
        .map(this::mapToDto)
        .orElseThrow(() -> new EntityNotFoundException(ENTITY_NOT_FOUND.getMessage(dto.getId())));
  }

  public T get(Long id) {
    return getRepository().findById(id)
        .map(this::mapToDto)
        .orElseThrow(() -> new EntityNotFoundException(ENTITY_NOT_FOUND.getMessage(id)));
  }

  public Page<T> getAllPageable(Pageable pageable) {
    return getRepository().findAll(pageable).map(this::mapToDto);
  }

  public List<T> getAll() {
    return getRepository().findAll()
        .stream()
        .map(this::mapToDto)
        .collect(Collectors.toList());
  }

  protected E entityPreSaveAction(E entity) {
    return entity;
  }

  protected E entityPreUpdateAction(E entity) {
    return entity;
  }

  protected E mapToEntity(T dto) {
    return getMapper().mapToEntity(dto);
  }

  protected T mapToDto(E entity) {
    return getMapper().mapToDto(entity);
  }

  protected abstract BeanMapper<T, E> getMapper();

  protected abstract JpaRepository<E, Long> getRepository();

  protected abstract E updateEntityWithDto(E entity, T dto);

}
