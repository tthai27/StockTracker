package com.stocktracker.util;

import java.util.List;
import java.util.Map.Entry;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.AmazonSQSException;
import com.amazonaws.services.sqs.model.CreateQueueRequest;
import com.amazonaws.services.sqs.model.ListQueuesResult;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.stocktracker.model.StockTrackerModel;

public class SQSUtil {
	
	private final String QUEUE_URL = "https://sqs.us-east-1.amazonaws.com/192241899469/EmailMQ";
	
	private static SQSUtil instance;
	
	public SQSUtil(){ 
	
	}
	
	public static SQSUtil getInstance(){
		if(null == instance){
			synchronized(SQSUtil.class){
				if(null == instance){
					instance = new SQSUtil();
				}
			}
		}
		return instance;
	}
	
	public static void main(String[] args){
//		String jsonInString = "{'companyName' : 'ACADIA','symbol':'ACAD','lastTradePrice':'33.33'}";
//		Gson gson = new Gson();
//		StockTrackerModel staff = gson.fromJson(jsonInString, StockTrackerModel.class);
//		staff.getCompanyName();
		System.out.println();
		StockTrackerModel model = new StockTrackerModel();
		model.setCompanyName("Alibaba");
		model.setSymbol("BABA");
		model.setLastTradePrice(122.33);
		SQSUtil util = SQSUtil.getInstance();
		//util.producerSQS(model);
		util.consumerSQS();
	}
	

	public void producerSQS(StockTrackerModel model){
		final String QUEUE_NAME = "EmailMQ";
		AmazonSQS sqs = AmazonSQSClientBuilder.defaultClient();
		CreateQueueRequest create_request = new CreateQueueRequest(QUEUE_NAME)
		        .addAttributesEntry("DelaySeconds", "5")
		        .addAttributesEntry("MessageRetentionPeriod", "86400");

		try {
		    sqs.createQueue(create_request);
		} catch (AmazonSQSException e) {
		    if (!e.getErrorCode().equals("QueueAlreadyExists")) {
		        throw e;
		    }
		}
		// Get the URL for a queue
        String queue_url = sqs.getQueueUrl(QUEUE_NAME).getQueueUrl();
        ListQueuesResult lq_result = sqs.listQueues();
        System.out.println("Your SQS Queue URLs:");
        for (String url : lq_result.getQueueUrls()) {
            System.out.println(url);
        }
        
        System.out.println("queue_url= "+queue_url);
		SendMessageRequest send_msg_request = new SendMessageRequest()
		        .withQueueUrl(QUEUE_URL)
		        .withMessageBody(model.toString())
		        .withDelaySeconds(5);
		sqs.sendMessage(send_msg_request);
	}
	
	public void consumerSQS(){
		System.out.println("Receiving messages from MyQueue.\n");
		AmazonSQS sqs = AmazonSQSClientBuilder.defaultClient();
		ReceiveMessageRequest receiveMessageRequest = new ReceiveMessageRequest(QUEUE_URL);
		List<Message> messages = sqs.receiveMessage(receiveMessageRequest).getMessages();
		for (Message message : messages) {
		    System.out.println("  Message");
		    System.out.println("    MessageId:     " + message.getMessageId());
		    System.out.println("    ReceiptHandle: " + message.getReceiptHandle());
		    System.out.println("    MD5OfBody:     " + message.getMD5OfBody());
		    System.out.println("    Body:          " + message.getBody());
		    for (Entry<String, String> entry : message.getAttributes().entrySet()) {
		        System.out.println("  Attribute");
		        System.out.println("    Name:  " + entry.getKey());
		        System.out.println("    Value: " + entry.getValue());
		    }
		}
		System.out.println();
		for (Message m : messages) {
			System.out.println("deleting " + m);
		    sqs.deleteMessage(QUEUE_URL, m.getReceiptHandle());
		}
	}
	
}
