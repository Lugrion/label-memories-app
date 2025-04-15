package io.github.lugrion.label_memories_app.Services;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import io.github.lugrion.label_memories_app.components.DTOFactory;
import io.github.lugrion.label_memories_app.components.UserDataFetcher;
import io.github.lugrion.label_memories_app.dto.Responses.MemoryDTO;
import io.github.lugrion.label_memories_app.entity.UserData;
import io.github.lugrion.label_memories_app.Repositories.MemoryRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DashboardService {

    private final MemoryRepository memoryRepository;
    private final UserDataFetcher userDataFetcher;
    private final DTOFactory dtoFactory;

    public Set<MemoryDTO> filterMemoriesByLabels(final List<Long> labelIds) {

        UserData userData = userDataFetcher.getUserDataByAuth();
        return dtoFactory.getMemoryDTOSet(memoryRepository.findByUserAndAllLabels(
                userData.getId(),
                labelIds,
                Long.valueOf(labelIds.size())));
    }
}
