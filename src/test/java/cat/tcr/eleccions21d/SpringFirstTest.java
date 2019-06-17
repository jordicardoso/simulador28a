package cat.tcr.eleccions21d;

import java.io.File;
import java.io.FileOutputStream;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.Consumer;
import org.apache.camel.Endpoint;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.test.spring.CamelSpringTestSupport;
import org.junit.Test;
import org.springframework.context.support.AbstractXmlApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringFirstTest extends CamelSpringTestSupport {
	protected AbstractXmlApplicationContext createApplicationContext() {
		return new ClassPathXmlApplicationContext(
				"META-INF/spring/camel-context.xml");
	}

	private FileOutputStream fos;

	@Test
	public void testRecompte() throws Exception {
		context.start();

		MockEndpoint result = getMockEndpoint("mock:result");
		result.expectedMessageCount(40);

		assertMockEndpointsSatisfied();

		context.stop();
	}
}