package ca.uvic.leadlab.obibconnector.rest;

import javax.net.ssl.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.security.*;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Enumeration;
import java.util.UUID;

class KeyStoreManager implements X509KeyManager, X509TrustManager {

    private X509TrustManager trustManager;
    private X509KeyManager keyManager;

    public KeyStoreManager(String keyStoreFile, String keyStorePass) {

        char[] password = keyStorePass.toCharArray();
        KeyStore keyStore = loadKeystore(keyStoreFile, password);
        loadKeyManager(keyStore, password);
        loadTrustManager(keyStore);
    }

    private KeyStore loadKeystore(String keyStoreFile, char[] password) {
        try {
            KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());
            InputStream in = KeyStoreManager.class.getResourceAsStream("/" + keyStoreFile);
            if (in == null) {
                throw new IOException("KeyStore " + keyStoreFile + " not found.");
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

    private void loadKeyManager(KeyStore keyStore, char[] keystorePass) {
        try {
            KeyManagerFactory factory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
            factory.init(keyStore, keystorePass);

            for (KeyManager manager : factory.getKeyManagers()) {
                if (manager instanceof X509KeyManager) {
                    keyManager = (X509KeyManager) manager;
                    return;
                }
            }

            throw new NoSuchAlgorithmException("No X509KeyManager in KeyManagerFactory");

        } catch (NoSuchAlgorithmException | KeyStoreException | UnrecoverableKeyException e) {
            throw new IllegalArgumentException("Error initializing KeyManager", e);
        }
    }

    private void loadTrustManager(KeyStore keyStore) {
        try {
            // Use the certificate chain to trust the server certificate
            Enumeration<String> enumerator = keyStore.aliases();
            while (enumerator.hasMoreElements()) {
                String alias = enumerator.nextElement();
                Certificate root = keyStore.getCertificate(alias);
                for (Certificate certificate : keyStore.getCertificateChain(alias)) {
                    if (!certificate.equals(root)) {
                        keyStore.setCertificateEntry(UUID.randomUUID().toString(), certificate);
                    }
                }
            }

            TrustManagerFactory factory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            factory.init(keyStore);

            for (TrustManager manager : factory.getTrustManagers()) {
                if (manager instanceof X509TrustManager) {
                    trustManager = (X509TrustManager) manager;
                    return;
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
