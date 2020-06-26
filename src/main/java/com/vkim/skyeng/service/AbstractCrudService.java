package com.vkim.skyeng.service;

import static java.util.Objects.requireNonNull;
import static java.util.stream.Collectors.toList;

import com.vkim.skyeng.dto.BaseDto;
import com.vkim.skyeng.entity.BaseEntity;
import com.vkim.skyeng.mapper.BeanMapper;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.StreamSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public abstract class AbstractCrudService<T extends BaseDto, E extends BaseEntity> {

  private static final Logger logger = LoggerFactory.getLogger(AbstractCrudService.class);

  @Transactional
  public T save(T dto) {
    logger.info("save dto {}", dto);
    requireNonNull(dto);
    E entity = mapToEntity(dto);
    entity = entityPreSaveAction(entity);
    E savedEntity = getRepository().save(entity);
    return mapToDto(savedEntity);
  }

  @Transactional
  public T update(T dto) {
    logger.info("update dto {}", dto);
    requireNonNull(dto);
    if (dto.getId() == null) {
      return this.save(dto);
    }
    Optional<E> optional = getRepository().findById(dto.getId());
    if (!optional.isPresent()) {
      return save(dto);
    }
    E entity = optional.get();
    entity = updateEntityWithDto(entity, dto);
    entity = entityPreUpdateAction(entity);
    E updatedEntity = getRepository().save(entity);
    return mapToDto(updatedEntity);
  }

  @Transactional(readOnly = true)
  public T get(Long id) {
    logger.info("get dto by id {}", id);
    requireNonNull(id);
    E entity = getRepository().findById(id).orElseGet(() -> {
      logger.info("entity not found by id {} ", id);
      return null;
    });
    return mapToDto(entity);
  }

  @Transactional(readOnly = true)
  public Collection<T> findAll() {
    return StreamSupport
        .stream(getRepository().findAll().spliterator(), false)
        .map(this::mapToDto)
        .collect(toList());
  }

  @Transactional
  public void delete(Long id) {
    getRepository().deleteById(id);
  }

  protected T mapToDto(E entity) {
    return getMapper().mapToDto(entity);
  }

  protected E updateEntityWithDto(E entity, T dto) {
    return this.getMapper().updateEntityWithDto(dto, entity);
  }

  protected E entityPreSaveAction(E entity) {
    entity.setLastUpdated(LocalDateTime.now());
    return entity;
  }

  protected E entityPreUpdateAction(E entity) {
    entity.setLastUpdated(LocalDateTime.now());
    return entity;
  }

  protected E mapToEntity(T dto) {
    return getMapper().mapToEntity(dto);
  }

  protected abstract BeanMapper<T, E> getMapper();

  protected abstract CrudRepository<E, Long> getRepository();
}
