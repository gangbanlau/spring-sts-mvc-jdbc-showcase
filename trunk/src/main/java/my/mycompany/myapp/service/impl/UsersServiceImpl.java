package my.mycompany.myapp.service.impl;

import my.mycompany.myapp.domain.User;
import my.mycompany.myapp.repository.IUserDao;
import my.mycompany.myapp.service.IUsersService;

import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Sha512Hash;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@PropertySource("classpath:project.properties")
@Transactional
public class UsersServiceImpl implements IUsersService {

	private static final Logger logger = LoggerFactory.getLogger(UsersServiceImpl.class);
	
	private SecureRandomNumberGenerator srGen = new SecureRandomNumberGenerator();
	
	@Autowired
    IUserDao userDao;
	
	@Autowired
	Environment env;
	
	public User insertUser(User newUser, String password) {
		newUser.setSalt(getSalt());
		newUser.setPassphrase(encodePassphrase(password, newUser.getSalt()));
		
		return userDao.insert(newUser);
	}
	
	protected String getSalt() {
		    return srGen.nextBytes().toBase64();
	}

	protected String getApplicationSalt() {
			return env.getProperty("shiro.applicationSalt");
	}
	
	protected String getCombinedSalt(String salt) {
		    return env.getProperty("shiro.applicationSalt") + ":" + salt;
	}

	protected String encodePassphrase(String rawPassphrase, String salt) {
		return new Sha512Hash(rawPassphrase, getCombinedSalt(salt), getIterations()).toBase64();
	}

	protected Integer getIterations() {
		    return Integer.parseInt(env.getProperty("shiro.hashIterations"));
	}	
}
