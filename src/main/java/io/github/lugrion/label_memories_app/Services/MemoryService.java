package io.github.lugrion.label_memories_app.Services;

import java.util.ArrayList;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.lugrion.label_memories_app.components.DTOFactory;
import io.github.lugrion.label_memories_app.components.UserDataFetcher;
import io.github.lugrion.label_memories_app.dto.Requests.CreateMemoryRequest;
import io.github.lugrion.label_memories_app.dto.Responses.MemoryDTO;
import io.github.lugrion.label_memories_app.entity.Label;
import io.github.lugrion.label_memories_app.entity.Memory;
import io.github.lugrion.label_memories_app.entity.UserData;
import io.github.lugrion.label_memories_app.Exceptions.UserDataNotFoundException;
import io.github.lugrion.label_memories_app.Repositories.LabelRepository;
import io.github.lugrion.label_memories_app.Repositories.MemoryRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class MemoryService {

    private final MemoryRepository memoryRepository;
    private final LabelRepository labelRepository;
    private final UserDataFetcher userDataFetcher;
    private final DTOFactory dtoFactory;

    public String createMemory(CreateMemoryRequest payload) {
        UserData userData = userDataFetcher.getUserDataByAuth();

        Memory memory = new Memory();
        memory.setName(payload.getName());
        memory.setUserData(userData);

        if (!payload.getLabelIds().isEmpty()) {
            Set<Label> labels = labelRepository.findAllByUserDataIdAndIdIn(
                    userData.getId(),
                    payload.getLabelIds());

            // Set labels on the memory (one side)
            memory.setLabels(labels);

            // Ensure the memory is added to each label's memories (other side)
            for (Label label : labels) {
                label.getMemories().add(memory); // Maintain bidirectional relationship
            }

        }
        memoryRepository.save(memory);

        return "Memory created!";
    }

    public Set<MemoryDTO> getMemories() {
        UserData userData = userDataFetcher.getUserDataByAuth();
        return dtoFactory.getMemoryDTOSet(userData.getMemories());
    }

    // NOT WORKING PROPERLY PROBABLY ERROR WHEN CREATING
    public String deleteMemory(Long id) {
        Memory memory = memoryRepository.findById(id)
                .orElseThrow(() -> new UserDataNotFoundException("Memory Not found"));
        UserData userData = userDataFetcher.getUserDataByAuth();
        Boolean allowed = userData.getMemories().contains(memory);

        if (!allowed) {
            return "Memory not in property";
        }

        // Remove memory from all associated labels first
        for (Label label : new ArrayList<>(memory.getLabels())) {
            label.getMemories().remove(memory);
        }

        memory.getLabels().clear();
        memoryRepository.deleteById(id);
        return "Memory deleted properly";
    }
}
