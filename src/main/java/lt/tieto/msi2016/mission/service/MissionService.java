/*
package lt.tieto.msi2016.mission.service;

import lt.tieto.msi2016.item.model.Item;
import lt.tieto.msi2016.item.repository.ItemRepository;
import lt.tieto.msi2016.mission.repository.Mission;
import lt.tieto.msi2016.operator.repository.OperatorVerification;
import lt.tieto.msi2016.operator.repository.model.OperatorDb;
import lt.tieto.msi2016.operator.model.OperatorModel;
import lt.tieto.msi2016.utils.exception.DataNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

*/
/**
 * Created by it11 on 16.8.9.
 *//*


@Service
public class MissionService{

    @Autowired
    private OperatorVerification repository;






    public OperatorModel createOrUpdateOperator(Long id, OperatorModel item) {
        /*/
/************
        if (repository.exists(id)) {
            OperatorDb updated = repository.update(mapToItemDb(id, item));
            return mapToItem(updated);
        } else {
            */
/*ItemDb created = repository.create(mapToItemDb(id, item));
            return mapToItem(created);*//*

        }
    }



    private static Item mapToItem(OperatorDb db) {
        Item api = new Item();
        api.setId(db.getId());
//        api.setName(db.getName());
//        api.setQuantity(db.getQuantity());
//        api.setSize(db.getSize());
        return api;
    }

    private static OperatorDb mapToItemDb(Long id, Item api) {
        OperatorDb db = new OperatorDb();
        db.setId(id);
       */
/* db.setName(api.getName());
        db.setQuantity(api.getQuantity());
        db.setSize(api.getSize());*//*

        return db;
    }

    private static OperatorDb mapToItemDb(Item api) {
        return mapToItemDb(api.getId(), api);
    }
}
*/
