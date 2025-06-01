package io.github.lugrion.label_memories_app.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import io.github.lugrion.label_memories_app.common.request.FilterRequest;
import io.github.lugrion.label_memories_app.common.response.GeneralResponse;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.lugrion.label_memories_app.common.DTOFactory;
import io.github.lugrion.label_memories_app.common.UserDataFetcher;
import io.github.lugrion.label_memories_app.common.request.MemoryRequest;
import io.github.lugrion.label_memories_app.dto.MemoryDTO;
import io.github.lugrion.label_memories_app.entity.Label;
import io.github.lugrion.label_memories_app.entity.Memory;
import io.github.lugrion.label_memories_app.entity.UserData;
import io.github.lugrion.label_memories_app.repository.LabelRepository;
import io.github.lugrion.label_memories_app.repository.MemoryRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemoryService {

    private final MemoryRepository memoryRepository;
    private final LabelRepository labelRepository;
    private final UserDataFetcher userDataFetcher;
    private final DTOFactory dtoFactory;

    public MemoryDTO createMemory(MemoryRequest payload) {
        UserData userData = userDataFetcher.getUserDataByAuth();

        Memory memory = new Memory();
        memory.setName(payload.name());
        memory.setUserData(userData);
        memory.setUrl(payload.url());

        if (!payload.labelIds().isEmpty()) {

            Set<Label> labels = labelRepository.findAllByUserDataIdAndIdIn(
                    userData.getId(),
                    payload.labelIds());

            if (labels.isEmpty()) {
                throw new EntityNotFoundException("Labels not found");
            }

            // Set labels on the memory (one side)
            memory.setLabels(labels);

            // Ensure the memory is added to each label's memories (other side)
            for (Label label : labels) {
                label.getMemories().add(memory); // Maintain bidirectional relationship
            }
        }


        memoryRepository.save(memory);

        return dtoFactory.getMemoryDTO(memory);
    }

    public Set<MemoryDTO> getMemories() {
        UserData userData = userDataFetcher.getUserDataByAuth();
        return dtoFactory.getMemoryDTOSet(userData.getMemories());
    }

    @Transactional
    public MemoryDTO patchMemory(Long memoryId, MemoryRequest payload) {
        UserData userData = userDataFetcher.getUserDataByAuth();

        Memory memory = memoryRepository.findByUserDataIdAndId(userData.getId(), memoryId).orElseThrow(
                () -> new EntityNotFoundException("Memory not found"));

        if (payload.name() != null) {
            memory.setName(payload.name());
        }

        if (payload.labelIds() != null) {
            for (Label label : new HashSet<>(memory.getLabels())) {
                label.getMemories().remove(memory);
            }

            memory.getLabels().clear();

            if (!payload.labelIds().isEmpty()) {
                Set<Label> newLabels = labelRepository.findAllByUserDataIdAndIdIn(userData.getId(), payload.labelIds());
                if (newLabels.isEmpty()) {
                    throw new EntityNotFoundException("Labels not found");
                }
                memory.setLabels(newLabels);

                for (Label label : newLabels) {
                    label.getMemories().add(memory);
                }
            }
        }

        memoryRepository.save(memory);
        return dtoFactory.getMemoryDTO(memory);
    }

    @Transactional
    public GeneralResponse deleteMemory(Long memoryId) {
        UserData userData = userDataFetcher.getUserDataByAuth();
        Memory memory = memoryRepository.findByUserDataIdAndId(userData.getId(), memoryId)
                .orElseThrow(() -> new EntityNotFoundException("Memory Not found"));

        // Remove memory from all associated labels first
        for (Label label : new ArrayList<>(memory.getLabels())) {
            label.getMemories().remove(memory);
        }

        memory.getLabels().clear();
        memoryRepository.deleteById(memoryId);
        return new GeneralResponse("Memory deleted properly");
    }

    public Set<MemoryDTO> filterMemoriesByLabels(final FilterRequest payload) {
        UserData userData = userDataFetcher.getUserDataByAuth();
        return dtoFactory.getMemoryDTOSet(memoryRepository.findByUserAndAllLabels(
                userData.getId(),
                payload.labelIds(),
                (long) payload.labelIds().size()));
    }
}
