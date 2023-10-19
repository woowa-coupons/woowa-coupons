package woowa.promotion.global.domain.page;

import java.util.List;

public record CustomPage<T>(

        List<T> data,
        Paging paging
) {
}
