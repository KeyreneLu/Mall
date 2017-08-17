package com.keyrene.domain;

import java.util.List;

/**
 *
 * Created by Administrator on 2017/5/15 0015.
 */
public class EasyUiDataForGrid<T> {
    private long total;
    private int page;
    private int currentPage;
    private int currentCount;
    private List<T> rows;

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getCurrentCount() {
        return currentCount;
    }

    public void setCurrentCount(int currentCount) {
        this.currentCount = currentCount;
    }

    @Override
    public String toString() {
        return "EasyUiDataForGrid{" +
                "total=" + total +
                ", page=" + page +
                ", currentPage=" + currentPage +
                ", currentCount=" + currentCount +
                ", rows=" + rows.size() +
                '}';
    }
}
