
package com.example.session05.dto;

import java.util.List;

public class PageResponse<T> {
    private List<T> items;
    private int page;
    private int size;
    private int totalItems;
    private int totalPages;
    private boolean isLast;

    public PageResponse(List<T> items, int page, int size,
                        int totalItems, int totalPages, boolean isLast) {
        this.items = items;
        this.page = page;
        this.size = size;
        this.totalItems = totalItems;
        this.totalPages = totalPages;
        this.isLast = isLast;
    }

    public List<T> getItems() { return items; }
    public int getPage() { return page; }
    public int getSize() { return size; }
    public int getTotalItems() { return totalItems; }
    public int getTotalPages() { return totalPages; }
    public boolean isLast() { return isLast; }
}
