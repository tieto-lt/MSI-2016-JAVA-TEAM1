package lt.tieto.msi2016.mission.service;

import lt.tieto.msi2016.item.model.Item;
import lt.tieto.msi2016.item.repository.model.ItemDb;
import lt.tieto.msi2016.item.service.ItemService;
import lt.tieto.msi2016.mission.model.MissionResultInternal;
import lt.tieto.msi2016.utils.exception.DataNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MissionService {

    @Transactional(readOnly = true)
    public MissionResultInternal get(Long id) {
        //TODO: save to database
        return null;
    }

    @Transactional(readOnly = true)
    public List<MissionResultInternal> all() {
        //TODO: save to database
        return null;
    }
}
