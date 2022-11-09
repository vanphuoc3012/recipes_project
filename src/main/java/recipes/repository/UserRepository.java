package recipes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import recipes.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    User findByEmail(String email);

    Boolean existsByEmail(String email);

    User save(User user);
}
