package com.pan.info.base;

/**
 * Author : Pan
 * Date : 19/03/2017
 */

public class Event {
    private String type;
    private boolean isSuccess;

    private Event(String type, boolean isSuccess) {
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

    public static class EventBuilder {
        private String type;
        private boolean isSuccess;

        public EventBuilder(String type, boolean isSuccess) {
            this.type = type;
            this.isSuccess = isSuccess;
        }

        public Event build() {
            return new Event(type, isSuccess);
        }
    }
}
