package com.deercoder.shop.stream;

import com.deercoder.commons.model.TokenModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
//@EnableBinding(Sink.class)
@EnableBinding(StreamClient.class)
public class StreamController {

	// 队列测试
	@StreamListener(StreamClient.INPUT)
	public void countPer(TokenModel message) {
		log.info("用户账号:" + message);
	}
}
