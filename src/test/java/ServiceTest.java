import com.system.entity.Interviewer;
import com.system.service.InterviewService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:applicationContext.xml", "classpath:application-mybatis.xml"})
@Transactional(transactionManager = "transactionManager")
@Rollback(value = false)
public class ServiceTest {
    @Autowired
    InterviewService interviewService;

    @Test
    public void add(){
        Interviewer interviewer = new Interviewer();
        String age = "23";
        String name = "zhangsan";
        String date = "2020-01-01";
        interviewer.setName(name);
        //interviewer.setGraduation_date(date);
        //interviewer.setAge(age);
        interviewService.saveInterviewer(interviewer);
    }
}
