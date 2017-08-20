package test.assignment.data.source.remote.beans;

public class DogSubBreedImageResponse extends Response {
    String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "DogSubBreedImageResponse{" +
                "message='" + message + '\'' +
                '}';
    }
}
