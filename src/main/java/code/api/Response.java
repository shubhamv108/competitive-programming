package code.api;

import java.util.List;

public class Response {
    private int page;
    private int per_page;
    private int total;
    private int total_pages;
    private List<Data> data;

    public int getTotal_pages() {
        return total_pages;
    }

    public List<Data> getData() {
        return data;
    }

    @Override
    public String toString() {
        return "Response{" +
                "page=" + page +
                ", per_page=" + per_page +
                ", total=" + total +
                ", total_pages=" + total_pages +
                ", data=" + data +
                '}';
    }
}