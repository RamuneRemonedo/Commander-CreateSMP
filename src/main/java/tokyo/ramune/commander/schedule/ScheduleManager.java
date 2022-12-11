package tokyo.ramune.commander.schedule;

import it.sauronsoftware.cron4j.Scheduler;

import javax.annotation.Nonnull;

public class ScheduleManager {
    private Scheduler scheduler;

    public ScheduleManager() {
    }

    public void start(@Nonnull Runnable runnable) {
        if (scheduler != null && scheduler.isStarted())
            scheduler.stop();

        scheduler = new Scheduler();
        scheduler.schedule("0 0 * * *", runnable);
        scheduler.start();
    }

    public void stop() {
        if (scheduler != null && !scheduler.isStarted())
            scheduler.stop();
    }
}
