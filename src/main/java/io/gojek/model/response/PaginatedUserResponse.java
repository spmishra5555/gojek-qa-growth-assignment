package io.gojek.model.response;

import java.util.List;
import java.util.Objects;

public class PaginatedUserResponse implements Comparable<PaginatedUserResponse> {

    private List<Data> data;

    public List<Data> getData() {
        return data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaginatedUserResponse that = (PaginatedUserResponse) o;
        return Objects.equals(data, that.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(data);
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "PaginatedUserResponse{" +
                "data=" + data +
                '}';
    }

    @Override
    public int compareTo(PaginatedUserResponse o) {
        if (o == null) {
            return 1;
        }
        if (this.data.size() != o.getData().size()) {
            return 1;
        }
        List<Data> incomingData = o.getData();
        for (int i = 0; i < data.size(); i++) {
            int compare = data.get(i).compareTo(incomingData.get(i));
            if (compare != 0) {
                return 1;
            }
        }
        return 0;
    }
}
