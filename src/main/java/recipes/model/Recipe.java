package recipes.model;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonFilter("recipeFilter")
@Entity
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    @NotNull
    private String name;

    @NotBlank
    private String description;

    @Builder.Default
    @ElementCollection
    @Size(min = 1)
    private List<String> ingredients = new ArrayList<>();

    @Builder.Default
    @ElementCollection
    @Size(min = 1)
    private List<String> directions = new ArrayList<>();

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
//    @JsonSerialize(using = LocalDateTimeSerializer.class)
//    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDateTime date;

    @NotBlank
    private String category;

    @ManyToOne
    @JoinColumn(name = "user_email")
    private User user;
}
