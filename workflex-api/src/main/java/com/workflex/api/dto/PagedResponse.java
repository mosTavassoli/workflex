package com.workflex.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PagedResponse<T> {

    private List<T> data;
    private PageMeta page;

    public static <T> PagedResponse<T> from(
            Page<?> springPage,
            List<T> data
    ) {
        return PagedResponse.<T>builder()
                .data(data)
                .page(
                        PageMeta.builder()
                                .number(springPage.getNumber())
                                .size(springPage.getSize())
                                .totalElements(springPage.getTotalElements())
                                .totalPages(springPage.getTotalPages())
                                .build()
                )
                .build();
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class PageMeta {
        private int number;
        private int size;
        private long totalElements;
        private int totalPages;
    }
}


