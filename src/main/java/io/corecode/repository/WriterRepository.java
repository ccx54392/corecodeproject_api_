package io.corecode.repository;


import io.corecode.entity.Publisher;
import io.corecode.entity.Writer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WriterRepository extends JpaRepository<Writer, Integer> {
}
