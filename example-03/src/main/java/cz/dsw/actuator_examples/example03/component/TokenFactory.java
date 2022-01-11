package cz.dsw.actuator_examples.example03.component;

import cz.dsw.actuator_examples.example03.entity.Token;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.URI;

@Component
public class TokenFactory {

    private static final Logger logger = LoggerFactory.getLogger(TokenFactory.class);

    @Value("${node.id}")
    private String nodeId;
    @Value("${node.name}")
    private String nodeName;

    public <T extends Token> T tokenInstance(Class<T> aClass) {
        try {
            return aClass.getDeclaredConstructor(URI.class, String.class).newInstance(new URI(nodeId), nodeName);
        } catch (Exception e) {
            logger.error("Token instance creation failed!", e);
            return null;
        }
    }

    public <T extends Token> T tokenInstance(URI tid, Class<T> aClass) {
        try {
            return aClass.getDeclaredConstructor(URI.class, String.class, URI.class).newInstance(new URI(nodeId), nodeName, tid);
        } catch (Exception e) {
            logger.error("Token instance creation failed!", e);
            return null;
        }
    }
}
