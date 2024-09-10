package co.edu.escuelaing;

import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface MessageRepository extends MongoRepository<Message, String> {
    List<Message> findTop10ByOrderByDateDesc();
}



