package ca.uvic.leadlab.cdxconnector;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.handler.Handler;
import javax.xml.ws.handler.HandlerResolver;
import javax.xml.ws.handler.PortInfo;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class WSClient {

    final Logger LOGGER = Logger.getLogger(this.getClass().getName());

//    static {
//        String path = WSClient.class.getClassLoader().getResource("logging.properties").getFile();
//        System.setProperty("java.util.logging.config.file", path);
//        Logger.getLogger(WSClient.class.getName()).info("Loaded logging.properties from " + path);
//    }

    // Base URL
    String baseUrl;

    // Location credentials
    private String username;
    private String password;

    // Certificate
    private String certPath;
    private char[] certPass;

    WSClient(String baseUrl, String username, String password,
             String certPath, String certPass) throws ConnectorException {
        LOGGER.log(Level.INFO, "Initializing WSClient for base URL: " + baseUrl);

        this.baseUrl = baseUrl;
        this.username = username;
        this.password = password;
        this.certPath = certPath;
        this.certPass = certPass.toCharArray();

        setupSSLContext();
    }

    void setupSSLContext() throws ConnectorException {
        try {
            SSLContext context = SSLContext.getInstance("SSLv3");

            KeyManagerFactory factory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());

            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            keyStore.load(new FileInputStream(certPath), certPass);

            factory.init(keyStore, certPass);

            context.init(factory.getKeyManagers(), null, null);

            HttpsURLConnection.setDefaultSSLSocketFactory(context.getSocketFactory());
        } catch (IOException e) {
            String errorMsg = "Error reading the certificate to initialize the SSL Context.";
            LOGGER.log(Level.SEVERE, errorMsg, e);
            throw new ConnectorException(errorMsg, e);
        } catch (GeneralSecurityException e) {
            String errorMsg = "Error initializing the SSL Context.";
            LOGGER.log(Level.SEVERE, errorMsg, e);
            throw new ConnectorException(errorMsg, e);
        }
    }

    HandlerResolver handlerResolver(final QName serviceName) {
        return new HandlerResolver() {
            @Override
            public List<Handler> getHandlerChain(PortInfo portInfo) {
                List<Handler> handlerChain = new ArrayList<>();
                handlerChain.add(new AuthenticationHandler(username, password));
                handlerChain.add(new CustomEnvelopHandler(serviceName));
                //handlerChain.add(new LoggingHandler());
                return handlerChain;
            }
        };
    }

    void setupTimeout(Object service) {
        Map<String, Object> requestCtx = ((BindingProvider) service).getRequestContext();

        requestCtx.put("com.sun.xml.internal.ws.connect.timeout",
                Integer.valueOf(PropertyUtil.getProperty("cdx.connect.timeout", "10000")));
        requestCtx.put("com.sun.xml.internal.ws.request.timeout",
                Integer.valueOf(PropertyUtil.getProperty("cdx.request.timeout", "30000")));
    }
}
