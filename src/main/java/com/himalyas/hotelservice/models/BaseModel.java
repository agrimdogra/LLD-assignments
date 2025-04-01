package com.himalyas.hotelservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@MappedSuperclass
@Builder
@AllArgsConstructor
@NoArgsConstructor
public abstract class BaseModel {
    @Id
    Long id;
    LocalDateTime createdAt;
    LocalDateTime modifiedAt;
}
