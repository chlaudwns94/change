package com.korit.mcdonaldkiosk.dto.response;

import com.korit.mcdonaldkiosk.entity.Menu;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class RespMenuListDto {
    private int page;
    private int limitCount;
    private int totalPages;
    private int totalElements;
    private boolean isFirstPage;
    private boolean isLastPage;
    private int nextPage;
    private List<Menu> menuList;
}
