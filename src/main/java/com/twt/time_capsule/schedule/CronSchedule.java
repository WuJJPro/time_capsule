//package com.twt.time_capsule.schedule;
//
//
//import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
//import com.twt.time_capsule.entity.ScheduleSetting;
//import com.twt.time_capsule.mapper.ScheduledSettingMapper;
//import io.lettuce.core.dynamic.support.ReflectionUtils;
//import java.lang.reflect.Method;
//import java.util.Date;
//import java.util.List;
//import org.apache.commons.lang3.StringUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.Trigger;
//import org.springframework.scheduling.TriggerContext;
//import org.springframework.scheduling.annotation.SchedulingConfigurer;
//import org.springframework.scheduling.config.ScheduledTaskRegistrar;
//import org.springframework.scheduling.support.CronTrigger;
//import org.springframework.stereotype.Component;
//import org.springframework.beans.BeansException;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.ApplicationContextAware;
//@Component
//public class CronSchedule implements SchedulingConfigurer,ApplicationContextAware{
//    @Autowired
//    private ScheduledSettingMapper scheduledSettingMapper;
//    private static ApplicationContext applicationContext;
//    @Override
//    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
//        // 获取所有任务
//        List<ScheduleSetting> scheduleList = scheduledSettingMapper.selectList(null);
//        System.out.println(scheduleList.size());
//        if (CollectionUtils.isNotEmpty(scheduleList)){
//            for (ScheduleSetting s : scheduleList){
//                scheduledTaskRegistrar.addTriggerTask(getRunnable(s), getTrigger(s));
//            }
//        }
//    }
//
//    /**
//     * 转换首字母小写
//     *
//     * @param str
//     * @return
//     */
//    public static String lowerFirstCapse(String str) {
//        char[] chars = str.toCharArray();
//        chars[0] += 32;
//        return String.valueOf(chars);
//    }
//
//    private Runnable getRunnable(ScheduleSetting scheduleSetting){
//        return new Runnable() {
//            @Override
//            public void run() {
//                Class<?> clazz;
//                try {
//                    clazz = Class.forName(scheduleSetting.getBeanName());
//                    String className = lowerFirstCapse(clazz.getSimpleName());
//                    Object bean = applicationContext.getBean(className);
//                    Method method = ReflectionUtils.findMethod(bean.getClass(), scheduleSetting.getMethodName());
//                    if (StringUtils.isNotEmpty(scheduleSetting.getMethodParams())) {
//                        ReflectionUtils.invokeMethod(method, bean, scheduleSetting.getMethodParams());
//                    } else {
//                        ReflectionUtils.invokeMethod(method, bean);
//                    }
//                } catch (ClassNotFoundException e) {
//                    e.printStackTrace();
//                }
//            }
//        };
//    }
//
//    private Trigger getTrigger(ScheduleSetting scheduleSetting){
//        return new Trigger() {
//            @Override
//            public Date nextExecutionTime(TriggerContext triggerContext) {
//                CronTrigger trigger = new CronTrigger(scheduleSetting.getCronExpression());
//                Date nextExec = trigger.nextExecutionTime(triggerContext);
//                return nextExec;
//            }
//        };
//    }
//
//    @Override
//    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
//        CronSchedule.applicationContext = applicationContext;
//    }
//}
