package jackson.serialization;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import static com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion.NON_NULL;

@JsonSerialize(include = NON_NULL)
public class SomeClass {
    public String fieldPublic;
    private String fieldPrivate;

    public String getFieldPrivate() {
        return this.fieldPrivate;
    }
    public SomeClass setFieldPrivate(String fieldPrivate) {
        this.fieldPrivate = fieldPrivate;
        return this;
    }
}
