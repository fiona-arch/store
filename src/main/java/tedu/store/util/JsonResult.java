package tedu.store.util;

public class JsonResult<T>{
    private Integer state;
    private String message;
    private T data;

    public JsonResult() {
    }

    public JsonResult(Integer state, T data) {
        this.state = state;
        this.data = data;
    }

    public JsonResult(Integer state) {
        this.state = state;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}

