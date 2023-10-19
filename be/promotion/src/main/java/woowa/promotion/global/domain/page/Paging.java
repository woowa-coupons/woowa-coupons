package woowa.promotion.global.domain.page;

public record Paging(

        int currentPage,
        int totalPages,
        long totalElements,
        int size
) {
}
