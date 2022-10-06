package com.twt.time_capsule.schedule;

public class SpringScheduledCron {
    private String cronId;
    private String cronKey;
    private String cronExpression;
    private String taskExplain;
    private String status;



    @Override
    public String toString() {
        return "SpringScheduledCron{" + "cronId=" + cronId + ", cronKey='" + cronKey + '\'' + ", cronExpression='"
                + cronExpression + '\'' + ", taskExplain='" + taskExplain + '\'' + ", status=" + status + '}';
    }

    public String getCronId() {
        return cronId;
    }

    public void setCronId(String cronId) {
        this.cronId = cronId;
    }

    public String getCronKey() {
        return cronKey;
    }

    public void setCronKey(String cronKey) {
        this.cronKey = cronKey;
    }

    public String getCronExpression() {
        return cronExpression;
    }

    public void setCronExpression(String cronExpression) {
        this.cronExpression = cronExpression;
    }

    public String getTaskExplain() {
        return taskExplain;
    }

    public void setTaskExplain(String taskExplain) {
        this.taskExplain = taskExplain;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }



    public SpringScheduledCron() {
        // TODO Auto-generated constructor stub
    }

    public SpringScheduledCron(String cronId, String cronKey, String cronExpression, String taskExplain,
                               String status) {
        this.cronId = cronId;
        this.cronKey = cronKey;
        this.cronExpression = cronExpression;
        this.taskExplain = taskExplain;
        this.status = status;
    }



}

