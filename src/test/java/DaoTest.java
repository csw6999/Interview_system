import com.system.entity.Interviewer;
import com.system.mapper.InterviewerMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:applicationContext.xml", "classpath:application-mybatis.xml"})
public class DaoTest {
    @Resource
    InterviewerMapper interviewerMapper;

    @Test
    public void test(){
        Interviewer interviewer = new Interviewer();
        Long age = 22L;
        String name = "zhangsan";
        String date = "2020-01-01";
        interviewer.setName(name);
        //interviewer.setGraduation_date(date);
        //interviewer.setAge(age);
        //interviewService.saveInterviewer(interviewer);
        interviewerMapper.insert(interviewer);
    }

}
