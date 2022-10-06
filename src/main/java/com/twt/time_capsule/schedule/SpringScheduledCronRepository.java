package com.twt.time_capsule.schedule;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;


@Component
public class SpringScheduledCronRepository {

    static int i = 0;
    static {
        System.out.println("in=="+(i++));
    }

    public SpringScheduledCron findByCronKey(String cronKey) {

        SpringScheduledCron cron = null;

        List<SpringScheduledCron> list = findAll();
        for (SpringScheduledCron springScheduledCron : list) {
            if(springScheduledCron.getCronKey().equals(cronKey)) {
                cron = springScheduledCron;
            }
        }

        return cron;
    };

    public List<SpringScheduledCron> findAll(){

        List<SpringScheduledCron> list = new ArrayList<SpringScheduledCron>();
        try {

            File cronFile = new File("./config/cron.txt");
            FileReader in = new FileReader(cronFile);
            BufferedReader bufIn = new BufferedReader(in);
            // 替换
            String line = null;
            while ((line = bufIn.readLine()) != null) {
                String[] ls = line.split("\\|");
                if(ls.length == 5) {
                    SpringScheduledCron cron =  new SpringScheduledCron(ls[0],ls[1],ls[2],ls[3],ls[4]);
                    list.add(cron);
                }
            }
            // 关闭 输入流
            bufIn.close();
        } catch (Throwable e) {
            System.out.println(e.getMessage());
        }
        return list;
    };

}
