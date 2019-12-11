package com.ityu.mall.component;


import com.ityu.mall.dto.QueueEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 取消订单消息的发出者
 * Created by macro on 2018/9/14.
 */
@Component
public class TestSender {
    private static Logger LOGGER = LoggerFactory.getLogger(TestSender.class);
    @Autowired
    private AmqpTemplate amqpTemplate;

    public void sendMessage(String msg,Long de) {
        //给延迟队列发送消息
        amqpTemplate.convertAndSend(QueueEnum.QUEUE_ITYU_TEST.getExchange(), QueueEnum.QUEUE_ITYU_TEST.getRouteKey(), msg,new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                //给消息设置延迟毫秒值
                message.getMessageProperties().setExpiration(String.valueOf(de));
                return message;
            }
        });
    }
}
