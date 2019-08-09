package ca.uvic.leadlab.obibconnector.rest;

import javax.net.ssl.*;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.Socket;
import java.security.*;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

class KeyStoreManager implements X509KeyManager, X509TrustManager {

    private final X509KeyManager keyManager;
    private final X509TrustManager trustManager;

    public KeyStoreManager(final String keyStoreFile, final String keyStorePass) {
        char[] password = keyStorePass.toCharArray();
        KeyStore keyStore = loadKeystore(keyStoreFile, password);
        keyManager = loadKeyManager(keyStore, password);
        trustManager = loadTrustManager(keyStore);
    }

    private KeyStore loadKeystore(String keyStoreFile, char[] password) {
        try {
            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            InputStream in = KeyStoreManager.class.getResourceAsStream(keyStoreFile);
            if (in == null) {
                in = new FileInputStream(keyStoreFile);
            }
            try {
                keyStore.load(in, password);
            } finally {
                in.close();
            }
            return keyStore;
        } catch (Exception e) {
            throw new IllegalArgumentException("Error loading KeyStore", e);
        }
    }

    private X509KeyManager loadKeyManager(KeyStore keyStore, char[] keystorePass) {
        try {
            KeyManagerFactory factory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
            factory.init(keyStore, keystorePass);

            for (KeyManager manager : factory.getKeyManagers()) {
                if (manager instanceof X509KeyManager) {
                    return (X509KeyManager) manager;
                }
            }

            throw new NoSuchAlgorithmException("No X509KeyManager in KeyManagerFactory");

        } catch (NoSuchAlgorithmException | KeyStoreException | UnrecoverableKeyException e) {
            throw new IllegalArgumentException("Error initializing KeyManager", e);
        }
    }

    private X509TrustManager loadTrustManager(KeyStore keyStore) {
        try {
            TrustManagerFactory factory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            factory.init(keyStore);

            for (TrustManager manager : factory.getTrustManagers()) {
                if (manager instanceof X509TrustManager) {
                    return (X509TrustManager) manager;
                }
            }

            throw new NoSuchAlgorithmException("No X509TrustManager in TrustManagerFactory");

        } catch (KeyStoreException | NoSuchAlgorithmException e) {
            throw new IllegalArgumentException("Error initializing TrustManager", e);
        }
    }

    @Override
    public String[] getClientAliases(String keyType, Principal[] issuers) {
        return keyManager.getClientAliases(keyType, issuers);
    }

    @Override
    public String chooseClientAlias(String[] keyType, Principal[] issuers, Socket socket) {
        return keyManager.chooseClientAlias(keyType, issuers, socket);
    }

    @Override
    public String[] getServerAliases(String keyType, Principal[] issuers) {
        return keyManager.getServerAliases(keyType, issuers);
    }

    @Override
    public String chooseServerAlias(String keyType, Principal[] issuers, Socket socket) {
        return keyManager.chooseServerAlias(keyType, issuers, socket);
    }

    @Override
    public X509Certificate[] getCertificateChain(String alias) {
        return keyManager.getCertificateChain(alias);
    }

    @Override
    public PrivateKey getPrivateKey(String alias) {
        return keyManager.getPrivateKey(alias);
    }

    @Override
    public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        trustManager.checkClientTrusted(chain, authType);
    }

    @Override
    public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        trustManager.checkServerTrusted(chain, authType);
    }

    @Override
    public X509Certificate[] getAcceptedIssuers() {
        return trustManager.getAcceptedIssuers();
    }

}
