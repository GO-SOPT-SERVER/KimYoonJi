package sopt.org.SecondSeminar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import sopt.org.SecondSeminar.domain.Posting;
import sopt.org.SecondSeminar.domain.User;

import java.util.ArrayList;

@SpringBootApplication
public class SecondSeminarApplication {

	public static ArrayList<User> userList;
	public static ArrayList<Posting> postingList;

	public static void main(String[] args) {
		SpringApplication.run(SecondSeminarApplication.class, args);

		userList = new ArrayList<>();
		postingList = new ArrayList<>();
	}
}
