package com.itemsmanagement.model;

import com.itemsmanagement.DTO.ItemDTO;
import com.itemsmanagement.enums.ItemStatus;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)

public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @EqualsAndHashCode.Include
    private Integer itemId;

    @Column(length = 64, unique = true, nullable = false)
    private String itemName;

    @Column(length = 100, nullable = false)
    private String itemDescription;

    @Column(length = 16,unique = true, nullable = false)
    private String itemCode;

    @Column(nullable = true)
    private LocalDateTime itemDateCreation;

    @Column(nullable = true)
    private LocalDateTime itemDateModification;

    @Enumerated(EnumType.STRING)
    private ItemStatus itemStatus;
}
