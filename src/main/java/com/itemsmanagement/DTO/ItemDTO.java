package com.itemsmanagement.DTO;

import com.itemsmanagement.enums.ItemStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)

public class ItemDTO {

    private Integer itemId;

    @NotNull
    @NotEmpty
    private String itemName;

    @Size (min = 3, max = 100)
    private String itemDescription;

    //@NotNull
    //@NotEmpty
    @Size (min = 1, max = 7)
    private String itemCode;

    //@NotEmpty
    private LocalDateTime itemDateCreation ;

   //@NotEmpty
    private LocalDateTime itemDateModification;

    //@NotNull
    private ItemStatus itemStatus;


}
