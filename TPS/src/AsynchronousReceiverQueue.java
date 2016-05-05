import java.util.Properties;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicSession;
import javax.jms.TopicSubscriber;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class AsynchronousReceiverQueue implements MessageListener {
    private Context context = null;
    private TopicConnectionFactory factory = null;
    private TopicConnection connection = null;
    private TopicSession session = null;
    private Topic topic = null;
    private TopicSubscriber subscriber = null;
    Thread idleThread = null;

    public AsynchronousReceiverQueue() {

    }

    public void subscribeMessage() {
        Properties initialProperties = new Properties();
        initialProperties.put(InitialContext.INITIAL_CONTEXT_FACTORY,
                "org.exolab.jms.jndi.InitialContextFactory");
        initialProperties.put(InitialContext.PROVIDER_URL,
                "tcp://localhost:3035");
        try {
            context = new InitialContext(initialProperties);
            factory = (TopicConnectionFactory) context
                    .lookup("ConnectionFactory");
            topic = (Topic) context.lookup("topic1");
            connection = factory.createTopicConnection();
            session = connection.createTopicSession(false,
                    TopicSession.AUTO_ACKNOWLEDGE);
            subscriber = session.createSubscriber(topic);
            subscriber.setMessageListener(this);
            connection.start();
            Runnable idleRunnable = new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        // stay here idle..the program should not exit till the
                        // response receives..Thats why...
                    }
                }

            };
            idleThread = new Thread(idleRunnable, "idleThread");
            idleThread.start();
        } catch (JMSException e) {
            e.printStackTrace();
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onMessage(Message message) {
        if (message instanceof TextMessage) {
            TextMessage text = (TextMessage) message;
            try {
                System.out.println("Message received is : " + text.getText());
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        AsynchronousReceiverQueue client = new AsynchronousReceiverQueue();
        client.subscribeMessage();
    }

}
