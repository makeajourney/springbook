package springbook.user.dao;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration	// 애플리케이션 컨텍스트 또는 빈 팩토리가 사용할 설정정보라는 표
public class DaoFactory {
	@Bean	// 오브젝트 생성을 담당하는 IoC용 메소드라는 표시 
	public UserDao userDao() {
		UserDao userDao = new UserDao();
		userDao.setConnectionMaker(connectionMaker());
		return userDao;
	}
	
	// 분리해서 중복을 제거한 ConnectionMaker 타입 오브젝트 생성 코드
	@Bean
	public ConnectionMaker connectionMaker() {
		return new SimpleConnectionMaker();
	}
}