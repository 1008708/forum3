package telran.java2022.security.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import telran.java2022.accounting.dao.UserAccountRepository;
import telran.java2022.accounting.model.UserAccount;
import telran.java2022.post.dao.PostRepository;
import telran.java2022.post.model.Post;

@Service
@RequiredArgsConstructor
public class CustomWebSecurity {

	final PostRepository postRepository;
	final UserAccountRepository repository;
	final static long DATE_PERIOD = 60;
	
	public boolean checkPostAuthor(String postId, String userName) {
		Post post = postRepository.findById(postId).orElse(null);
		return post != null && userName.equalsIgnoreCase(post.getAuthor()); 
	}
	
	public boolean checkDatePassword(String userName) {
		UserAccount userAccount = repository.findById(userName).orElse(null);
		if (userAccount == null) {
			return false;
		}
		long period = ChronoUnit.DAYS.between(userAccount.getDatePassword(), LocalDate.now());
		return period <= DATE_PERIOD;
	}

}
