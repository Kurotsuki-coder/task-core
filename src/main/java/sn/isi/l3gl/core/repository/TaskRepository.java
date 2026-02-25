package sn.isi.l3gl.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sn.isi.l3gl.core.entity.Task;
import sn.isi.l3gl.core.entity.Status;

public interface TaskRepository extends JpaRepository<Task,Long> {

    long countByStatus(Status status);

}
