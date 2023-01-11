package com.kuizu.exammicroservice.controller.Response;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class GenericResponse<T> {
    private Boolean success;
    private int code;
    private DataResponse data;

    public GenericResponse(Boolean success, HttpStatus code, String title, T data){
        this.success = success;
        this.code = code.value();
        this.data = new DataResponse(title, data);
    }

    public GenericResponse(Boolean success, HttpStatus code, String title){
        this.success = success;
        this.code = code.value();
        this.data = new DataResponse(title);
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public DataResponse getData() {
        return data;
    }

    public void setData(DataResponse data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public class DataResponse<T>{
        private String title;
        private T data;

        public DataResponse(String title, T data){
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

        public T getData() {
            return data;
        }

        public void setData(T data) {
            this.data = data;
        }
    }

}
