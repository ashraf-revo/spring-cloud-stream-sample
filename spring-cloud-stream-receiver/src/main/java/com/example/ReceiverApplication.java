package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.ServiceActivator;

@EnableBinding(Sink.class)
@IntegrationComponentScan
@SpringBootApplication
public class ReceiverApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReceiverApplication.class, args);
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
	@Override
	public String toString() {
		return "Reservation {\"id\":\"" + id + "\",\"name\":\"" + name + "\"}";
	}
	
}


@MessageEndpoint
class MessageAcceptor{
	@ServiceActivator (inputChannel=Sink.INPUT)
	public void accept(Reservation r){
		System.out.println(r);
	}
}