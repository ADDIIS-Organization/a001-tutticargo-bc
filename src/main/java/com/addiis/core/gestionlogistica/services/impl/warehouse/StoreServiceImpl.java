package com.addiis.core.gestionlogistica.services.impl.warehouse;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.addiis.core.gestionlogistica.domain.dto.request.StoreRequest;
import com.addiis.core.gestionlogistica.domain.dto.response.CustomStoreResponse;
import com.addiis.core.gestionlogistica.domain.dto.response.StoreResponse;
import com.addiis.core.gestionlogistica.mappers.StoreMapper;
import com.addiis.core.gestionlogistica.persistence.entities.warehouse.Store;
import com.addiis.core.gestionlogistica.persistence.repositories.warehouse.StoreRepository;
import com.addiis.core.gestionlogistica.services.warehouse.StoreService;
import com.addiis.core.gestionlogistica.utils.exceptions.IdNotFoundException;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class StoreServiceImpl implements StoreService {
  @Autowired
  private final StoreMapper storeMapper;

  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Autowired
  private final StoreRepository storeRepository;

  public Page<CustomStoreResponse> findAllCustom(int page, int size) {
    Pageable pageable = PageRequest.of(page, size);

    String query = "SELECT s.id, s.name, s.code, s.address, s.city, s.observation, s.priority, s.ruc, " +
        "r.id AS route_id, r.route_number AS route_name, " +
        "c.id AS channel_id, c.number AS channel_name, " +
        "p.id AS platform_id, p.number AS platform_name " +
        "FROM stores s " +
        "LEFT JOIN routes r ON s.route_id = r.id " +
        "LEFT JOIN channels c ON s.channel_id = c.id " +
        "LEFT JOIN platforms p ON c.platform_id = p.id " +
        "LIMIT ? OFFSET ?";

    int offset = page * size;
    List<CustomStoreResponse> stores = jdbcTemplate.query(query, (rs, rowNum) -> new CustomStoreResponse(
        rs.getLong("id"),
        rs.getString("name"),
        rs.getInt("code"),
        rs.getString("address"),
        rs.getString("city"),
        rs.getString("observation"),
        rs.getString("priority"),
        rs.getString("ruc"),
        rs.getLong("route_id"),
        rs.getString("route_name"),
        rs.getLong("channel_id"),
        rs.getString("channel_name"),
        rs.getLong("platform_id"),
        rs.getString("platform_name")
        ), size, offset);

    long total = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM stores", Long.class);

    return new PageImpl<CustomStoreResponse>(stores, pageable, total);
  }

  @Override
  public StoreResponse create(StoreRequest request) {
    Store entity = storeMapper.toEntity(request);
    Store storeCreated = storeRepository.save(entity);
    return storeMapper.toResponse(storeCreated);
  }

  @Override
  public void delete(Long id) {
    Store entity = find(id);
    storeRepository.delete(entity);

  }

  @Override
  public Page<StoreResponse> findAll(int page, int size) {
    if (page < 0) {
      page = 0;

    }
    Pageable pageable = PageRequest.of(page, size);
    return this.storeRepository.findAll(pageable).map(storeMapper::toResponse);
  }

  @Override
  public StoreResponse findById(Long id) {
    Store entity = find(id);
    return storeMapper.toResponse(entity);

  }

  @Override
  public StoreResponse update(StoreRequest request, Long id) {
    Store entity = this.find(id);
    Store updateEntity = this.storeMapper.toEntity(request);
    updateEntity.setId(entity.getId());
    return this.storeMapper.toResponse(this.storeRepository.save(updateEntity));

  }

  @Override
  public StoreResponse patch(StoreRequest request, Long id) {
    Store entity = find(id);
    entity.setStatus(0);
    return this.storeMapper.toResponse(this.storeRepository.save(entity));
  }

  private Store find(Long id) {
    Store entity = storeRepository.findById(id).orElseThrow(() -> new IdNotFoundException("Store", id));
    return entity;

  }

}
