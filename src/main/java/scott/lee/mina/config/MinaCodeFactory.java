package scott.lee.mina.config;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;
import org.apache.mina.filter.codec.textline.LineDelimiter;
import org.apache.mina.filter.codec.textline.TextLineDecoder;
import org.apache.mina.filter.codec.textline.TextLineEncoder;
import org.springframework.stereotype.Component;

import java.nio.charset.Charset;

/**
 * Created by Scott on 16/8/24.
 */
@Component
public class MinaCodeFactory implements ProtocolCodecFactory {

    public MinaCodeFactory() {
        this(Charset.forName("gb2312"));
    }

    private final TextLineEncoder encoder;
    private final TextLineDecoder decoder;
    /* final static char endchar = 0x1a; */
    final static char endchar = 0x0d;

    public MinaCodeFactory(Charset charset) {
        encoder = new TextLineEncoder(charset, LineDelimiter.UNIX);
        decoder = new TextLineDecoder(charset, LineDelimiter.AUTO);
    }

    @Override
    public ProtocolDecoder getDecoder(IoSession session) throws Exception {
        return decoder;
    }

    @Override
    public ProtocolEncoder getEncoder(IoSession session) throws Exception {
        return encoder;
    }

    public int getEncoderMaxLineLength() {
        return encoder.getMaxLineLength();
    }

    public void setEncoderMaxLineLength(int maxLineLength) {
        encoder.setMaxLineLength(maxLineLength);
    }

    public int getDecoderMaxLineLength() {
        return decoder.getMaxLineLength();
    }

    public void setDecoderMaxLineLength(int maxLineLength) {
        decoder.setMaxLineLength(maxLineLength);
    }


}
