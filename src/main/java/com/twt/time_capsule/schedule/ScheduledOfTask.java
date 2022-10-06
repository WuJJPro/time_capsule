package com.twt.time_capsule.schedule;

public interface ScheduledOfTask extends Runnable {
    /**
     * 定时任务方法
     */
    void execute();
    /**
     * 实现控制定时任务启用或禁用的功能
     */
    @Override
    default void run() {
        SpringScheduledCronRepository repository = SpringUtils.getBean(SpringScheduledCronRepository.class);
        SpringScheduledCron scheduledCron = repository.findByCronKey(this.getClass().getName());
        if ("".equals(scheduledCron.getStatus())) {
            return;
        }
        execute();
    }
}




