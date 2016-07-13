package springbook.learningtest.junit;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.matchers.JUnitMatchers.hasItem;
import static org.junit.matchers.JUnitMatchers.either;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="/junit.xml")
public class JUnitTest {
	@Autowired ApplicationContext context;	// 테스트 컨텍스트가 매번 주입해주는 애플리케이션 컨텍스트는 항상 같은 오브젝트인지 테스트로 확인해본다.
	
	static Set<JUnitTest> testObjects = new HashSet<JUnitTest>();
	static ApplicationContext contextObject = null;
	
	@Test public void test1() {
		assertThat(testObjects, not(hasItem(this)));
		testObjects.add(this);
		
		// 매처와 비교할 대상인 첫 번째 파라미터에 Boolean 타입의 결과가 나오는 조건문을 넣는다. 그리고 그 결과를 is() 매처를 써서 true와 비교하면 된다.
		assertThat(contextObject == null || contextObject == this.context, is(true));
		contextObject = this.context;
	}
	
	@Test public void test2() {
		assertThat(testObjects, not(hasItem(this)));
		testObjects.add(this);
		
		// 조건문을 넣어서 그 결과를 true와 비교.
		assertTrue(contextObject == null || contextObject == this.context);
		contextObject = this.context;
	}
	
	@Test public void test3() {
		assertThat(testObjects, not(hasItem(this)));
		testObjects.add(this);
		
		// either()는 뒤에 이어서 나오는 or()와 함께 두 개의 매처의 결과를 OR 조건으로 비교해준다.
		// nullValue() -> 오브젝트가 null인지 확인해준다.
		assertThat(contextObject, either(is(nullValue())).or(is(this.context)));
		contextObject = this.context;
	}
}
