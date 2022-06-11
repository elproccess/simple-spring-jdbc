import doa.personDOA;
import model.Person;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Application {

    public static void main(String[] args){
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-module.xml");
        personDOA personDOA = (personDOA) context.getBean(doa.personDOA.class);
        Person person = new Person("obiwan", "kinobi@gmail.com");
        personDOA.insertPerson(person);
        Person p = personDOA.getUserById(1);
        System.out.println(p.getName() + p.getEmail());
    }
}
