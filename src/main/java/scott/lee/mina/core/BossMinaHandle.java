package scott.lee.mina.core;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

/**
 * socket接收 发送处理类
 * Created by Scott on 16/8/24.
 */
public class BossMinaHandle  extends IoHandlerAdapter{
    @Override
    public void messageReceived(IoSession session, Object message)
            throws Exception {
        //super.messageReceived(session, message);
        System.out.println("message :"+message);
    }

    @Override
    public void sessionClosed(IoSession session) throws Exception {
        super.sessionClosed(session);
    }

    @Override
    public void sessionIdle(IoSession session, IdleStatus status)
            throws Exception {
        super.sessionIdle(session, status);
    }

    @Override
    public void messageSent(IoSession session, Object message) throws Exception {
        System.out.println("发送的消息是："+message.toString());
    }

    @Override
    public void sessionCreated(IoSession session) throws Exception {
        super.sessionCreated(session);
    }

    @Override
    public void sessionOpened(IoSession session) throws Exception {
        super.sessionOpened(session);
    }
}
