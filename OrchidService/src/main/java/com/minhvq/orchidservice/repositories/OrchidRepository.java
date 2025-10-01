package com.minhvq.orchidservice.repositories;

import com.minhvq.orchidservice.entities.Orchid;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrchidRepository extends JpaRepository<Orchid, Integer> {
}
