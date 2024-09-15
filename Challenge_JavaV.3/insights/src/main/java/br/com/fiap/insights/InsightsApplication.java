	package br.com.fiap.insights;

	import org.springframework.boot.SpringApplication;
	import org.springframework.boot.autoconfigure.SpringBootApplication;
	import org.springframework.web.bind.annotation.RequestMapping;
	import org.springframework.web.bind.annotation.ResponseBody;

	@SpringBootApplication
	public class InsightsApplication {

		public static void main(String[] args) {
			SpringApplication.run(InsightsApplication.class, args);
		}

		@RequestMapping
		@ResponseBody
		public String home(){
			return "DaVinci Insights";
		}

	}
