package io.github.lugrion.label_memories_app.Repositories;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import io.github.lugrion.label_memories_app.entity.Memory;

public interface MemoryRepository extends JpaRepository<Memory, Long> {
    @Query("SELECT m FROM Memory m JOIN m.labels l " +
            "WHERE m.userData.id = :userDataId AND l.id IN :labelIds " +
            "GROUP BY m.id HAVING COUNT(DISTINCT l.id) = :labelCount")
    Set<Memory> findByUserAndAllLabels(@Param("userDataId") Long userDataId,
            @Param("labelIds") List<Long> labelIds,
            @Param("labelCount") Long labelCount);
}
