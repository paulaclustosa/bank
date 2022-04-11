package com.letscode.bank.repository;

import com.letscode.bank.user.model.User;
import com.letscode.bank.user.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

@DataJpaTest
@ActiveProfiles("test")
public class UserRepositoryTest {

  private final TestEntityManager entityManager;
  private final UserRepository userRepository;

  @Autowired
  public UserRepositoryTest(TestEntityManager entityManager, UserRepository userRepository) {
    this.entityManager = entityManager;
    this.userRepository = userRepository;
  }

  @Test
  void should_store_a_user() {
    User user = new User();
    user.setName("João dos Testes");
    user.setCpf("49402637702");
    user.setPassword("sun");

    User userSaved = userRepository.save(user);

    Assertions.assertEquals(userSaved.getName(), user.getName());
    Assertions.assertEquals(userSaved.getCpf(), user.getCpf());
    Assertions.assertEquals(userSaved.getPassword(), user.getPassword());
  }

  @Test
  void should_find_no_results_if_repository_is_empty() {
    List<User> users = userRepository.findAll();
    Assertions.assertEquals(List.of(), users);
  }

  @Test
  void should_find_all_stored_users_when_called_find_all() {
    User userFirst = new User();
    userFirst.setName("Paula");
    userFirst.setCpf("77106218219");
    userFirst.setPassword("1234");

    User userSecond = new User();
    userFirst.setName("Kiko");
    userFirst.setCpf("55741445306");
    userFirst.setPassword("happy");

    entityManager.persist(userFirst);
    entityManager.persist(userSecond);

    List<User> users = userRepository.findAll();
    Assertions.assertEquals(List.of(userFirst, userSecond), users);
  }

  @Test
  void should_find_user_by_id() {
    User userFirst = new User();
    userFirst.setName("Paula");
    userFirst.setCpf("77106218219");
    userFirst.setPassword("1234");

    User userSecond = new User();
    userFirst.setName("Kiko");
    userFirst.setCpf("55741445306");
    userFirst.setPassword("happy");

    var userFirstPersisted = entityManager.persist(userFirst);
    var userSecondPersisted = entityManager.persist(userSecond);

    Assertions.assertEquals(userFirstPersisted.getId(), userRepository.getById(userFirstPersisted.getId()).getId());
    Assertions.assertEquals(userSecondPersisted.getId(), userRepository.getById(userSecondPersisted.getId()).getId());
  }

  @Test
  void should_update_user_by_id() {
    User userFirst = new User();
    userFirst.setName("Paula");
    userFirst.setCpf("77106218219");
    userFirst.setPassword("1234");

    User userSecond = new User();
    userFirst.setName("Kiko");
    userFirst.setCpf("55741445306");
    userFirst.setPassword("happy");

    entityManager.persist(userFirst);
    entityManager.persist(userSecond);

    User updatedUserRef = new User();
    updatedUserRef.setName("Paula Updated");
    updatedUserRef.setCpf("77106218219");
    updatedUserRef.setPassword("1234");

    User userToBeUpdated = userRepository.getById(userFirst.getId());
    userToBeUpdated.setName(updatedUserRef.getName());
    userToBeUpdated.setName(updatedUserRef.getCpf());
    userToBeUpdated.setName(updatedUserRef.getName());

    userRepository.save(userToBeUpdated);

    User updatedUserFoundFromDb = userRepository.getById(userFirst.getId());

    Assertions.assertEquals(updatedUserFoundFromDb.getId(), userFirst.getId());
    Assertions.assertEquals(updatedUserFoundFromDb.getName(), userToBeUpdated.getName());
    Assertions.assertEquals(updatedUserFoundFromDb.getCpf(), userToBeUpdated.getCpf());
    Assertions.assertEquals(updatedUserFoundFromDb.getPassword(), userToBeUpdated.getPassword());
  }

  @Test
  void should_delete_user_by_id() {
    User userFirst = new User();
    userFirst.setName("João dos Testes");
    userFirst.setCpf("49402637702");
    userFirst.setPassword("sun");

    User userSecond = new User();
    userSecond.setName("Paula");
    userSecond.setCpf("77106218219");
    userSecond.setPassword("1234");

    User userThird = new User();
    userThird.setName("Kiko");
    userThird.setCpf("55741445306");
    userThird.setPassword("happy");

    entityManager.persist(userFirst);
    entityManager.persist(userSecond);
    entityManager.persist(userThird);

    List<User> usersBeforeOneDeleted = userRepository.findAll();
    userRepository.deleteById(userThird.getId());
    List<User> usersAfterOneDeleted = userRepository.findAll();

    Assertions.assertEquals(3, usersBeforeOneDeleted.size());
    Assertions.assertEquals(2, usersAfterOneDeleted.size());

    Assertions.assertTrue(usersBeforeOneDeleted.contains(userFirst));
    Assertions.assertTrue(usersBeforeOneDeleted.contains(userSecond));
    Assertions.assertTrue(usersBeforeOneDeleted.contains(userThird));

    Assertions.assertTrue(usersAfterOneDeleted.contains(userFirst));
    Assertions.assertTrue(usersAfterOneDeleted.contains(userSecond));
    Assertions.assertFalse(usersAfterOneDeleted.contains(userThird));
  }

  void exists_by_id_should_return_true_for_stored_user() {

  }

  void exists_by_id_should_return_false_for_user_not_stored() {

  }

}
