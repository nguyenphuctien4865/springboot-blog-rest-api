package com.nptien.blog.dtos;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostRespone {
    private List<PostDto> content;
    private int totalPages;
    private int pageSize;
    private int pageNo;
    private long totalElements;
    private boolean last;

}
