package TareasProgramadas;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import static org.quartz.CronScheduleBuilder.cronSchedule;

public class ReporteMensualDeGastosEjecutable {

    public static void main(String[] args) throws SchedulerException {

        JobDetail job = JobBuilder.newJob(ReporteMensualDeGastos.class).withIdentity("reporteDeGastos", "grupo1").build();

        Trigger triggerReporteMensualDeGastos = TriggerBuilder.newTrigger()
                .withIdentity("reporteDeGastos", "grupo1")
                // Ultimo día del mes a las 23:59 PM
                //.withSchedule(cronSchedule("0 59 23 L * ?"))
                // Cada 5 segundos
                .withSchedule(cronSchedule("0/5 * * * * ?"))
                .build();

        Scheduler scheduler = new StdSchedulerFactory().getScheduler();
        scheduler.start();
        scheduler.scheduleJob(job, triggerReporteMensualDeGastos);


    }
}