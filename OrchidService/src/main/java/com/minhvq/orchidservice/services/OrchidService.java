package com.minhvq.orchidservice.services;

import com.minhvq.orchidservice.entities.Orchid;

import java.util.List;
import java.util.Optional;

public interface OrchidService {
    List<Orchid> findAll();

    Orchid create(Orchid orchid);

    Orchid update(int id, Orchid orchid);

    void delete(int id);

    Optional<Orchid> findById(int id);
}
