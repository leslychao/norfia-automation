package com.vkim.skyeng.service;

import com.vkim.skyeng.dto.BaseDto;
import com.vkim.skyeng.entity.BaseEntity;
import com.vkim.skyeng.exceptions.EntityNotFoundException;
import com.vkim.skyeng.mapper.BeanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static java.util.Objects.requireNonNull;
import static java.util.stream.Collectors.toList;

@Transactional(isolation = Isolation.READ_COMMITTED)
public abstract class AbstractCrudService<T extends BaseDto, E extends BaseEntity> {

    private static final Logger _logger = LoggerFactory.getLogger(AbstractCrudService.class);

    public T save(T dto) {
        _logger.info("save dto {}", dto);
        return Optional.of(dto)
                .map(this::mapToEntity)
                .map(this::entityPreSaveAction)
                .map(entity -> getRepository().save(entity))
                .map(this::mapToDto)
                .get();
    }

    public T update(T dto) {
        requireNonNull(dto);
        _logger.info("update dto {}", dto);
        return getRepository().findById(dto.getId())
                .map(entity -> updateEntityWithDto(entity, dto))
                .map(this::entityPreUpdateAction)
                .map(entity -> getRepository().save(entity))
                .map(this::mapToDto)
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("can't find entity by id: [%d]", dto.getId())));
    }

    public T get(Long id) {
        requireNonNull(id);
        _logger.info("get dto by id {}", id);
        E entity = getRepository().findById(id)
                .orElseThrow(
                        () -> new EntityNotFoundException(String.format("can't find entity by id: [%d]", id)));
        return mapToDto(entity);
    }

    public List<T> findAll() {
        return getRepository().findAll()
                .stream()
                .map(this::mapToDto)
                .collect(toList());
    }

    public Page<T> findAll(Pageable pageable) {
        return getRepository()
                .findAll(pageable)
                .map(this::mapToDto);
    }

    public void delete(Long id) {
        requireNonNull(id);
        _logger.info("delete dto by id {}", id);
        getRepository().deleteById(id);
    }

    public boolean exists(Long id) {
        requireNonNull(id);
        _logger.info("exists dto by id {}", id);
        return getRepository().existsById(id);
    }

    protected T mapToDto(E entity) {
        return getMapper().mapToDto(entity);
    }

    protected E updateEntityWithDto(E entity, T dto) {
        return entity;
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

    protected abstract JpaRepository<E, Long> getRepository();
}
