package com.minhvq.orchidservice.services;

import com.minhvq.orchidservice.entities.Orchid;
import com.minhvq.orchidservice.repositories.OrchidRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrchidServiceImpl implements OrchidService {
    private final OrchidRepository orchidRepository;

    @Override
    public List<Orchid> findAll() {
        return orchidRepository.findAll();
    }

    @Override
    @Transactional
    public Orchid create(Orchid orchid) {
        return orchidRepository.save(orchid);
    }

    @Override
    @Transactional
    public Orchid update(int id, Orchid orchid) {
        if (orchidRepository.existsById(id)) {
            orchid.setId(id);
            return orchidRepository.save(orchid);
        }
        return null;
    }

    @Override
    @Transactional
    public void delete(int id) {
        orchidRepository.deleteById(id);
    }

    @Override
    public Optional<Orchid> findById(int id) {
        return orchidRepository.findById(id);
    }
}
