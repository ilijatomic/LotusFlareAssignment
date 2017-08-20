package test.assignment.data.source.remote.beans;

import java.util.List;
import java.util.Map;

public class DogBreedsResponse extends Response {
    Map<String, List<String>> message;

    public Map<String, List<String>> getMessage() {
        return message;
    }

    public void setMessage(Map<String, List<String>> message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "DogBreedsResponse{" +
                "status='" + status + '\'' +
                ", message=" + message +
                '}';
    }
}
