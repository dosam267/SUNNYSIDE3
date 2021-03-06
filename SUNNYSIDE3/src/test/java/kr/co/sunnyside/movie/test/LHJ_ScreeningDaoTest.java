package kr.co.sunnyside.movie.test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.WebApplicationContext;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import kr.co.sunnyside.cmn.SearchVO;
import kr.co.sunnyside.cmn.StringUtil;
import kr.co.sunnyside.movie.service.LHJ_BoxofficeVO;
import kr.co.sunnyside.movie.service.LHJ_MovieVO;
import kr.co.sunnyside.movie.service.impl.LHJ_BoxofficeDaoImpl;
import kr.co.sunnyside.store.service.SEJ_StroreVO;
import kr.co.sunnyside.store.service.impl.SEJ_StroreDaoImpl;

import kr.co.sunnyside.movie.service.impl.LHJ_MovieParsing;
import kr.co.sunnyside.movie.service.impl.LHJ_ScreeningDaoImpl;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations= {"file:src/main/webapp/WEB-INF/spring/**/*.xml"}) 
@FixMethodOrder(MethodSorters.NAME_ASCENDING) //테스트 이름 알파벳 순으로 수행한다.
public class LHJ_ScreeningDaoTest {
	private final static Logger LOG = LoggerFactory.getLogger(LHJ_ScreeningDaoTest.class);
	
	@Autowired
	private WebApplicationContext context;
	
	@Autowired
	private LHJ_ScreeningDaoImpl screeningDaoImpl;
	
	List<LHJ_MovieVO> list = new ArrayList<LHJ_MovieVO>();
	
	@Before
	public void setUp() throws IOException, ParseException{
		list = Arrays.asList(
				 new LHJ_MovieVO("F48336","조커","","","","2019-10-02","","",0,"","",0.0,0.0,"000","0","01")
				,new LHJ_MovieVO("K20477","가장","","","","2019-10-02","","",0,"","",0.0,0.0,"000","0","02")
				,new LHJ_MovieVO("K21186","퍼펙트맨","","","","2019-10-02","","",0,"","",0.0,0.0,"000","0","03")
				,new LHJ_MovieVO("K18372","장사리 ","","","","2019-9-25","","",0,"","",0.0,0.0,"000","0","04")
				,new LHJ_MovieVO("F48958","소피와","","","","2019-10-02","","",0,"","",0.0,0.0,"000","0","05")
				,new LHJ_MovieVO("F49317","몬스터","","","","2019-10-03","","",0,"","",0.0,0.0,"000","0","06")
				,new LHJ_MovieVO("K21060","양자물리학","","","","2019-09-25","","",0,"","",0.0,0.0,"000","0","07")
				,new LHJ_MovieVO("K21094","나쁜","","","","2019-09-11","","",0,"","",0.0,0.0,"000","0","08")
				,new LHJ_MovieVO("F48214","원스","","","","2019-09-25","","",0,"","",0.0,0.0,"000","0","09")
				,new LHJ_MovieVO("F48401","47미터","","","","2019-08-28","","",0,"","",0.0,0.0,"000","0","10")
		);		
	}		
	
	/**전체 테스트*/
	@Test
//	@Ignore
	public void allTest() {
		//상영상태-상영안함('000')으로 업데이트
		for(LHJ_MovieVO vo:list) {
			int flag = screeningDaoImpl.do_update_screenDown(vo);
			assertThat(1, is(flag));
		}
		
		//상영상태 -상영중('010')으로 업데이트
		for(LHJ_MovieVO vo:list) {
			int flag = screeningDaoImpl.do_update_screenUp(vo);
			assertThat(1, is(flag));
		}
		
		//단건조회
		screeningDaoImpl.do_selectOne(list.get(0));
		
		//전체조회
		SearchVO search = new SearchVO();
		search.setPageSize(10);
		search.setPageNum(1);
		
		List<LHJ_MovieVO> list = (List<LHJ_MovieVO>) screeningDaoImpl.do_retrieve(search);
		assertThat(10, is(list.size()));	
	}
	
	/**목록조회*/
	@Test
	@Ignore
	public void do_retrieve() {
		SearchVO search = new SearchVO();
		search.setPageSize(10);
		search.setPageNum(1);
//		search.setSearchDiv("10");
//		search.setSearchWord("물리");
		
		List<LHJ_MovieVO> list = (List<LHJ_MovieVO>) screeningDaoImpl.do_retrieve(search);
		assertThat(1, is(list.size()));		
	}
	
	
	/**단건조회*/
	@Test
	@Ignore
	public void do_selectOne() {
		screeningDaoImpl.do_selectOne(list.get(0));
	}
	
	/** 비 상영상태로 상태 변경 */
	@Test
	@Ignore
	public void do_update_screenDown() {
		for(LHJ_MovieVO vo:list) {
			int flag = screeningDaoImpl.do_update_screenDown(vo);
			assertThat(1, is(flag));
		}
	}
	
	/** 최신개봉으로 상태 변경(상영중으로 상태 변경) */
	@Test
	@Ignore
	public void do_update_screenUp() {
		for(LHJ_MovieVO vo:list) {
			int flag = screeningDaoImpl.do_update_screenUp(vo);
			assertThat(1, is(flag));
		}
	}
	
	@Test
	@Ignore
	public void getBean() {
		LOG.debug("======================");
		LOG.debug("context="+context);
		LOG.debug("screeningDaoImpl="+screeningDaoImpl);
		LOG.debug("======================");
		assertThat(context, is(notNullValue()));
		assertThat(screeningDaoImpl, is(notNullValue()));
	}
	
	@After
	public void tearDown() {
		
	}
	
}
