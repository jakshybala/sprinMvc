package grey.crud.services;

import grey.crud.model.Teacher;
import grey.crud.repositories.TeacherRepo;
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
public class TeacherService {

    private final TeacherRepo teacherRepo;

    public TeacherService(TeacherRepo teacherRepo) {
        this.teacherRepo = teacherRepo;
    }

    public List<Teacher> showAll() {
        return teacherRepo.findAll();
    }

    public Teacher getById(int id) {
        Optional<Teacher> allById = teacherRepo.findById(id);
        return allById.orElse(null);
    }
    @Transactional
    public void creteTeacher(Teacher newTeacher) {
        teacherRepo.save(newTeacher);
    }
    @Transactional
    public void updateTeacher(int id, Teacher teacher) {
        teacher.setId(id);
        teacherRepo.save(teacher);
    }
    @Transactional
    public void deletById(int id) {
        teacherRepo.deleteById(id);
    }
}
