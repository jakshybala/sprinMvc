package grey.crud.services;

import grey.crud.model.Course;
import grey.crud.model.Group;
import grey.crud.model.Teacher;
import grey.crud.repositories.GroupRepo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/*
grey.crud.services
Tarih: 16.06.2022, Saat: 22:03, Author: Grey 
*/
@Service
@Transactional(readOnly = true)
public class GroupService {

    private final GroupRepo groupRepo;

    public GroupService(GroupRepo groupRepo) {
        this.groupRepo = groupRepo;
    }


    public List<Group> showAll() {
        return groupRepo.findAll();
    }

    public Group getById(int id) {
        Optional<Group> allById = groupRepo.findById(id);
        return allById.orElse(null);
    }
    @Transactional
    public void createGroup(Group newGroup) {
        groupRepo.save(newGroup);
    }
    @Transactional
    public void updateGroup(int id, Group updateGroup) {
        updateGroup.setId(id);
        groupRepo.save(updateGroup);
    }
    @Transactional
    public void deletById(int id) {
        groupRepo.deleteById(id);
    }
//    public List<Group> findAllByCourseId(int courseId) {
//        return groupRepo.findAllByCourseyId(courseId);
//    }
}
