package sopt.org.thirdSeminar.infrastructure;

import org.springframework.data.repository.Repository;
import sopt.org.thirdSeminar.domain.User;

public interface UserRepository extends Repository<User, Long> {
    void save(User user);
}