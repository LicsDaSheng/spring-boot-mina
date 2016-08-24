package scott.lee.mina.config;

import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
import org.apache.mina.core.filterchain.IoFilter;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.executor.ExecutorFilter;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.filter.logging.MdcInjectionFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import scott.lee.mina.core.ReceiveMinaHandle;

import java.net.InetSocketAddress;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * mina配置
 * Created by Scott on 16/8/24.
 */
@Configuration
@ConfigurationProperties(prefix = "mina")
public class MinaSocketConfig {

    private int port;

    @Bean(initMethod = "bind", destroyMethod = "unbind")
    public NioSocketAcceptor nioSocketAcceptor(ReceiveMinaHandle receiveMinaHandle,DefaultIoFilterChainBuilder defaultIoFilterChainBuilder) {

        NioSocketAcceptor nioSocketAcceptor = new NioSocketAcceptor();
        nioSocketAcceptor.setDefaultLocalAddress(new InetSocketAddress(port));
        nioSocketAcceptor.setReuseAddress(true);
        nioSocketAcceptor.setFilterChainBuilder(defaultIoFilterChainBuilder);
        nioSocketAcceptor.setHandler(receiveMinaHandle);
        return nioSocketAcceptor;
    }

    @Bean
    public DefaultIoFilterChainBuilder defaultIoFilterChainBuilder(ExecutorFilter executorFilter, MdcInjectionFilter mdcInjectionFilter, ProtocolCodecFilter protocolCodecFilter, LoggingFilter loggingFilter) {
        DefaultIoFilterChainBuilder defaultIoFilterChainBuilder = new DefaultIoFilterChainBuilder();
        Map<String, IoFilter> filters = new LinkedHashMap<>();
        filters.put("executor", executorFilter);
        filters.put("mdcInjectionFilter", mdcInjectionFilter);
        filters.put("codecFilter", protocolCodecFilter);
        filters.put("loggingFilter", loggingFilter);
        defaultIoFilterChainBuilder.setFilters(filters);
        return defaultIoFilterChainBuilder;
    }

    @Bean
    public ExecutorFilter executorFilter() {
        return new ExecutorFilter();
    }

    @Bean
    public MdcInjectionFilter mdcInjectionFilter() {
        return new MdcInjectionFilter(MdcInjectionFilter.MdcKey.remoteAddress);
    }

    @Bean
    public ProtocolCodecFilter protocolCodecFilter(MinaCodeFactory minaCodeFactory) {
        return new ProtocolCodecFilter(minaCodeFactory);
    }

    @Bean
    public LoggingFilter loggingFilter() {
        return new LoggingFilter();
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
