package io.github.lugrion.label_memories_app.service;

import java.util.Set;

import io.github.lugrion.label_memories_app.common.request.FilterRequest;
import org.springframework.stereotype.Service;

import io.github.lugrion.label_memories_app.common.DTOFactory;
import io.github.lugrion.label_memories_app.common.UserDataFetcher;
import io.github.lugrion.label_memories_app.dto.MemoryDTO;
import io.github.lugrion.label_memories_app.entity.UserData;
import io.github.lugrion.label_memories_app.repository.MemoryRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DashboardService {

    private final MemoryRepository memoryRepository;
    private final UserDataFetcher userDataFetcher;
    private final DTOFactory dtoFactory;

    public Set<MemoryDTO> filterMemoriesByLabels(final FilterRequest payload) {
        UserData userData = userDataFetcher.getUserDataByAuth();
        return dtoFactory.getMemoryDTOSet(memoryRepository.findByUserAndAllLabels(
                userData.getId(),
                payload.labelIds(),
                (long) payload.labelIds().size()));
    }
}
