package woowa.promotion.global.domain.page;

import java.util.List;

public record CustomPage<T>(

        List<T> data,
        Paging paging
) {

    public record Paging(

            int currentPage,
            int totalPages,
            long totalElements,
            int size
    ) {
    }
}
