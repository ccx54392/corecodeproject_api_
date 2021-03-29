package io.corecode.repository;


import io.corecode.entity.Publisher;
import io.corecode.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublisherRepository extends JpaRepository<Publisher, Integer> {
}
