package ru.seppna.sportwebshop_rest.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.seppna.sportwebshop_rest.models.User;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

}
