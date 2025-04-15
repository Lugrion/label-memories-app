package io.github.lugrion.label_memories_app.Services;

import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.lugrion.label_memories_app.components.DTOFactory;
import io.github.lugrion.label_memories_app.components.UserDataFetcher;
import io.github.lugrion.label_memories_app.dto.Requests.CreateLabelRequest;
import io.github.lugrion.label_memories_app.dto.Responses.LabelDTO;
import io.github.lugrion.label_memories_app.entity.Label;
import io.github.lugrion.label_memories_app.entity.UserData;
import io.github.lugrion.label_memories_app.Exceptions.UserDataNotFoundException;
import io.github.lugrion.label_memories_app.Repositories.LabelRepository;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class LabelService {

    private final LabelRepository labelRepository;
    private final UserDataFetcher userDataFetcher;
    private final DTOFactory dtoFactory;

    public String createLabel(CreateLabelRequest payload) {
        UserData userData = userDataFetcher.getUserDataByAuth();
        
        Label label = new Label();
        label.setTitle(payload.getTitle());
        label.setUserData(userData);
        labelRepository.save(label);
        
        return "Label created";
    }

    public Set<LabelDTO> getLabels() {
        UserData userData = userDataFetcher.getUserDataByAuth();
        return dtoFactory.getLabelDTOSet(userData.getLabels());
    }


    // NOT WORKING PROPERLY
    public String deleteLabel(Long id) {
        Label label = labelRepository.findById(id).orElseThrow(() -> new UserDataNotFoundException("Label not found"));
        UserData userData = userDataFetcher.getUserDataByAuth();
        Boolean allowed = userData.getLabels().contains(label);

        if (!allowed) {
            return "Label not in property";
        }

        userData.getLabels().remove(label);
        labelRepository.deleteById(id);
        return "Label deleted properly";
    }
}
