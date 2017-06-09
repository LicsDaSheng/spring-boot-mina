package scott.lee.mina.core;

import com.sun.javafx.binding.Logging;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * socket接收处理类
 * Created by Scott on 16/8/24.
 */
@Component
public class ReceiveMinaHandle extends IoHandlerAdapter{
    private static Logger LOGGER = LoggerFactory.getLogger(ReceiveMinaHandle.class);

    @Override
    public void messageReceived(IoSession session, Object message)
            throws Exception {
        // TODO Auto-generated method stub
        LOGGER.info(String.format("message : %s", message));
    }

    @Override
    public void sessionClosed(IoSession session) throws Exception {
        // TODO Auto-generated method stub
        LOGGER.info("session :" + session.getId());
        super.sessionClosed(session);
    }

    @Override
    public void sessionIdle(IoSession session, IdleStatus status)
            throws Exception {
        // TODO Auto-generated method stub
        LOGGER.info(String.format("session [%s] ,status [%s]",session.getId(), status.toString()));
        super.sessionIdle(session, status);
    }
}
