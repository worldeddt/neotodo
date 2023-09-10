package spring.neotodobackend.infra;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.neotodobackend.domain.entity.Todo;

import java.util.List;
import java.util.Optional;

public interface TodoRepository extends JpaRepository<Todo, Integer> {
    Optional<List<Todo>> findAllByStatus(String activeOnly);

    Optional<Todo> findByIndex(Integer index);
}
