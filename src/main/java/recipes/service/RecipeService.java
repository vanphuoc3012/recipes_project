package recipes.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import recipes.model.Recipe;
import recipes.repository.RecipeRepository;

import java.util.List;
import java.util.Optional;

@Service
public class RecipeService {
    private final RecipeRepository repository;

    @Autowired
    public RecipeService(RecipeRepository repository) {
        this.repository = repository;
    }

    public Recipe save(Recipe recipe) {
        System.out.println("~~~~Saving : "+recipe.getName()+"~~~~");
        return repository.save(recipe);
    }

    public Recipe findById(Integer id) {
        Optional<Recipe> recipe = repository.findById(id);
        if(recipe.isEmpty()) {
            System.out.println("~~~~Not found from RecipeService~~~~");
            return null;
        } else {
            return repository.findById(id).get();
        }

    }

    public boolean checkExistsById(Integer id) {
        return repository.existsById(id);
    }

    public void deleteById(Integer id) {
        repository.deleteById(id);
    }

    public List<Recipe> findAllByCategoryAllIgnoreCaseOrderByDateDesc(String category) {
        return repository.findAllByCategoryAllIgnoreCaseOrderByDateDesc(category);
    }

    public List<Recipe> findByNameContainingIgnoreCaseOrderByDateDesc(String name) {
        return repository.findByNameContainingIgnoreCaseOrderByDateDesc(name);
    }
}
