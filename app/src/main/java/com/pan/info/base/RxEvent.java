package com.pan.info.base;

/**
 * Author : Pan
 * Date : 19/03/2017
 */

public class RxEvent {
    private String type;
    private boolean isSuccess;

    private RxEvent(String type, boolean isSuccess) {
        this.type = type;
        this.isSuccess = isSuccess;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public static class RxEventBuilder {
        private String type;
        private boolean isSuccess;

        public RxEventBuilder(String type, boolean isSuccess) {
            this.type = type;
            this.isSuccess = isSuccess;
        }

        public RxEvent build() {
            return new RxEvent(type, isSuccess);
        }
    }
}
