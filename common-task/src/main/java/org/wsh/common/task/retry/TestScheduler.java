package org.wsh.common.task.retry;

/**
 * author: wsh
 * JDK-version:  JDK1.8
 * comments:  对此类的描述，可以引用系统设计中的描述
 * since Date： 2017-04-14 15:12
 */
import java.util.Date;
import java.util.List;

public class TestScheduler extends AbstractRetryScheduleTask<Integer> {

    public static void main(String[] args) {
        TestScheduler test = new TestScheduler();
        test.addData(0);
        test.addRetryData(1, TestScheduler.RETRY_MODE_30S);
        test.addRetryData(2, TestScheduler.RETRY_MODE_5MIN);
        test.addRetryData(3, TestScheduler.RETRY_MODE_30MIN);
        test.addData(0);
        test.addRetryData(1, TestScheduler.RETRY_MODE_30S);
        test.addRetryData(2, TestScheduler.RETRY_MODE_5MIN);
        test.addRetryData(3, TestScheduler.RETRY_MODE_30MIN);
        try {
            System.out.println("now sleep");
            Thread.sleep(10 * 1000);
            System.out.println("now weakup");
            test.addData(0);
            test.addRetryData(1, TestScheduler.RETRY_MODE_30S);
            test.addRetryData(2, TestScheduler.RETRY_MODE_5MIN);
            test.addRetryData(3, TestScheduler.RETRY_MODE_30MIN);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected Integer doRetryData(List<Integer> data) {
        return doData(data);
    }

    @Override
    protected Integer doData(List<Integer> data) {
        System.out.println(data + " " + new Date().getTime());
        return 1;
    }
}
