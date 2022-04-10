package ru.inside.jwttask.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.inside.jwttask.model.Message;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

    @Query(value = "SELECT * FROM messages WHERE user_id = ?1 ORDER BY id DESC LIMIT ?2", nativeQuery = true)
    List<Message> getLastMessagesByUserId(long user_id, int number);
}
