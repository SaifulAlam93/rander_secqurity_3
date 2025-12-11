package com.abc.SpringBootSecqurityEx.dtos;

// Product DTO
import java.time.LocalDateTime;

public record Product(
        Long id,
        String name,
        String category,
        Double price,

        String description,
        Integer stock,
        Boolean active,
        Double rating,
        String imageUrl,
        LocalDateTime createdAt
) {}
