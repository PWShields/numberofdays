package com.puffin.numberofdays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NumberofdaysApplicationTests {

	@Test
	public void contextLoads() {
	}

	@Test
	public void whenInputValid_ResultIsAccurate(){
//		Input       (DD/MM/YYYY)      Output (Days)
//		02/06/1983  22/06/1983          19
//		04/07/1984  25/12/1984          173
//      03/01/1989  03/08/1983          1979
	}

}
