package tra.irts4;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.zkoss.zk.au.http.DHtmlUpdateServlet;
import org.zkoss.zk.ui.http.DHtmlLayoutServlet;

@SpringBootApplication
public class ApplicationWeb extends SpringBootServletInitializer {

	private static final Logger LOG = LoggerFactory.getLogger(ApplicationWeb.class);

	public static void main(String[] args) {
		System.setProperty("spring.devtools.restart.enabled", "true");
		System.setProperty("spring.devtools.livereload.enabled", "true");
		
		// args 優先權最高
		ApplicationContext ctx = SpringApplication.run(ApplicationWeb.class, args);
		
//		proc(ctx);
	}

	private static void proc(ApplicationContext ctx) {
		// if (LOG.isDebugEnabled()) {
		// under log: Started Application in 00.00 seconds (JVM running for
		// 0.00)
		LOG.info("[Inspect the spring beans]");

		String[] beanNames = ctx.getBeanDefinitionNames();
		Arrays.sort(beanNames);
		for (String beanName : beanNames) {

			LOG.info("{} ", beanName);
		}
		// }
	}

	/*
	 * ZK servlets
	 */
	@Bean
	public ServletRegistrationBean dHtmlLayoutServlet() {
		Map<String, String> params = new HashMap<String, String>();
		params.put("update-uri", "/zkau");
		DHtmlLayoutServlet dHtmlLayoutServlet = new DHtmlLayoutServlet();
		ServletRegistrationBean reg = new ServletRegistrationBean(dHtmlLayoutServlet, "*.zul");
		reg.setLoadOnStartup(100);
		reg.setInitParameters(params);
		return reg;
	}

	@Bean
	public ServletRegistrationBean dHtmlUpdateServlet() {
		Map<String, String> params = new HashMap<String, String>();
		params.put("update-uri", "/zkau/*");
		ServletRegistrationBean reg = new ServletRegistrationBean(new DHtmlUpdateServlet(), "/zkau/*");
		reg.setLoadOnStartup(101);
		reg.setInitParameters(params);
		return reg;
	}
}
