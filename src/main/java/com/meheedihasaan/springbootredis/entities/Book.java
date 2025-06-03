package com.meheedihasaan.springbootredis.entities;

import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder(toBuilder = true)
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "books")
public class Book extends Audit {

    private String title;

    private String author;

    private String isbn;
}
