package orderservice.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.util.Optional;

@Mapper
public interface OptionalMapper {

    String UNWRAP_OPTIONAL = "UNWRAP_OPTIONAL";

    @Named(UNWRAP_OPTIONAL)
    default <T> T unwrap(Optional<T> optional) {
        return optional.orElse(null);
    }

}
