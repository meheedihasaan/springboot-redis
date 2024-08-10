package com.meheedihasaan.springbootredis.models;

import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class User implements Serializable {

    private UUID id;

    private String name;

    private String email;

    private String phone;
}
