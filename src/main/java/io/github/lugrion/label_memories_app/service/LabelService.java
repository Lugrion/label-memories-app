package io.github.lugrion.label_memories_app.service;

import java.util.Set;

import io.github.lugrion.label_memories_app.common.response.GeneralResponse;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.lugrion.label_memories_app.common.DTOFactory;
import io.github.lugrion.label_memories_app.common.UserDataFetcher;
import io.github.lugrion.label_memories_app.common.request.LabelRequest;
import io.github.lugrion.label_memories_app.dto.LabelDTO;
import io.github.lugrion.label_memories_app.entity.Label;
import io.github.lugrion.label_memories_app.entity.UserData;
import io.github.lugrion.label_memories_app.repository.LabelRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LabelService {
    private final LabelRepository labelRepository;
    private final UserDataFetcher userDataFetcher;
    private final DTOFactory dtoFactory;

    public LabelDTO createLabel(LabelRequest payload) {
        UserData userData = userDataFetcher.getUserDataByAuth();

        Label label = new Label();
        label.setTitle(payload.title());
        label.setUserData(userData);
        labelRepository.save(label);

        return dtoFactory.getLabelDTO(label);
    }

    public Set<LabelDTO> getLabels() {
        UserData userData = userDataFetcher.getUserDataByAuth();
        return dtoFactory.getLabelDTOSet(userData.getLabels());
    }

    @Transactional
    public LabelDTO patchLabel(Long labelId, LabelRequest payload) {
        UserData userData = userDataFetcher.getUserDataByAuth();
        Label label = labelRepository.findByUserDataIdAndId(userData.getId(), labelId).orElseThrow(
                () -> new EntityNotFoundException("Label not found"));

        if (payload.title() != null) {
            label.setTitle(payload.title());
        }

        labelRepository.save(label);
        return dtoFactory.getLabelDTO(label);
    }

    @Transactional
    public GeneralResponse deleteLabel(Long labelId) {
        UserData userData = userDataFetcher.getUserDataByAuth();
        Label label = labelRepository.findByUserDataIdAndId(userData.getId(), labelId).orElseThrow(
                () -> new EntityNotFoundException("Label not found"));
        userData.getLabels().remove(label);
        labelRepository.deleteById(labelId);
        return new GeneralResponse("Label deleted properly");
    }
}
