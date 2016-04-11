package com.example;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@EnableBinding (Source.class)
@SpringBootApplication
public class SenderApplication {

	public static void main(String[] args) {
		SpringApplication.run(SenderApplication.class, args);
	}
}

class Reservation{
	private Long id;
	private String name;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Reservation(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
}


@RestController
class Controller {

	@Autowired
	@Output(Source.OUTPUT)
	private MessageChannel channel;

	@RequestMapping(method=RequestMethod.POST, value="/test")
	public void write (){
		System.out.println("received POST request");
		this.channel.send(MessageBuilder.withPayload(new Reservation((long)1, "Derrick")).build());
	}
	
}