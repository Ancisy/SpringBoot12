package com.example.midtest;

import com.example.midtest.entity.Course;
import com.example.midtest.entity.Topic;
import com.example.midtest.entity.User;
import com.example.midtest.repository.CourseRepository;
import com.example.midtest.repository.TopicRepository;
import com.example.midtest.repository.UserRepository;
import com.example.midtest.service.CourseService;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@DataJpaTest
@AutoConfigureTestDatabase (replace = AutoConfigureTestDatabase.Replace.NONE)
class MidTestApplicationTests {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TopicRepository topicRepository;

    @Autowired
    private CourseRepository courseRepository;




    @Test
    @Rollback(value = false)
    void save_user(){
        User user = User.builder()
                .name("Nguyễn Văn A")
                .email("NguyenVanA@gmail.com")
                .phone("09869886666")
                .build();

        User user1 = User.builder()
                .name("Nguyen Van Dao")
                .email("NguyenVanDao@gmail.com")
                .phone("0989977666")
                .build();

        User user2 = User.builder()
                .name("Tran Dai Nghia")
                .email("TranDaiNghia@gmail.com")
                .phone("0933880000")
                .build();

        userRepository.saveAll(List.of(user,user1,user2));
    }

    @Test
    @Rollback(value = false)
    void save_topic(){
        Topic topic = Topic.builder()
                .name("Java Basics")
                .build();

        Topic topic1 = Topic.builder()
                .name("Spring Boot Basics")
                .build();

        Topic topic2 = Topic.builder()
                .name("C++ Basics")
                .build();

        Topic topic3 = Topic.builder()
                .name("C# Basics")
                .build();

        topicRepository.saveAll(List.of(topic,topic1,topic2,topic3));
    }

    @Test
    @Rollback(value = false)
    void save_course(){
        Faker fake = new Faker();

        List<User> list = userRepository.findAll();
        List<Topic> topicList = topicRepository.findAll();

        Random rd = new Random();

        for (int i = 0; i < 10; i++) {
            User rduser = list.get(rd.nextInt(list.size()));

            List<Topic> myList = new ArrayList<>();
            for (int j = 0; j < 3; j++) {
                Topic rdtopic = topicList.get(rd.nextInt(topicList.size()));
                if(!myList.contains(rdtopic)){
                    myList.add(rdtopic);
                }
            }
            Course course = Course.builder()
                    .name(fake.name().fullName())
                    .description(fake.expression("Hello"))
                    .type(rd.nextInt(2)== 0 ? "Online" : "Onlab")
                    .user(rduser)
                    .topics(myList)
                    .build();
            courseRepository.save(course);
        }
    }

//    @Test
//    void test_sql(){
//        System.out.println(courseRepository.getCourseByOnline());
//    }
}
