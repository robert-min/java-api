package com.example.simpleboard.common;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Pagination {
    private Integer page;

    private Integer size;

    private Integer currentElements;

    private Integer totalPage;

    private Long totalElements;
}
