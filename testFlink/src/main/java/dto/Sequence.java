package dto;

import lombok.Data;

@Data
public class Sequence {

    private String storeId;

    private Integer year;

    private Long value;

    public Long next() {
        return ++value;
    }
}
