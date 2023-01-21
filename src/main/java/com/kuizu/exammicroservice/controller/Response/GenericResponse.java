package com.kuizu.exammicroservice.controller.Response;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class GenericResponse<T> {
    private Boolean success;
    private int code;
    private DataResponse<T> data;

    public GenericResponse(Boolean success, HttpStatus code, String title, T data){
        this.success = success;
        this.code = code.value();
        this.data = new DataResponse<>(title, data);
    }

    public GenericResponse(Boolean success, HttpStatus code, String title){
        this.success = success;
        this.code = code.value();
        this.data = new DataResponse<>(title);
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public DataResponse<T> getData() {
        return data;
    }

    public void setData(DataResponse<T> data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public class DataResponse<S>{
        private String title;
        private S data;

        public DataResponse(String title, S data){
            this.title = title;
            this.data = data;
        }
        public DataResponse(String title){
            this.title = title;
            this.data = null;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public S getData() {
            return data;
        }

        public void setData(S data) {
            this.data = data;
        }
    }

}
