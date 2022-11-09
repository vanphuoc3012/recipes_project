package recipes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import recipes.model.Recipe;

import java.util.List;
import java.util.Optional;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Integer> {
    @Override
    <S extends Recipe> S save(S entity);

    @Override
    Optional<Recipe> findById(Integer integer);

    @Override
    boolean existsById(Integer integer);

    @Override
    void deleteById(Integer integer);

    List<Recipe> findAllByCategoryAllIgnoreCaseOrderByDateDesc(String category);

    List<Recipe> findByNameContainingIgnoreCaseOrderByDateDesc(String name);
}
